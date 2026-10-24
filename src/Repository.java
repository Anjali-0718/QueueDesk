import java.util.*;
import java.util.stream.Collectors;

public class Repository<T extends Ticket> {

    private List<T> tickets = new ArrayList<>();

    public void add(T ticket) {
        tickets.add(ticket);
    }

    public List<T> all() {
        return List.copyOf(tickets);
    }

    public T findById(int id) {
        for (T ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        throw new TicketNotFoundException(id);
    }
    public Map<String, List<T>> groupByAssignee() {
        return tickets.stream()
                .collect(Collectors.groupingBy(Ticket::getAssignee));
    }
}
