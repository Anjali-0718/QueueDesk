import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Comparator;
import java.util.stream.Collectors;

public class TicketAnalytics {
    public static Map<Priority, Long> countByPriority(List<Ticket> tickets){
        return tickets.stream().collect(Collectors.groupingBy(
                Ticket::getPriority,
                Collectors.counting())); //counting returns long
    }
    public static Map<String, List<Ticket>> groupByAssignee(List<Ticket> tickets){
        return tickets.stream()
                .filter(ticket->ticket.getAssignee()!=null)
                .collect(Collectors.groupingBy(Ticket::getAssignee));
    }
    public static double averageEffortHours(List<Ticket> tickets){
        return tickets.stream()
                .mapToDouble(Ticket::estimateEffortHours)
                .average()
                .orElse(0.0);
    }
    public static double totalEffortHours(List<Ticket> tickets){
        return tickets.stream()
                .map(Ticket::estimateEffortHours)
                .reduce(0.0,Double::sum,Double::sum);
    }
    public static Optional<Ticket> oldestOpenTicket(List<Ticket> tickets){
        return tickets.stream()
                .filter(ticket -> ticket.getStatus()==Status.OPEN)
                .min(Comparator.comparing(Ticket::getCreatedAt));
    }
}
