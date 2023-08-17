package ru.radmitr.core;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Station implements Comparable<Station> {

    @SerializedName("line")
    private final String lineId;

    @SerializedName("station")
    private final String name;

    public Station(String lineId, String name) {
        this.lineId = lineId;
        this.name = name;
    }

    public String getLineId() {
        return lineId;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Station station) {
        int lineComparison = lineId.compareTo(station.getLineId());
        if (lineComparison != 0) {
            return lineComparison;
        }
        return name.compareToIgnoreCase(station.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Station station = (Station) o;

        if (!Objects.equals(lineId, station.lineId)) return false;
        return Objects.equals(name, station.name);
    }

    @Override
    public int hashCode() {
        int result = lineId != null ? lineId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Station{" +
                "lineId='" + lineId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
