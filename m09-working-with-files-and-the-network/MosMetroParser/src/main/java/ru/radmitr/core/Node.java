package ru.radmitr.core;

import com.google.gson.annotations.SerializedName;

import java.util.Set;

public class Node {

    @SerializedName("nodes")
    private final Set<Station> nodes;

    public Node(Set<Station> nodes) {
        this.nodes = nodes;
    }

    public Set<Station> getNodes() {
        return nodes;
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodes=" + nodes +
                '}';
    }
}
