public class InvalidTicketDataException extends Exception{

    public InvalidTicketDataException(int lineNumber, String message) {
        super("Invalid ticket data at line " + lineNumber + ": " + message);
    }
}