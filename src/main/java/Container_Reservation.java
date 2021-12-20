import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Container_Reservation {
    private LinkedList<Reservation> reservations;

    public Container_Reservation() {
        reservations = new LinkedList<Reservation>();
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public Reservation searchReservation(String reservation_id) throws NotFound {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId().equals(reservation_id)) {
                return reservation;
            }

        }
        throw new NotFound("Reservation Not Found");
    }

    public void readDataFromFile() throws IOException {
        Stream<String> stream = Files.lines(Paths.get("dat_reservation.dat"));
        stream.forEach(line -> {
            String[] data = line.split(",");
            Reservation reservation = new Reservation(data[0], data[1]);

            int total_passengers = Integer.parseInt(data[2]);

            for (int i = 0; i < total_passengers; ++i) {

                String[] passenger_data = data[i + 3].split(";");

                Passenger passenger = new Passenger(passenger_data[0], passenger_data[1], passenger_data[2]);
                reservation.addPassenger(passenger);

            }

            reservations.add(reservation);
        });
        stream.close();
    }

    public void writeDataToFile() throws IOException {
        FileOutputStream file_out = new FileOutputStream("dat_reservation.dat");
        FileChannel write_channel = file_out.getChannel();
        ByteBuffer write_buffer = ByteBuffer.allocate(1024);

        for (Reservation reservation : reservations) {
            write_buffer.clear();
            reservation.writeData(write_buffer);
            write_buffer.flip();
            write_channel.write(write_buffer);
        }

        file_out.close();
    }

    public void printAllReservations() {
        for (Reservation reservation : reservations) {
            reservation.printReservation();
        }
    }

}
