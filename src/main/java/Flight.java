import java.nio.ByteBuffer;
import java.util.Date;

public class Flight {
    String flight_num;
    String departure;
    String arrival;
    String plane;
    int capacity;
    int booked_tickets;
    float seat_price;
    Date departure_time;
    Date arrival_time;

    public Flight() {
        flight_num = new String();
        departure = new String();
        arrival = new String();
        plane = new String();
        capacity = 0;
        booked_tickets = 0;
        seat_price = 0;
        departure_time = new Date();
        arrival_time = new Date();
    }

    public Flight(String flt_num) {

        flight_num = flt_num;
        departure = "London";
        arrival = "Paris";
        plane = "Boeing 747";
        capacity = 200;
        booked_tickets = 100;
        seat_price = 1000;
        departure_time = new Date(2021, 10, 24, 18, 00);
        arrival_time = new Date(2021, 10, 24, 22, 00);
    }

    public Flight(String flight_num, String departure, String arrival, String plane, int capacity, int booked_tickets,
            float seat_price, Date departure_time, Date arrival_time) {
        this.flight_num = flight_num;
        this.departure = departure;
        this.arrival = arrival;
        this.plane = plane;
        this.capacity = capacity;
        this.booked_tickets = booked_tickets;
        this.seat_price = seat_price;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
    }

    public Flight(String flight_num, String departure, String arrival, String plane, String departure_time,
            String arrival_time, String seat_price, String capacity, String booked_tickets) {

        this.flight_num = flight_num;
        this.departure = departure;
        this.arrival = arrival;
        this.plane = plane;
        System.out.println("capacity: " + capacity);
        this.capacity = Integer.parseInt(capacity);
        this.booked_tickets = Integer.parseInt(booked_tickets);
        this.seat_price = Float.parseFloat(seat_price);
        this.departure_time = new Date(Long.parseLong(departure_time));
        this.arrival_time = new Date(Long.parseLong(arrival_time));

    }

    public int seatsAvailable() {
        return capacity - booked_tickets;
    }

    public void bookTickets(int tickets) throws FlightOverBooked {
        if (seatsAvailable() < tickets) {
            throw new FlightOverBooked("Seats Not Available");
        } else {
            booked_tickets += tickets;

            if (seatsAvailable() < 0) {
                throw new FlightOverBooked("Something Went Wrong");
            }

        }
    }

    public float calculatePrice(int tickets) {
        return (float) (tickets * seat_price);
    }

    public float getSeatPrice() {
        return seat_price;
    }

    public int getBookedTickets() {
        return booked_tickets;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getDepartureAirport() {
        return departure;
    }

    public String getArrivalAirport() {
        return arrival;
    }

    public Date getDepartureTime() {
        return departure_time;
    }

    public Date getArrivalTime() {
        return arrival_time;
    }

    public String getPlane() {
        return plane;
    }

    public String getFlightNumber() {
        return flight_num;
    }

    public void readData(ByteBuffer buffer) {

        char check = buffer.getChar();

        while (check != ',') {
            flight_num += check;
            check = buffer.getChar();
        }

        check = buffer.getChar();
        while (check != ',') {
            departure += check;
            check = buffer.getChar();
        }

        check = buffer.getChar();
        while (check != ',') {
            arrival += check;
            check = buffer.getChar();
        }

        check = buffer.getChar();
        while (check != ',') {
            plane += check;
            check = buffer.getChar();
        }

        departure_time = new Date(buffer.getLong());
        arrival_time = new Date(buffer.getLong());
        seat_price = buffer.getFloat();
        capacity = buffer.getInt();
        booked_tickets = buffer.getInt();

    }

    public void writeData(ByteBuffer write) {
        write.put(flight_num.getBytes());
        write.put(",".getBytes());

        write.put(departure.getBytes());
        write.put(",".getBytes());

        write.put(arrival.getBytes());
        write.put(",".getBytes());

        write.put(plane.getBytes());
        write.put(",".getBytes());

        long DT = departure_time.getTime();
        write.put(String.valueOf(DT).getBytes());
        write.put(",".getBytes());

        DT = arrival_time.getTime();
        write.put(String.valueOf(DT).getBytes());
        write.put(",".getBytes());

        write.put(String.valueOf(seat_price).getBytes());
        write.put(",".getBytes());

        write.put(String.valueOf(capacity).getBytes());
        write.put(",".getBytes());

        write.put(String.valueOf(booked_tickets).getBytes());

        write.put("\n".getBytes());

    }

    public void printDetails() {
        System.out.println("Flight Number: " + flight_num);
        System.out.println("Departure Airport: " + departure);
        System.out.println("Arrival Airport: " + arrival);
        System.out.println("Plane: " + plane);
        System.out.println("Departure Time: " + departure_time);
        System.out.println("Arrival Time: " + arrival_time);
        System.out.println("Seat Price: " + seat_price);
        System.out.println("Capacity: " + capacity);
        System.out.println("Booked Tickets: " + booked_tickets);

    }

    public boolean search_flightnum(String flight) {
        if (flight_num.equals(flight)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean search_departure(String flight) {
        if (departure.equals(flight)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean search_arrival(String flight) {
        if (arrival.equals(flight)) {
            return true;
        } else {
            return false;
        }

    }
}
