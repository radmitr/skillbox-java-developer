package abstract_factory;

import java.awt.Point;
import java.util.List;

// Concrete factory 1 - CarRouteFactory
public class CarRouteFactory implements RouteAbstractFactory {

    private List<Point> points;

    public CarRouteFactory(List<Point> points) {
        this.points = points;
    }

    @Override
    public Route createRoute() {
        // Product 1 -  CarRoute
        return new CarRoute(points);
    }
}
