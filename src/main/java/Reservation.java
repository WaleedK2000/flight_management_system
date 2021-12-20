import java.nio.ByteBuffer;
import java.util.LinkedList;

public class Reservation {
    String reservation_id;
    LinkedList<Passenger> passengers;
    String flight_num;

    public Reservation(String revid, LinkedList<Passenger> passengers, String flight_num) {
        this.reservation_id = revid;
        this.passengers = passengers;
        this.flight_num = flight_num;
    }

    public Reservation(String revid, String flight_num) {
        this.reservation_id = revid;
        this.passengers = new LinkedList<Passenger>();
        this.flight_num = flight_num;
    }

    public String getReservationId() {
        return reservation_id;
    }

    public String getFlightNumber() {
        return flight_num;
    }

    public LinkedList<Passenger> getPassengerList() {
        return passengers;
    }

    public void writeData(ByteBuffer write) {
        write.put(reservation_id.getBytes());
        write.put(",".getBytes());

        write.put(flight_num.getBytes());
        write.put(",".getBytes());

        write.put(String.valueOf(passengers.size()).getBytes());
        write.put(",".getBytes());

        for (int i = 0; i < passengers.size(); i++) {

            passengers.get(i).writeData(write);

            if (i != passengers.size() - 1) {
                write.put(",".getBytes());
            }
        }
        write.put("\n".getBytes());
    }

    public void addPassenger(Passenger p) {
        passengers.add(p);
    }

    public void printReservation() {
        System.out.println("Reservation ID: " + reservation_id);
        System.out.println("Flight Number: " + flight_num);
        System.out.println("Passengers: ");
        for (Passenger p : passengers) {
            p.printPassengerData();
        }

    }

}
