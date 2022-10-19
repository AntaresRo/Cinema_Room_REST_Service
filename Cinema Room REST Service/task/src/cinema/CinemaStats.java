package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CinemaStats {

    //CinemaRoom cinemaRoom = new CinemaRoom();
    private int currentIncome = 0;
    private int numberOfAvailableSeats = 81;
    private int numberOfPurchasedTickets = 0;
    @JsonIgnore
    private final String password = "super_secret";

    public CinemaStats() {

    }

    public int getCurrentIncome() {
        return currentIncome;
    }


    public String getPassword() {
        return password;
    }

    public void setCurrentIncome(int currentIncome) {
        this.currentIncome = currentIncome;
    }

    public int getNumberOfAvailableSeats() {
        return numberOfAvailableSeats;
    }

    public void setNumberOfAvailableSeats(int numberOfAvailableSeats) {
        this.numberOfAvailableSeats = numberOfAvailableSeats;
    }

    public int getNumberOfPurchasedTickets() {
        return numberOfPurchasedTickets;
    }

    public void setNumberOfPurchasedTickets(int numberOfPurchasedTickets) {
        this.numberOfPurchasedTickets = numberOfPurchasedTickets;
    }
    protected void calculateStats(CinemaRoom cinemaRoom, int i) {
                currentIncome += cinemaRoom.getAvailableSeats().get(i).getPrice();
                numberOfAvailableSeats--;
                numberOfPurchasedTickets++;

    }

    protected void modifyStatsAfterTicketReturn(CinemaRoom cinemaRoom, int i) {
        currentIncome -= cinemaRoom.getAvailableSeats().get(i).getPrice();
        numberOfAvailableSeats++;
        numberOfPurchasedTickets--;
    }

}
