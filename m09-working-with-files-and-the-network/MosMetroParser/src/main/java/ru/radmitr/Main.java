package ru.radmitr;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import ru.radmitr.core.Line;
import ru.radmitr.core.Metro;
import ru.radmitr.core.Transfer;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class Main {

    private final static String MOSMETRO_MAP_JSON_PATH = "src/main/resources/mosmetro.json";

    public static void main(String[] args) throws IOException {
        ParserWikiMosMetroHtml metroParser = new ParserWikiMosMetroHtml();

        Map<String, Set<String>> stations = metroParser.parseStations();
        Set<Line> lines = metroParser.parseLines();
        List<Transfer> transfers = metroParser.parseTransferStations();

        Metro metro = new Metro(stations, lines, transfers);
        writeMetroToJson(metro, MOSMETRO_MAP_JSON_PATH);

        Metro readMetro = readMetroFromJsom(MOSMETRO_MAP_JSON_PATH);
        printMetroAsJson(readMetro);
        System.out.println("----------------------------------------------");

        printNumberOfStationsOnLines(readMetro);
    }

    private static void writeMetroToJson(Metro metro, String path) throws IOException {
        FileWriter writer = new FileWriter(path);
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(metro, writer);
            writer.flush();
        } finally {
            writer.close();
        }
    }

    private static Metro readMetroFromJsom(String path) throws IOException {
        Metro metro = new Metro();
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new FileReader(path));

        Map<String, Set<String>> readStations;
        Set<Line> readLines;
        List<Transfer> readTransfers;

        try {
            // read JSON elements
            JsonObject metroObject = gson.fromJson(reader, JsonObject.class);
            JsonObject stationsObject = metroObject.get("stations").getAsJsonObject();
            JsonArray linesArray = metroObject.get("lines").getAsJsonArray();
            JsonArray transfersArray = metroObject.get("transfers").getAsJsonArray();

            // read stations
            Type stationsType = new TypeToken<Map<String, Set<String>>>(){}.getType();
            readStations = gson.fromJson(stationsObject, stationsType);

            // read lines
            Type linesType = new TypeToken<Set<Line>>(){}.getType();
            readLines = gson.fromJson(linesArray, linesType);

            // read transfers
            Type transfersType= new TypeToken<List<Transfer>>(){}.getType();
            readTransfers = gson.fromJson(transfersArray, transfersType);
        } finally {
            reader.close();
        }

        metro.setStations(readStations);
        metro.setLines(readLines);
        metro.setTransfers(readTransfers);

        return metro;
    }

    private static void printNumberOfStationsOnLines(Metro metro) {
        metro.getStations().entrySet()
                .stream()
                .forEach(s -> {
                    long amount = s.getValue().stream().count();
                    String lineId = s.getKey();
                    Optional<String> lineNameOptional = metro.getLines().stream()
                            .filter(l -> l.getId().equals(lineId))
                            .findFirst()
                            .map(Line::getName);
                    String lineName = "";
                    if (lineNameOptional.isPresent()) {
                        lineName = lineNameOptional.get();
                    }
                    System.out.println(lineId + "-" + lineName + ": " + amount + " ст.");
                });
    }

    private static void printMetroAsJson(Metro metro) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String stringMetro = gson.toJson(metro);
        System.out.println(stringMetro);
    }
}
