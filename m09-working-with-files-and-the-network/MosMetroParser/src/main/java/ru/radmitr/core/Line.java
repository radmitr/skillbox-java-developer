package ru.radmitr.core;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Line implements Comparable<Line> {

    @SerializedName("id")
    private final String id;

    @SerializedName("name")
    private final String name;

    @SerializedName("color")
    private final String color;

    public Line(String id, String name) {
        this.id = id;
        this.name = name;
        this.color = "Undefined";
    }

    public Line(String id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    @Override
    public int compareTo(Line line) {
        int idComparison = id.compareToIgnoreCase(line.getId());
        if (idComparison != 0) {
            return idComparison;
        }
        return name.compareToIgnoreCase(line.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (!Objects.equals(id, line.id)) return false;
        return Objects.equals(name, line.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Line{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
