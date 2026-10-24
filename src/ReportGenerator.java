import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportGenerator {

    public static void generateReport(List<Ticket> tickets, String fileName) throws IOException {
        StringBuilder report = new StringBuilder();
        report.append("END OF DAY TICKET REPORT\n\n");
        report.append("Status Counts:\n");
        for (Status status : Status.values()) {
            int count = 0;

            for (Ticket ticket : tickets) {
                if (ticket.getStatus() == status) {
                    count++;
                }
            }

            report.append(status).append(": ").append(count).append("\n");
        }
        report.append("\nPriority Counts:\n");
        for (Priority priority : Priority.values()) {
            int count = 0;

            for (Ticket ticket : tickets) {
                if (ticket.getPriority() == priority) {
                    count++;
                }
            }

            report.append(priority).append(": ").append(count).append("\n");
        }

        Ticket oldestOpen = null;
        for (Ticket ticket : tickets) {
            if (ticket.getStatus() == Status.OPEN) {
                if (oldestOpen == null || ticket.getCreatedAt().isBefore(oldestOpen.getCreatedAt())) {
                    oldestOpen = ticket;
                }
            }
        }

        report.append("\nOldest Open Ticket:\n");
        if (oldestOpen != null) {
            report.append(oldestOpen).append("\n");
        } else {
            report.append("None\n");
        }

        report.append("\nOverdue CRITICAL Tickets:\n");

        for (Ticket ticket : tickets) {
            if (ticket.getPriority() == Priority.CRITICAL &&
                    ticket.ageInHours() > 24) {
                report.append(ticket).append("\n");
            }
            else report.append("---");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report.toString());
        }
    }
}