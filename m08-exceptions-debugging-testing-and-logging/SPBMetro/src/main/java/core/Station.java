package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Station implements Comparable<Station> {

    private static final Logger log = LogManager.getLogger(Station.class);

    private Line line;
    private String name;

    public Station(String name, Line line) {
        log.trace("Constructor start: {}-{}", line.getNumber(), name);
        this.name = name;
        this.line = line;
        log.trace("Constructor end: {}-{}", line.getNumber(), name);
    }

    public Line getLine() {
        return line;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Station station) {
        int lineComparison = line.compareTo(station.getLine());
        if (lineComparison != 0) {
            return lineComparison;
        }
        return name.compareToIgnoreCase(station.getName());
    }

    @Override
    public boolean equals(Object obj) {
        return compareTo((Station) obj) == 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
