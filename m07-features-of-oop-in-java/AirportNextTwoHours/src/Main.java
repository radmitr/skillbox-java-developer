import com.skillbox.airport.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        List<Terminal> terminals = airport.getTerminals();
        List<Flight> flightList = new ArrayList<>();

        for (Terminal terminal : terminals) {
            flightList.addAll(terminal.getFlights());
        }

//        List<Aircraft> aircrafts = airport.getAllAircrafts();
//        System.out.println(airport);
//        System.out.println(terminals.get(0).getFlights().get(0).getDate().getTime());
//        System.out.println(terminals.get(0).getFlights().get(0).getDate());

        long currentTime = System.currentTimeMillis();

        // Without stream API
        for (Terminal terminal : terminals) {
            List<Flight> flights = terminal.getFlights();
            for (Flight flight : flights) {
                long flightTime = flight.getDate().getTime();
                if (flight.getType() == Flight.Type.DEPARTURE) {
                    if ((flightTime >= currentTime) && (flightTime - currentTime) < (2 * 60 * 60 * 1000)) {
                        System.out.println(flight);
                    }
                }
            }
        }
        System.out.println();

        // Variant 1 (in milliseconds)
        terminals.forEach(terminal -> terminal.getFlights().stream()
                .filter(flight -> flight.getType() == Flight.Type.DEPARTURE)
                .map(flight -> flight.getDate().getTime())
                .filter(time -> ((time - currentTime) < (2 * 60 * 60 * 1000)) && (time >= currentTime))
                .forEach(System.out::println)
        );
        System.out.println();

        // Variant 2 (in milliseconds)
        flightList.stream()
                .filter(flight -> flight.getType() == Flight.Type.DEPARTURE)
                .map(flight -> flight.getDate().getTime())
                .filter(time -> ((time - currentTime) < (2 * 60 * 60 * 1000)) && (time >= currentTime))
                .forEach(System.out::println);
        System.out.println();

        // Variant 3
        flightList.stream()
                .filter(flight -> flight.getType() == Flight.Type.DEPARTURE)
                .filter(flight -> ((flight.getDate().getTime() - currentTime) < (2 * 60 * 60 * 1000))
                        && (flight.getDate().getTime()) >= currentTime)
                .forEach(System.out::println);
    }
}
