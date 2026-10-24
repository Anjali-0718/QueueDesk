import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            BugTicket bug = new BugTicket(
                    "Login not working",
                    "User cannot login",
                    "Anjali",
                    Priority.CRITICAL,
                    Severity.HIGH,
                    "Enter username and password"
            );

            FeatureRequestTicket feature = new FeatureRequestTicket(
                    "Dark mode",
                    "Add dark mode",
                    "Rahul",
                    Priority.MEDIUM,
                    10,
                    "Better user experience"
            );

            AccessRequestTicket access = new AccessRequestTicket(
                    "Database access",
                    "Need database access",
                    "Priya",
                    Priority.HIGH,
                    "Production DB",
                    "Read"
            );

            Repository<Ticket> repository = new Repository<>();

            repository.add(bug);
            repository.add(feature);
            repository.add(access);

            System.out.println("Total tickets: " + Ticket.totalCreated());

            System.out.println("\nTriage Board:");
            TriageBoard.printTriageBoard(repository.all());

            System.out.println("\nFinding ticket:");
            System.out.println(repository.findById(bug.getId()));

            System.out.println("\nTriage Queue:");
            TriageQueue.serveTickets(repository);

            System.out.println("\nOldest ticket:");
            System.out.println(TriageQueue.oldest(repository.all()));

            System.out.println("\nLoading tickets.txt:");

            List<Ticket> loadedTickets =
                    TicketFileLoader.load(Path.of("tickets.txt"));

            System.out.println("Loaded tickets: " + loadedTickets.size());

            ReportGenerator.generateReport(
                    repository.all(),
                    "ticket-report.txt"

            );

            System.out.println("\nReport generated.");

        } catch (TicketNotFoundException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (InvalidTicketDataException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}