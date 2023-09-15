import core.Line;
import core.Station;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class RouteCalculatorTest {

    private static final Logger log = LoggerFactory.getLogger(RouteCalculatorTest.class); // slf4j

    static StationIndex stationIndex;
    static RouteCalculator calculator;
    static List<Station> routeWithoutConnections;
    static List<Station> routeWithOneConnection;
    static List<Station> routeWithTwoConnections;
    static List<Station> routeWithoutConnectionsReversed;
    static List<Station> routeWithOneConnectionReversed;
    static List<Station> routeWithTwoConnectionsReversed;

    @BeforeAll
    static void setUp() {

        // ---------------- Metro ---------------------

        ///////////////////////////////////////////////
        //                                           //
        //              (line2)                      //
        //                 |                         //
        //                 | connection1             //
        //   --------------|-------------- (line1)   //
        //                 |                         //
        //                 | connection2             //
        //   --------------|-------------- (line3)   //
        //                 |                         //
        //                                           //
        //   ----------------------------- (line4)   //
        //                                           //
        ///////////////////////////////////////////////

        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");
        Line line4 = new Line(4, "Четвёртая");

        stationIndex = new StationIndex();

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        stationIndex.addLine(line4);

        // line 1
        Station station1 = new Station("Апельсиновая", line1);
        Station station2 = new Station("Яблочная", line1);
        Station station3 = new Station("Арбузная", line1);
        Station station4 = new Station("Виноградная", line1); // connection1
        Station station5 = new Station("Персиковая", line1);
        stationIndex.addStation(station1);
        stationIndex.addStation(station2);
        stationIndex.addStation(station3);
        stationIndex.addStation(station4);
        stationIndex.addStation(station5);
        line1.addStation(station1);
        line1.addStation(station2);
        line1.addStation(station3);
        line1.addStation(station4);
        line1.addStation(station5);

        // line 2
        Station station6 = new Station("Морковная", line2);
        Station station7 = new Station("Свекольная", line2); // connection1
        Station station8 = new Station("Луковая", line2);
        Station station9 = new Station("Капустная", line2); // connection2
        Station station10 = new Station("Фасольная", line2);
        stationIndex.addStation(station6);
        stationIndex.addStation(station7);
        stationIndex.addStation(station8);
        stationIndex.addStation(station9);
        stationIndex.addStation(station10);
        line2.addStation(station6);
        line2.addStation(station7);
        line2.addStation(station8);
        line2.addStation(station9);
        line2.addStation(station10);

        // line 3
        Station station11 = new Station("Петровская", line3);
        Station station12 = new Station("Николаевская", line3); // connection2
        Station station13 = new Station("Михайловская", line3);
        Station station14 = new Station("Игоревская", line3);
        Station station15 = new Station("Рустемовская", line3);
        stationIndex.addStation(station11);
        stationIndex.addStation(station12);
        stationIndex.addStation(station13);
        stationIndex.addStation(station14);
        stationIndex.addStation(station15);
        line3.addStation(station11);
        line3.addStation(station12);
        line3.addStation(station13);
        line3.addStation(station14);
        line3.addStation(station15);

        // line 4 (no intersection)
        Station station16 = new Station("Кошачья", line4);
        Station station17 = new Station("Собачья", line4);
        stationIndex.addStation(station16);
        stationIndex.addStation(station17);
        line4.addStation(station16);
        line4.addStation(station17);

        // connections
        List<Station> connection1 = new ArrayList<>();
        connection1.add(stationIndex.getStation("Виноградная", 1));
        connection1.add(stationIndex.getStation("Свекольная", 2));
        List<Station> connection2 = new ArrayList<>();
        connection2.add(stationIndex.getStation("Капустная", 2));
        connection2.add(stationIndex.getStation("Николаевская", 3));

        stationIndex.addConnection(connection1);
        stationIndex.addConnection(connection2);

        // --------------- Routes ---------------
        // route without connections
        routeWithoutConnections = new ArrayList<>();

        routeWithoutConnections.add(stationIndex.getStation("Яблочная", 1));
        routeWithoutConnections.add(stationIndex.getStation("Арбузная", 1));
        routeWithoutConnections.add(stationIndex.getStation("Виноградная", 1));

        routeWithoutConnectionsReversed = new ArrayList<>(routeWithoutConnections);
        Collections.reverse(routeWithoutConnectionsReversed);

        // route with one connection
        routeWithOneConnection = new ArrayList<>();

        routeWithOneConnection.add(stationIndex.getStation("Арбузная", 1));
        routeWithOneConnection.add(stationIndex.getStation("Виноградная", 1));
        routeWithOneConnection.add(stationIndex.getStation("Свекольная", 2));
        routeWithOneConnection.add(stationIndex.getStation("Луковая", 2));

        routeWithOneConnectionReversed = new ArrayList<>(routeWithOneConnection);
        Collections.reverse(routeWithOneConnectionReversed);

        // route with two connections
        routeWithTwoConnections = new ArrayList<>();

        routeWithTwoConnections.add(stationIndex.getStation("Яблочная", 1));
        routeWithTwoConnections.add(stationIndex.getStation("Арбузная", 1));
        routeWithTwoConnections.add(stationIndex.getStation("Виноградная", 1));
        routeWithTwoConnections.add(stationIndex.getStation("Свекольная", 2));
        routeWithTwoConnections.add(stationIndex.getStation("Луковая", 2));
        routeWithTwoConnections.add(stationIndex.getStation("Капустная", 2));
        routeWithTwoConnections.add(stationIndex.getStation("Николаевская", 3));
        routeWithTwoConnections.add(stationIndex.getStation("Михайловская", 3));
        routeWithTwoConnections.add(stationIndex.getStation("Игоревская", 3));

        routeWithTwoConnectionsReversed = new ArrayList<>(routeWithTwoConnections);
        Collections.reverse(routeWithTwoConnectionsReversed);

        // --------------- Calculator ---------------
        calculator = new RouteCalculator(stationIndex);
    }

    @Nested
    @DisplayName("Testing method: getShortestRoute()")
    @Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class GetShortestRoute {
        @Test
        @Order(1)
        void getShortestRoute_withoutConnections() {
            LocalDateTime dt = LocalDateTime.now();
            log.debug("TEST SLF4J {}", dt);
            List<Station> expected = routeWithoutConnections;
            List<Station> actual = calculator.getShortestRoute(stationIndex.getStation("Яблочная"),
                    stationIndex.getStation("Виноградная"));
            assertEquals(expected, actual);
        }

        @Test
        @Order(2)
        void getShortestRoute_withoutConnectionsAndWithReversedStations() {
            List<Station> expected = routeWithoutConnectionsReversed;
            List<Station> actual = calculator.getShortestRoute(stationIndex.getStation("Виноградная"),
                    stationIndex.getStation("Яблочная"));
            assertEquals(expected, actual);
        }

        @Test
        @Order(3)
        void getShortestRoute_withOneConnection() {
            List<Station> expected = routeWithOneConnection;
            List<Station> actual = calculator.getShortestRoute(stationIndex.getStation("Арбузная"),
                    stationIndex.getStation("Луковая"));
            assertEquals(expected, actual);
        }

        @Test
        @Order(4)
        void getShortestRoute_withOneConnectionAndReversedStations() {
            List<Station> expected = routeWithOneConnectionReversed;
            List<Station> actual = calculator.getShortestRoute(stationIndex.getStation("Луковая"),
                    stationIndex.getStation("Арбузная"));
            assertEquals(expected, actual);
        }

        @Test
        @Order(5)
        void getShortestRoute_withTwoConnections() {
            List<Station> expected = routeWithTwoConnections;
            List<Station> actual = calculator.getShortestRoute(stationIndex.getStation("Яблочная"),
                    stationIndex.getStation("Игоревская"));
            assertEquals(expected, actual);
        }

        @Test
        @Order(6)
        void getShortestRoute_withTwoConnectionsAndReversedStations() {
            List<Station> expected = routeWithTwoConnectionsReversed;
            List<Station> actual = calculator.getShortestRoute(stationIndex.getStation("Игоревская"),
                    stationIndex.getStation("Яблочная"));
            assertEquals(expected, actual);
        }

        @Test
        @Order(7)
        void getShortestRoute_withUnconnectedStations_shouldReturnZeroSizeRoute() {
            List<Station> actual = calculator.getShortestRoute(stationIndex.getStation("Яблочная"),
                    stationIndex.getStation("Собачья"));
            assertEquals(0, actual.size());
        }
    }

    @Nested
    @DisplayName("Testing static method: calculateDuration()")
    @Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class CalculateDuration {
        @Test
        @Order(1)
        void calculateDuration_withRouteNotHavingConnections() {
            double actual = RouteCalculator.calculateDuration(routeWithoutConnections);
            double expected = 5;
            assertEquals(expected, actual);
        }

        @Test
        @Order(2)
        void calculateDuration_withRouteNotHavingConnectionsAndWithReversedStations() {
            double actual = RouteCalculator.calculateDuration(routeWithoutConnectionsReversed);
            double expected = 5;
            assertEquals(expected, actual);
        }

        @Test
        @Order(3)
        void calculateDuration_withRouteHavingOneConnection() {
            double actual = RouteCalculator.calculateDuration(routeWithOneConnection);
            double expected = 8.5;
            assertEquals(expected, actual);
        }

        @Test
        @Order(4)
        void calculateDuration_withRouteHavingOneConnectionAndWithReversedStations() {
            double actual = RouteCalculator.calculateDuration(routeWithOneConnectionReversed);
            double expected = 8.5;
            assertEquals(expected, actual);
        }

        @Test
        @Order(5)
        void calculateDuration_withRouteHavingTwoConnections() {
            double actual = RouteCalculator.calculateDuration(routeWithTwoConnections);
            double expected = 22;
            assertEquals(expected, actual);
        }

        @Test
        @Order(6)
        void calculateDuration_withRouteHavingTwoConnectionsReversed() {
            double actual = RouteCalculator.calculateDuration(routeWithTwoConnectionsReversed);
            double expected = 22;
            assertEquals(expected, actual);
        }

        @Test
        @Order(7)
        void calculateDuration_withZeroSizeRoute_shouldReturnZero() {
            double actual = RouteCalculator.calculateDuration(new ArrayList<>());
            double expected = 0;
            assertEquals(expected, actual);
        }
    }

    @AfterAll
    static void tearDown() {
        // ---------------- Metro ---------------------
        stationIndex = null;

        // --------------- Calculator ---------------
        calculator = null;

        // --------------- Routes ---------------
        routeWithoutConnections.clear();
        routeWithoutConnectionsReversed.clear();
        routeWithOneConnection.clear();
        routeWithOneConnectionReversed.clear();
        routeWithTwoConnections.clear();
        routeWithTwoConnectionsReversed.clear();
    }
}
