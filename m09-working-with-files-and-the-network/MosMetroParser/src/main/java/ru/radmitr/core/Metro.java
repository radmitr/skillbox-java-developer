package ru.radmitr.core;

import com.google.gson.annotations.SerializedName;

import java.util.*;

public class Metro {

    @SerializedName("stations")
    private Map<String, Set<String>> stations;

    @SerializedName("lines")
    private Set<Line> lines;

    @SerializedName("transfers")
    private List<Transfer> transfers;

    public Metro() {
    }

    public Metro(Map<String, Set<String>> stations, Set<Line> lines, List<Transfer> transfers) {
        this.stations = stations;
        this.lines = lines;
        this.transfers = transfers;
    }

    public Map<String, Set<String>> getStations() {
        return stations;
    }

    public void setStations(Map<String, Set<String>> stations) {
        this.stations = stations;
    }

    public Set<Line> getLines() {
        return lines;
    }

    public void setLines(Set<Line> lines) {
        this.lines = lines;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<Transfer> transfers) {
        this.transfers = transfers;
    }

    @Override
    public String toString() {
        return "Metro{" +
                "stations=" + stations +
                ", lines=" + lines +
                ", transfers=" + transfers +
                '}';
    }
}
