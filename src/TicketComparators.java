import java.util.Comparator;

public class TicketComparators {

    public static Comparator<Ticket> byAssigneeThenPriority() {
        return Comparator.comparing(Ticket::getAssignee,
                        Comparator.nullsLast(String::compareTo))
                .thenComparing(Ticket::getPriority);
    }

    public static Comparator<Ticket> byStatusThenAge() {
        return Comparator.comparing(Ticket::getStatus)
                .thenComparing(Ticket::ageInHours);
    }
}