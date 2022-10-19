package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CinemaRoom {

    public final int totalRows = 9;
    public final int totalColumns = 9;
    private final List<RowsAndColumns> availableSeats = new ArrayList<>();
    private List<TicketWithToken> ticketWithTokenList = new ArrayList<>();
    @JsonIgnore
    protected List<TicketWithToken> getTicketWithTokenList() {
        return ticketWithTokenList;
    }

    public void setTicketWithTokenList(List<TicketWithToken> ticketWithTokenList) {
        this.ticketWithTokenList = ticketWithTokenList;
    }

    public List<RowsAndColumns> getAvailableSeats() {
        return availableSeats;
    }

    public CinemaRoom() {

        populateSeatList();
        populateTokenList();
    }

    private void populateSeatList () {
        int price;
        for (int row = 1; row < totalRows + 1; row++ ){
            for (int column = 1; column < totalColumns + 1; column++) {
                if (row <= 4) {
                     price = 10;
                } else {
                    price = 8;
                }
                availableSeats.add(new RowsAndColumns(row, column, price));
            }
        }
    }

    private void populateTokenList() {
        for (RowsAndColumns availableSeat : availableSeats) {
            TicketWithToken ticketWithToken = new TicketWithToken(availableSeat);
            ticketWithTokenList.add(ticketWithToken);
        }
    }



}
