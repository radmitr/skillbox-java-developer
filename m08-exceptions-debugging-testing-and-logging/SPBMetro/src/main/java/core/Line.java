package core;

// --- log4j ---
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// --- slf4j ---
//import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Line implements Comparable<Line> {

    // log4j (comment out slf4j import)
    private static final Logger log = LogManager.getLogger(Line.class);
    // log4j2-slf4j2 (comment out log4j import)
//    private static final Logger log = LoggerFactory.getLogger(Line.class);

    private int number;
    private String name;
    private List<Station> stations;

    public Line(int number, String name) {
        log.trace("Constructor start: {}-{}", number, name);
        this.number = number;
        this.name = name;
        stations = new ArrayList<>();
//        if (log.isDebugEnabled()) {
            log.debug("Method insertion check: {}", makeSomething());
//        }
        log.trace("constructor end: {}-{}", number, name);
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    public List<Station> getStations() {
        return stations;
    }

    @Override
    public int compareTo(Line line) {
        return Integer.compare(number, line.getNumber());
    }

    @Override
    public boolean equals(Object obj) {
        return compareTo((Line) obj) == 0;
    }

    private static String makeSomething() {
        System.out.println("METHOD WAS INVOKED even if the log fails the level");
        return "METHOD WAS INVOKED even if the log fails the level";
    }
}
