package cinema.ErrorHandling;

public class InvalidToken extends RuntimeException{
    public InvalidToken() {
    }

    public InvalidToken(String message) {
        super(message);
    }
}
