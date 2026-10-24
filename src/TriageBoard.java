import java.util.List;

public class TriageBoard {

    public static void printTriageBoard(List<Ticket> tickets) {

        for (Ticket ticket : tickets) {

            System.out.println(ticket);

            String description = ticket.getDescription();

            if (description.length() > 50) {
                description = description.substring(0, 50) + "...";
            }

            System.out.println("Description: " + description);

            System.out.println(
                    "Estimated effort: " +
                            ticket.estimateEffortHours() +
                            " hours"
            );

            if (ticket instanceof Escalatable e) {
                System.out.println(
                        "Escalation: " +
                                e.escalationBadge() +
                                " | Level: " +
                                e.escalationLevel()
                );
            }

            System.out.println("----------------------");
        }
    }
}