import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class main {

    static Container_Flights flight_list = new Container_Flights();
    static Container_Reservation reservation_list = new Container_Reservation();

    public static void main(String[] arg) throws IOException {



        try {
            menu();
        } catch (incorrectInput | FlightOverBooked | NotFound e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return;

    }

    public static LinkedList<Passenger> inputPassengerDetails() throws incorrectInput {

        Scanner in = new Scanner(System.in);

        LinkedList<Passenger> passenger_list = new LinkedList<Passenger>();
        System.out.println("Enter the number of passengers");
        int num_passengers = in.nextInt();

        for (int i = 0; i < num_passengers; i++) {
            System.out.println("Enter the name of passenger " + (i + 1));
            String name = in.next();
            System.out.println("Enter the age of passenger " + (i + 1));
            int age = in.nextInt();
            System.out.println("Enter \"m\" for male AND \"F\" for female ");
            String gender = in.next();
            char gender_c;
            if (gender == "m" || gender == "M") {
                gender_c = 'm';
            } else if (gender == "f" || gender == "F") {
                gender_c = 'f';
            } else {
                throw new incorrectInput("Only accepts M or F");
            }

            passenger_list.add(new Passenger(name, age, gender_c));

        }

        return passenger_list;

    }

    public static void bookFlight() throws incorrectInput, FlightOverBooked, NotFound {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter Flightnuber of flight you want to book");
        String flight_number = in.next();
        LinkedList<Passenger> list_pax = inputPassengerDetails();

        addReservation(flight_number, list_pax.size(), list_pax);

    }

    public static void menu() throws incorrectInput, FlightOverBooked, NotFound {

        boolean exit = false;
        while (!exit) {
            Scanner in = new Scanner(System.in);

            System.out.println("Press 1 To View Flights");
            System.out.println("Press 2 To Make a Reservation");
            System.out.println("Press 3 To view your reservation");
            System.out.println("Press 4 to do admin tasks");
            System.out.println("Press 5 to view all flights");

            System.out.println("Press 0 To exit");

            int choice = in.nextInt();

            switch (choice) {
            case 1:
                searchFlight();
                break;
            case 2:
                bookFlight();
                break;
            case 3:
                viewReservation();
                break;
            case 4:
                adminMenu();
                break;
            case 5:
                flight_list.printAllFlights();
                break;

            case 0:
                exit = true;
                break;

            }
            in.close();
        }
    }

    static public void adminMenu() {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = in.next();
        System.out.println("Enter password: ");
        String password = in.next();

        boolean flag = true;
        while (flag) {
            System.out.println("Press 1 to Add a flight");
            System.out.println("Press 0 to go back");

            int choice = in.nextInt();
            switch (choice) {
            case 1:
                addFlight();
                break;
            case 0:
                flag = false;
                break;
            }
        }
        in.close();

    }

    private static void addFlight() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Flight Number: ");
        String flight_number = in.next();
        System.out.println("Enter Departure Airport:");
        String departure_airport = in.next();
        System.out.println("Enter Arrival Airport:");
        String arrival_airport = in.next();

        System.out.println("Enter Plane Type");
        String plane = in.next();

        System.out.println("Enter Price per seat:");
        float price = in.nextFloat();

        System.out.println("Enter Capacity:");
        int capacity = in.nextInt();

        System.out.println("Enter Departure Day:");
        int departure_day = in.nextInt();
        System.out.println("Enter Departure Month:");
        int departure_month = in.nextInt();
        System.out.println("Enter Departure Year:");
        int departure_year = in.nextInt();
        System.out.println("Enter Departure Hour");
        int departure_hour = in.nextInt();
        System.out.println("Enter Departure Minute");
        int departure_minute = in.nextInt();

        Date departure_time = new Date(departure_year, departure_month, departure_day, departure_hour,
                departure_minute);

        System.out.println("Enter Arrival Day:");
        int arrival_day = in.nextInt();
        System.out.println("Enter Arrival Month:");
        int arrival_month = in.nextInt();
        System.out.println("Enter Arrival Year:");
        int arrival_year = in.nextInt();
        System.out.println("Enter Arrival Hour");
        int arrival_hour = in.nextInt();
        System.out.println("Enter Arrival Minute");
        int arrival_minute = in.nextInt();
        Date arrival_time = new Date(arrival_year, arrival_month, arrival_day, arrival_hour, arrival_minute);

        flight_list.addFlight(new Flight(flight_number, departure_airport, arrival_airport, plane, capacity, 0, price,
                departure_time, arrival_time));

    }

    public static void viewReservation() throws NotFound {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your reservation number");
        String reservation_number = in.next();
        Reservation reservation = reservation_list.searchReservation(reservation_number);
        reservation.printReservation();
    }

    public static void addReservation(String flight_number, int tickets, LinkedList<Passenger> passenger_list)
            throws FlightOverBooked, NotFound {
        flight_list.searchFlight(flight_number).bookTickets(tickets);
        Reservation new_reservation = new Reservation(generateBookingNumber(), passenger_list, flight_number);
        reservation_list.addReservation(new_reservation);

        System.out.println("Reservation Successful");
        System.out.println("Your booking number is " + new_reservation.getReservationId());

    }

    // generates random string of capital letters, small letters and numbers of
    // length 8
    public static String generateBookingNumber() {
        StringBuilder sb = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = characters.length();
        for (int i = 0; i < 8; i++) {
            sb.append(characters.charAt((int) (Math.random() * length)));
        }
        return sb.toString();
    }

    public static void searchFlight() {
        // Reads input from user for how to serach for flight then searches for flight
        // based on input
        Scanner in = new Scanner(System.in);
        System.out.println("Press 1 to search by Flight Number");
        System.out.println("Press 2 to search by departure and arrival airport");

        int option;
        option = in.nextInt();
        option = 1;

        switch (option) {
        case 1:
            // Search by flight number
            // enter flight num
            System.out.println("Enter Flight Number");
            String flt_num;
            flt_num = "EK2444";
            flt_num = in.next();
            LinkedList<Flight> search_list_flightnum = flight_list.searchByFlightNum(flt_num);
            break;
        case 2:
            // Search by Departure and Arrival
            String dep;
            String arr;
            System.out.println("Enter Departure Airport");
            dep = in.nextLine();
            System.out.println("Enter Arrival Airport");
            arr = in.nextLine();

            LinkedList<Flight> search_list_depart = flight_list.searchByDepartureArrival(dep, arr);

            break;

        default:
            System.out.println("Invalid input");
            break;
        }

    }
}
