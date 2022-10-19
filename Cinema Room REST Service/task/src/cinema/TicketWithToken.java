package cinema;

import io.micrometer.core.lang.Nullable;

import java.util.UUID;

public class TicketWithToken {
    @Nullable
    private UUID token;

    public TicketWithToken() {
    }

    public TicketWithToken(UUID token) {
        this.token = token;
    }

    @Nullable
    private RowsAndColumns ticket;

    public UUID getToken() {
        return token;
    }

    public TicketWithToken(RowsAndColumns ticket) {

        this.ticket = ticket;
    }

    public TicketWithToken(UUID token, RowsAndColumns ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public RowsAndColumns getTicket() {
        return ticket;
    }

    public void setTicket(RowsAndColumns ticket) {
        this.ticket = ticket;
    }
}
