import java.util.*;

public class InternTaskExtra {
    public static int flightsMap(List<String[]> flights, String origin, String destination, String group) {

        // Create a graph representation of the flights using Hashmap
        Map<String, List<String>> graph = new HashMap<>();

        // Iterate through each flight and add it to the graph.
        for (String[] flight : flights) {
            String departure = flight[0];
            String arrival = flight[1];
            String capacity = flight[2];
            graph.putIfAbsent(departure, new ArrayList<>());
            graph.get(departure).add(arrival);
        }

        // Perform Breadth-First Search to find the shortest travel distance
        Queue<String[]> queue = new LinkedList<>();
        queue.offer(new String[]{origin, "0"});
        HashSet<String> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            String[] current = queue.poll();
            String currentFlight = current[0];
            int timeSoFar = Integer.parseInt(current[1]);
            if (currentFlight.equals(destination)) {
                return timeSoFar;
            }
            if (!visited.contains(currentFlight)) {
                visited.add(currentFlight);

                // Get the other flights and add them to the queue
                List<String> otherFlights = graph.getOrDefault(currentFlight, new ArrayList<>());
                for (String diffFlight : otherFlights) {
                    queue.offer(new String[]{diffFlight, String.valueOf(timeSoFar + 1)});
                }
            }
        }

        // If unreachable, return 0 (Subtask 1)
        return 0;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Create a list to store the flights
        List<String[]> flights = new ArrayList<>();

        // Wrapping the code in try-catch block to catch the Exception
        try {
            System.out.println("Flights:");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Exit the loop when an empty line is encountered
                if (line.isEmpty()) {
                    break;
                }

                // Read each line and split it based on (",")
                String[] flight = line.split(",");

                if (flight.length != 3) {
                    throw new IllegalArgumentException("Correct format is \"Departure,Arrival,Capacity\"");
                }

                flights.add(flight);
            }

            System.out.println("Origin:");
            String origin = scanner.nextLine();
            System.out.println("Destination:");
            String destination = scanner.nextLine();
            System.out.println("Group size:");
            int groupSize = Integer.parseInt(scanner.nextLine());

            // Remove the flight from the list if the group exceeds the capacity
            for (int i = flights.size() - 1; i >= 0; i--) {
                String[] flight = flights.get(i);
                int capacity = Integer.parseInt(flight[2]);

                if (capacity < groupSize) {
                    flights.remove(i);
                }
            }

            // Calculate and display result
            System.out.println(flightsMap(flights, origin, destination, Integer.toString(groupSize)));
        } catch (NumberFormatException e) {
            System.out.println("Invalid group size format. Please enter a valid integer.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input format: " + e.getMessage());
        }

        // Ensure it closes properly even if an exception occurs
        finally {
            scanner.close();
        }
    }
}
