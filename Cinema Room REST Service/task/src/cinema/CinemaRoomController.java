package cinema;

import cinema.ErrorHandling.InvalidToken;
import cinema.ErrorHandling.OutOfBoundsError;
import cinema.ErrorHandling.SeatAlreadyBooked;
import cinema.ErrorHandling.SecretPasswordError;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CinemaRoomController {
    Gson gson = new Gson();
    @Autowired
    CinemaRoom cinemaRoom;
    @Autowired
    RowsAndColumns rowsAndColumns;
    CinemaStats cinemaStats = new CinemaStats();


    @GetMapping("/seats")
    public CinemaRoom cinemaRoomData() {
        //cinemaRoom.populateSeatList();
        return cinemaRoom;
    }


    private TicketWithToken validateSeat(PickedSeat pickedSeat) {
        TicketWithToken ticketWithToken = null;
        RowsAndColumns seatToBook = null;
        if ((pickedSeat.getRow() < 0) || (pickedSeat.getColumn() < 0) || (pickedSeat.getRow() > 9) || (pickedSeat.getColumn() > 9)) {
            throw new OutOfBoundsError("etc");
        } else {
            for (int i = 0; i < cinemaRoom.getAvailableSeats().size(); i ++) {
                rowsAndColumns = cinemaRoom.getAvailableSeats().get(i);
                if ((rowsAndColumns.getRow() == pickedSeat.getRow()) && (rowsAndColumns.getColumn() == pickedSeat.getColumn() )){
                    if (rowsAndColumns.isBooked()) {
                        throw new SeatAlreadyBooked("etc");
                    } else {
                        rowsAndColumns.setBooked(true);
                        seatToBook = rowsAndColumns;
                        UUID token = UUID.randomUUID();
                        ticketWithToken = new TicketWithToken(token, seatToBook);
                        //cinemaRoom.getTicketWithTokenList()
                        for (int k = 0; k < cinemaRoom.getTicketWithTokenList().size(); k++){
                            TicketWithToken ticketWithToken1 = cinemaRoom.getTicketWithTokenList().get(k);
                            if (ticketWithToken1.getTicket() == seatToBook) {
                                cinemaRoom.getTicketWithTokenList().get(k).setToken(token);
                            }
                        }
                        cinemaStats.calculateStats(cinemaRoom, i);
                    }
                }
            }
        }

        return  ticketWithToken;
    }

    @PostMapping("/purchase")
    public TicketWithToken bookSeats(@RequestBody PickedSeat pickedSeat) {

        return validateSeat(pickedSeat);
    }


    private TicketToReturn returnTicket(TicketWithToken token) {
        TicketToReturn returnedTicket = null;
        boolean tokenExists = false;
        for (int i = 0; i < cinemaRoom.getTicketWithTokenList().size(); i++) {

            UUID compare = cinemaRoom.getTicketWithTokenList().get(i).getToken();
            UUID input = token.getToken();
            if (input.equals(compare)){
                returnedTicket = new TicketToReturn(cinemaRoom.getTicketWithTokenList().get(i).getTicket());
                cinemaRoom.getAvailableSeats().get(i).setBooked(false);
                tokenExists = true;
                cinemaStats.modifyStatsAfterTicketReturn(cinemaRoom, i);
                break;
            }

        }
        if (!tokenExists) {
            throw new InvalidToken();
        }
        return returnedTicket;
    }

    @PostMapping("/return")
    public TicketToReturn returnTicketEndPoint(@RequestBody TicketWithToken token){

        return returnTicket(token);
    }

    @PostMapping("/stats")
    public CinemaStats getCinemaStats(@RequestParam("password") Optional<String> password) {

        if (password.isEmpty()) {
            throw new SecretPasswordError();
        } else if (!(password.equals(Optional.of(cinemaStats.getPassword())))) {
            throw new SecretPasswordError();
        }
        return cinemaStats;
    }
}
