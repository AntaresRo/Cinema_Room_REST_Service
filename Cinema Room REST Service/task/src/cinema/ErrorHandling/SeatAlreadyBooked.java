package cinema.ErrorHandling;

public class SeatAlreadyBooked extends RuntimeException{
    public SeatAlreadyBooked(String message) {
        super(message);
    }
}
