class FlightOverBooked extends Exception {
    public FlightOverBooked(String err) {
        super(err);
    }
}

class NotFound extends Exception {
    public NotFound(String err) {
        super(err);
    }
}

class incorrectInput extends Exception {
    public incorrectInput(String err) {
        super(err);
    }
}