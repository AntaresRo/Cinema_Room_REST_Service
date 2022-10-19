package cinema;

public class TicketToReturn {
    private RowsAndColumns returnedTicket;

    public RowsAndColumns getReturnedTicket() {
        return returnedTicket;
    }

    public void setReturnedTicket(RowsAndColumns returnedTicket) {
        this.returnedTicket = returnedTicket;
    }
    public TicketToReturn() {

    }

    public TicketToReturn(RowsAndColumns returnedTicket) {
        this.returnedTicket = returnedTicket;
    }
}
