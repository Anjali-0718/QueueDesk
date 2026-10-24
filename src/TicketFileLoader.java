import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TicketFileLoader {

    public static List<Ticket> load(Path file)
            throws InvalidTicketDataException {

        List<Ticket> tickets = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile()))) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {

                lineNumber++;

                String[] fields = line.split("\\|");
                if (fields.length != 4) {
                    throw new InvalidTicketDataException(
                            lineNumber,
                            "Expected 4 fields"
                    );
                }

                String title = fields[0].trim();
                String description = fields[1].trim();
                String requester = fields[2].trim();
                String priorityText = fields[3].trim();

                try {
                    Priority priority = Priority.valueOf(priorityText.toUpperCase());

                    Ticket ticket = new FeatureRequestTicket(
                            title,
                            description,
                            requester,
                            priority,
                            0,
                            ""
                    );

                    tickets.add(ticket);

                } catch (Exception e) {
                    throw new InvalidTicketDataException(
                            lineNumber,
                            "Invalid ticket data"
                    );
                }
            }

        } catch (IOException e) {
            throw new InvalidTicketDataException(
                    0,
                    "Could not read file"
            );
        }

        return tickets;
    }
}