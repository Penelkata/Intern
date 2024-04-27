import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class InternTaskTest {

    InternTask travelTime = new InternTask();

    // Test case for a simple scenario
    @Test
    void basicValidScenario(){
        List<String[]> flights = new ArrayList<>();
        flights.add(new String[]{"SOF", "IST"});
        flights.add(new String[]{"IST", "NYC"});
        flights.add(new String[]{"NYC", "MEX"});
        String origin = "SOF";
        String destination = "MEX";

        assertEquals(3,travelTime.flightsMap(flights,origin,destination));
    }

    // Test case for unreachable destination
    @Test
    void unreachableDestination(){
        List<String[]> flights = new ArrayList<>();
        flights.add(new String[]{"SOF", "MEX"});
        flights.add(new String[]{"IST", "NYC"});
        String origin = "SOF";
        String destination = "NYC";

        assertEquals(0,travelTime.flightsMap(flights,origin,destination));
    }

    // Test case for single flight available
    @Test
    void singleFlightScenario(){
        List<String[]> flights = new ArrayList<>();
        flights.add(new String[]{"SOF", "MEX"});
        String origin = "SOF";
        String destination = "MEX";

        assertEquals(1,travelTime.flightsMap(flights,origin,destination));
    }

    // Test Case for large amount of flights
    @Test
    void largeListScenario(){
        List<String[]> flights = new ArrayList<>();
        flights.add(new String[]{"SOF", "IST"});
        flights.add(new String[]{"IST", "NYC"});
        flights.add(new String[]{"IST", "MEX"});
        flights.add(new String[]{"MEX", "IST"});
        flights.add(new String[]{"MEX", "NYC"});
        flights.add(new String[]{"VAR", "MEX"});
        flights.add(new String[]{"VAR", "IST"});
        flights.add(new String[]{"VAR", "NYC"});
        flights.add(new String[]{"SWE", "MEX"});
        flights.add(new String[]{"SOF", "SWE"});
        flights.add(new String[]{"IST", "SWE"});
        flights.add(new String[]{"NYC", "SWE"});
        flights.add(new String[]{"NYC", "SWE"});
        flights.add(new String[]{"SWE", "POL"});
        String origin = "SOF";
        String destination = "POL";

        assertEquals(2,travelTime.flightsMap(flights,origin,destination));
    }

    // Test case for a circular route scenario available
    @Test
    void circularRouteScenario(){
        List<String[]> flights = new ArrayList<>();
        flights.add(new String[]{"SOF", "IST"});
        flights.add(new String[]{"IST", "NYC"});
        flights.add(new String[]{"NYC", "SOF"});
        String origin = "SOF";
        String destination = "NYC";

        assertEquals(2,travelTime.flightsMap(flights,origin,destination));
    }


    // Test case for no flights available
    @Test
    void noFlightsInput(){
        List<String[]> flights = new ArrayList<>();
        String origin = "SOF";
        String destination = "NYC";

        assertEquals(0,travelTime.flightsMap(flights,origin,destination));
    }

    // Test case for no origin input
    @Test
    void noOriginInput(){
        List<String[]> flights = new ArrayList<>();
        flights.add(new String[]{"IST","SOF"});
        flights.add(new String[]{"SOF","NYC"});
        String origin = "";
        String destination = "NYC";

        assertEquals(0,travelTime.flightsMap(flights,origin,destination));
    }

    // Test case for no holiday destination
    @Test
    void noDestinationInput(){
        List<String[]> flights = new ArrayList<>();
        flights.add(new String[]{"IST","SOF"});
        flights.add(new String[]{"SOF","NYC"});
        String origin = "IST";
        String destination = "";

        assertEquals(0,travelTime.flightsMap(flights,origin,destination));
    }

    // Test case for invalid input
    @Test
    void invalidInput() {
        List<String[]> flights = new ArrayList<>();
        flights.add(new String[]{"SOF","NYC"});
        flights.add(new String[]{"NYC"});
        String origin = "SOF";
        String destination = "SWE";

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> InternTask.flightsMap(flights, origin, destination));
    }
}