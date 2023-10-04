package abstract_factory;

import java.awt.Point;
import java.util.List;

// Abstract product - Route
public abstract class Route {

    protected List<Point> points;

    public Route(List<Point> points) {
        this.points = points;
    }

    public abstract double calculateDuration();

    public abstract double calculateLength();
}
