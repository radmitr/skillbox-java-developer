package abstract_factory;

// Client -> Route
// Client -> RouteAbstractFactory
public class RouteFactory {

    public static Route getRoute(RouteAbstractFactory factory) {
        return factory.createRoute();
    }
}
