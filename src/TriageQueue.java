import java.util.PriorityQueue;
import java.util.List;
public class TriageQueue {

    public static void serveTickets(Repository<Ticket> repository) {

        PriorityQueue<Ticket> naturalQueue =
                new PriorityQueue<>(repository.all());

        PriorityQueue<Ticket> comparatorQueue =
                new PriorityQueue<>(
                        TicketComparators.byAssigneeThenPriority()
                );

        for (Ticket ticket : repository.all()) {
            comparatorQueue.offer(ticket);
        }

        System.out.println("Natural ordering:");

        while (!naturalQueue.isEmpty()) {
            Ticket ticket = naturalQueue.poll();
            System.out.println(ticket);
        }

        System.out.println("Comparator ordering:");

        while (!comparatorQueue.isEmpty()) {
            Ticket ticket = comparatorQueue.poll();
            System.out.println(ticket);
        }
    }
    public static <T extends Ticket> T oldest(List<T> tickets) {
        T oldestTicket = tickets.get(0);

        for (T ticket : tickets) {
            if (ticket.getCreatedAt().isBefore(oldestTicket.getCreatedAt())) {
                oldestTicket = ticket;
            }
        }

        return oldestTicket;
    }
}
