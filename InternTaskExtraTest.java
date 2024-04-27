import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class InternTaskExtraTest {

    InternTaskExtra travelTime = new InternTaskExtra();
    // Test case for a simple scenario
    @Test
    void basicValidScenario(){
        List<String[]> flights = new ArrayList<>();
        flights.add(new String[]{"SOF", "IST", "4"});
        flights.add(new String[]{"IST", "NYC", "4"});
        flights.add(new String[]{"NYC", "MEX", "4"});
        String origin = "SOF";
        String destination = "MEX";
        int groupSize = 4;

        assertEquals(3,travelTime.flightsMap(flights,origin,destination,Integer.toString(groupSize)));
    }

    // Test case for unreachable destination
    @Test
    void unreachableDestination(){
        List<String[]> flights = new ArrayList<>();
        flights.add(new String[]{"SOF", "MEX", "3"});
        flights.add(new String[]{"IST", "NYC", "3"});
        String origin = "SOF";
        String destination = "NYC";
        int groupSize = 3;

        assertEquals(0,travelTime.flightsMap(flights,origin,destination,Integer.toString(groupSize)));
    }

    // Test case for single flight available
    @Test
    void singleFlightScenario(){
        List<String[]> flights = new ArrayList<>();
        flights.add(new String[]{"SOF", "MEX","5"});
        String origin = "SOF";
        String destination = "MEX";
        int groupSize = 2;

        assertEquals(1,travelTime.flightsMap(flights,origin,destination,Integer.toString(groupSize)));
    }

    // Test Case for large amount of flights
    @Test
    void largeListScenario(){
        List<String[]> flights = new ArrayList<>();
        flights.add(new String[]{"SOF", "IST", "5"});
        flights.add(new String[]{"IST", "NYC", "5"});
        flights.add(new String[]{"IST", "MEX", "2"});
        flights.add(new String[]{"MEX", "IST", "2"});
        flights.add(new String[]{"MEX", "NYC", "2"});
        flights.add(new String[]{"VAR", "MEX", "2"});
        flights.add(new String[]{"VAR", "IST", "2"});
        flights.add(new String[]{"VAR", "NYC", "2"});
        flights.add(new String[]{"SWE", "MEX", "2"});
        flights.add(new String[]{"SOF", "SWE", "5"});
        flights.add(new String[]{"IST", "SWE", "2"});
        flights.add(new String[]{"NYC", "SWE", "5"});
        flights.add(new String[]{"NYC", "SWE", "2"});
        flights.add(new String[]{"SWE", "POL", "5"});
        flights.add(new String[]{"SWE", "POL", "1"});
        String origin = "SOF";
        String destination = "POL";
        int groupSize = 4;

        assertEquals(2,travelTime.flightsMap(flights,origin,destination,Integer.toString(groupSize)));
    }

    // Test case for a circular route scenario available
    @Test
    void circularRouteScenario(){
        List<String[]> flights = new ArrayList<>();
        flights.add(new String[]{"SOF", "IST", "4"});
        flights.add(new String[]{"IST", "NYC", "4"});
        flights.add(new String[]{"NYC", "SOF", "4"});
        String origin = "SOF";
        String destination = "NYC";
        int groupSize = 4;

        assertEquals(2,travelTime.flightsMap(flights,origin,destination,Integer.toString(groupSize)));
    }


    // Test case for no flights available
    @Test
    void noFlightsInput(){
        List<String[]> flights = new ArrayList<>();
        String origin = "SOF";
        String destination = "NYC";
        int groupSize = 4;

        assertEquals(0,travelTime.flightsMap(flights,origin,destination,Integer.toString(groupSize)));
    }

    // Test case for no origin input
    @Test
    void noOriginInput(){
        List<String[]> flights = new ArrayList<>();
        flights.add(new String[]{"IST","SOF", "4"});
        flights.add(new String[]{"SOF","NYC", "4"});
        String origin = "";
        String destination = "NYC";
        int groupSize = 4;

        assertEquals(0,travelTime.flightsMap(flights,origin,destination,Integer.toString(groupSize)));
    }

    // Test case for no holiday destination
    @Test
    void noDestinationInput(){
        List<String[]> flights = new ArrayList<>();
        flights.add(new String[]{"IST","SOF", "4"});
        flights.add(new String[]{"SOF","NYC", "4"});
        String origin = "IST";
        String destination = "";
        int groupSize = 4;

        assertEquals(0,travelTime.flightsMap(flights,origin,destination,Integer.toString(groupSize)));
    }

    // Test case for invalid format input
    @Test
    void invalidInput() {
        List<String[]> flights = new ArrayList<>();
        flights.add(new String[]{"SOF", "NYC", "4"});
        flights.add(new String[]{"NYC"});
        String origin = "SOF";
        String destination = "SWE";
        int groupSize = 4;

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {travelTime.flightsMap(flights, origin, destination, Integer.toString(groupSize));});
    }

    // Test case for invalid group size input
    @Test
    void invalidGroupSize() {
        List<String[]> flights = new ArrayList<>();
        flights.add(new String[]{"SOF", "NYC", "4"});
        flights.add(new String[]{"NYC", "SWE", "4"});
        String origin = "SOF";
        String destination = "SWE";
        String invalidGroupSize = "invalid";

        assertThrows(NumberFormatException.class, () -> {travelTime.flightsMap(flights, origin, destination, String.valueOf(Integer.parseInt(invalidGroupSize)));});
    }
}
