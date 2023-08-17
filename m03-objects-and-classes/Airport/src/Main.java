import com.skillbox.airport.Airport;
import com.skillbox.airport.Terminal;
import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Flight;

// Task 3.8
public class Main {

    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        System.out.println(airport.getAllAircrafts());
        System.out.println(airport.getTerminals());

        Terminal terminal = airport.getTerminals().get(0);
        System.out.println(terminal.getName());
        System.out.println(terminal.getFlights());
        System.out.println(terminal.getParkedAircrafts());

        Aircraft aircraft = terminal.getParkedAircrafts().get(0);
        System.out.println(aircraft);

        Flight flight = terminal.getFlights().get(0);
        System.out.println(flight);
//        System.out.println(airport.hashCode());
//        System.out.println(airport.getClass());
    }
}
