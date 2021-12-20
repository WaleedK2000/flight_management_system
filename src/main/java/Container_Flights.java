import java.util.LinkedList;
import java.util.stream.Stream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.ByteBuffer;

public class Container_Flights {

    private LinkedList<Flight> flights;

    public Container_Flights() {
        flights = new LinkedList<>();
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void removeFlight(Flight flight) {
        flights.remove(flight);
    }

    public Flight searchFlight(String flightNumber) throws NotFound {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        throw new NotFound("Flight Not Found");
    }

    // Reads data from file and adds it to linkedList
    public void readDataFromFile() throws IOException {

        Stream<String> lines = Files.lines(Paths.get("dat_flight.dat"));
        lines.forEach(line -> {
            String[] flightData = line.split(",");

            flights.add(new Flight(flightData[0], flightData[1], flightData[2], flightData[3], flightData[4],
                    flightData[5], flightData[6], flightData[7], flightData[8]));
        });

        lines.close();

    }

    public void writeDataToFile() throws IOException {
        FileOutputStream file_out = new FileOutputStream("dat_flight.dat");
        FileChannel writeChannel = file_out.getChannel();
        ByteBuffer write = ByteBuffer.allocate(1024);

        for (Flight flight : flights) {
            write.clear();
            flight.writeData(write);
            write.flip();
            writeChannel.write(write);

        }
        file_out.close();
    }

    public void printAllFlights() {
        for (Flight flight : flights) {
            flight.printDetails();
        }
    }

    public LinkedList<Flight> searchByFlightNum(String flight_num) {
        LinkedList<Flight> searchResults = new LinkedList<>();
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flight_num)) {
                searchResults.add(flight);
            }
        }
        return searchResults;

    }

    public LinkedList<Flight> searchByDepartureArrival(String departure, String arrival) {
        LinkedList<Flight> searchResults = new LinkedList<>();
        for (Flight flight : flights) {
            if (flight.getDepartureAirport().equals(departure) && flight.getArrivalAirport().equals(arrival)) {
                searchResults.add(flight);
            }
        }

        return searchResults;
    }

}
