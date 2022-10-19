package cinema.ErrorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionsHandler {

    @ExceptionHandler(OutOfBoundsError.class)
    public ResponseEntity<Object> handleOutOfBoundsException() {
        ApiOutOfBounds apiOutOfBounds = new ApiOutOfBounds(HttpStatus.BAD_REQUEST.value(), "The number of a row or a column is out of bounds!");
        return new ResponseEntity<>(apiOutOfBounds, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SeatAlreadyBooked.class)
    public ResponseEntity<Object> handleSeatAlreadyBooked(){
        ApiOutOfBounds seatBooked = new ApiOutOfBounds(HttpStatus.BAD_REQUEST.value(), "The ticket has been already purchased!");
        return new ResponseEntity<>(seatBooked, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidToken.class)
    public ResponseEntity<Object> handleWrongToken(){
        ApiOutOfBounds invalidToken = new ApiOutOfBounds(HttpStatus.BAD_REQUEST.value(), "Wrong token!");
        return new ResponseEntity<>(invalidToken, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SecretPasswordError.class)
    public ResponseEntity<Object> handleWrongPassword(){
        ApiOutOfBounds invalidToken = new ApiOutOfBounds(HttpStatus.UNAUTHORIZED.value(), "The password is wrong!");
        return new ResponseEntity<>(invalidToken, HttpStatus.UNAUTHORIZED);
    }


}
