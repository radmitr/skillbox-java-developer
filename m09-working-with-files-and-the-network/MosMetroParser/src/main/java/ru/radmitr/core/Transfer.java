package ru.radmitr.core;

import com.google.gson.annotations.SerializedName;

import java.util.Set;

public class Transfer {

    @SerializedName("fromStation")
    private final Station station;

    @SerializedName("toStation(s)")
    private final Set<Station> connectedStations;

    public Transfer(Station station, Set<Station> connectedStations) {
        this.station = station;
        this.connectedStations = connectedStations;
    }

    public Station getStation() {
        return station;
    }

    public Set<Station> getConnectedStations() {
        return connectedStations;
    }

    @Override
    public String toString() {
        return "TransferStations{" +
                "station=" + station +
                ", connectedStations=" + connectedStations +
                '}';
    }
}
