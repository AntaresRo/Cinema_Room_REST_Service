package cinema.ErrorHandling;

public class OutOfBoundsError extends RuntimeException{
    public OutOfBoundsError(String message) {
        super(message);
    }
}
