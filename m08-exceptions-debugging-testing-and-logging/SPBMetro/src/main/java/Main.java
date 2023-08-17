import core.Line;
import core.Station;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// --- log4j ---
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// --- slf4j ---
//import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    // log4j2 (comment out slf4j import)
    private static final Logger log = LogManager.getRootLogger();
    // log4j2-slf4j2 (comment out log4j import)
//    private static final Logger log = LoggerFactory.getLogger(Main.class);

    // file exist
    private static final String MAP_JSON_PATH = "src/main/resources/map.json";
    // file doesn't exist
//    private static final String MAP_JSON_PATH = "src/main/resources/map2.json";

    private static Scanner scanner;
    private static StationIndex stationIndex;

    public static void main(String[] args) {
        try {
            RouteCalculator calculator = getRouteCalculator();

            System.out.println("Программа расчёта маршрутов метрополитена Санкт-Петербурга");
            scanner = new Scanner(System.in);
            for (;;) {
                Station from = takeStation("Введите станцию отправления:");
                Station to = takeStation("Введите станцию назначения:");

                List<Station> route = calculator.getShortestRoute(from, to);
                System.out.println("Маршрут:");
                printRoute(route);

                System.out.println("Длительность: " +
                        RouteCalculator.calculateDuration(route) + " минут\n");
            }
        } catch (NoSuchElementException e) {
            // after Ctrl+D
            log.info("Process finished with exit code 0");
            System.exit(0);
        } catch (IOException e) {
            // JSON file doesn't exist
            log.error("Process finished with exit code -11", e);
            System.exit(-11);
        } catch (NullPointerException e) {
            // object or array doesn't exist in JSON file
            log.error("Process finished with exit code -12", e);
            System.exit(-12);
        } catch (Exception e) {
            // other
            log.error("Process finished with exit code -13", e);
            System.exit(-13);
        }
    }

    private static RouteCalculator getRouteCalculator() throws Exception {
        createStationIndex();
        return new RouteCalculator(stationIndex);
    }

    private static void printRoute(List<Station> route) {
        Station previousStation = null;
        for (Station station : route) {
            if (previousStation != null) {
                Line prevLine = previousStation.getLine();
                Line nextLine = station.getLine();
                if (!prevLine.equals(nextLine)) {
                    System.out.println("\tПереход на станцию " +
                        station.getName() + " (" + nextLine.getName() + " линия)");
                }
            }
            System.out.println("\t" + station.getName());
            previousStation = station;
        }
    }

    private static Station takeStation(String message) {
        for (;;) {
            System.out.println(message);
            String line = scanner.nextLine().trim();
            Station station = stationIndex.getStation(line);
            if (station != null) {
                log.info("Введена станция: {}", line);
                return station;
            }
            log.warn("Станция не найдена: {}", line);
            System.out.println("Станция не найдена :(");
        }
    }

    private static void createStationIndex() throws Exception {
        stationIndex = new StationIndex();

        JSONParser parser = new JSONParser();
        JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());

        JSONArray linesArray = (JSONArray) jsonData.get("lines");
        if (linesArray == null) {
            throw new NullPointerException("Array \"lines\" doesn't exist in JSON file");
        }
        parseLines(linesArray);

        JSONObject stationsObject = (JSONObject) jsonData.get("stations");
        if (stationsObject == null) {
            throw new NullPointerException("Object \"stations\" doesn't exist in JSON file");
        }
        parseStations(stationsObject);

        JSONArray connectionsArray = (JSONArray) jsonData.get("connections");
        if (connectionsArray == null) {
            throw new NullPointerException("Object \"connections\" doesn't exist in JSON file");
        }
        parseConnections(connectionsArray);
    }

    private static void parseLines(JSONArray linesArray) {
        linesArray.forEach(lineObject -> {
            JSONObject lineJsonObject = (JSONObject) lineObject;
            Line line = new Line(
                    ((Long) lineJsonObject.get("number")).intValue(),
                    (String) lineJsonObject.get("name")
            );
            stationIndex.addLine(line);
        });
    }

    private static void parseStations(JSONObject stationsObject) {
        stationsObject.keySet().forEach(lineNumberObject -> {
            int lineNumber = Integer.parseInt((String) lineNumberObject);
            Line line = stationIndex.getLine(lineNumber);
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            stationsArray.forEach(stationObject -> {
                Station station = new Station((String) stationObject, line);
                stationIndex.addStation(station);
                line.addStation(station);
            });
        });
    }

    private static void parseConnections(JSONArray connectionsArray) {
        connectionsArray.forEach(connectionObject -> {
            JSONArray connection = (JSONArray) connectionObject;
            List<Station> connectionStations = new ArrayList<>();
            connection.forEach(item -> {
                JSONObject itemObject = (JSONObject) item;
                int lineNumber = ((Long) itemObject.get("line")).intValue();
                String stationName = (String) itemObject.get("station");

                Station station = stationIndex.getStation(stationName, lineNumber);
                if (station == null) {
                    throw new IllegalArgumentException("core.Station " +
                            stationName + " on line " + lineNumber + " not found");
                }
                connectionStations.add(station);
            });
            stationIndex.addConnection(connectionStations);
        });
    }

    private static String getJsonFile() throws IOException {
        StringBuilder builder = new StringBuilder();
        List<String> lines;

        lines = Files.readAllLines(Paths.get(MAP_JSON_PATH));
        lines.forEach(line -> builder.append(line));

        return builder.toString();
    }
}
