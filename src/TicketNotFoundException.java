public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(int id) {
        super("Ticket not found: " + id);
    }
}