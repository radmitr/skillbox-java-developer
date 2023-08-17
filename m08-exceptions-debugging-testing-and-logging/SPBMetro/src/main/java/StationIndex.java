import core.Line;
import core.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class StationIndex {

    private static final Logger log = LogManager.getLogger(StationIndex.class);

    HashMap<Integer, Line> number2line;
    TreeSet<Station> stations;
    TreeMap<Station, TreeSet<Station>> connections;

    public StationIndex() {
        log.trace("Constructor start");
        number2line = new HashMap<>();
        stations = new TreeSet<>();
        connections = new TreeMap<>();
        log.trace("Constructor end");
    }

    public void addStation(Station station) {
        log.debug("Add station: {}", station.getName());
        stations.add(station);
    }

    public void addLine(Line line) {
        log.debug("Add line: {}", line.getNumber());
        number2line.put(line.getNumber(), line);
    }

    public void addConnection(List<Station> stations) {
        for (Station station : stations) {
            if (!connections.containsKey(station)) {
                log.debug("Add station to connection: {}", station.getName());
                connections.put(station, new TreeSet<>());
            }
            TreeSet<Station> connectedStations = connections.get(station);
            log.debug("Add connected stations");
            connectedStations.addAll(stations.stream()
                    .filter(s -> !s.equals(station)).collect(Collectors.toList()));
        }
    }

    public Line getLine(int number) {
        return number2line.get(number);
    }

    public Station getStation(String name) {
        for (Station station : stations) {
            if (station.getName().equalsIgnoreCase(name)) {
                return station;
            }
        }
        return null;
    }

    public Station getStation(String name, int lineNumber) {
        Station query = new Station(name, getLine(lineNumber));
        Station station = stations.ceiling(query);
        return station.equals(query) ? station : null;
    }

    public Set<Station> getConnectedStations(Station station) {
        if (connections.containsKey(station)) {
            return connections.get(station);
        }
        return new TreeSet<>();
    }
}
