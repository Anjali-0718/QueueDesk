import java.time.Duration;
import java.time.LocalDateTime;

public abstract class Ticket {

    private static int nextId = 0;
    private int id;
    private String title;
    private String description;
    private String requester;
    private Priority priority;
    private Status status;
    private LocalDateTime createdAt;

    public Ticket(String title, String description, String requester, Priority priority) {
        this.id = ++nextId;
        this.title = title;
        this.description = description;
        this.requester = requester;
        this.priority = priority;
        this.status = Status.OPEN;
        this.createdAt = LocalDateTime.now();
    }
    public Ticket(String title, String requester) {
        this(
                title,
                "No description provided",
                requester,
                Priority.MEDIUM
        );
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getRequester() {
        return requester;
    }
    public Priority getPriority() {
        return priority;
    }
    public Status getStatus() {
        return status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double ageInHours() {
        Duration duration = Duration.between(
                createdAt,
                LocalDateTime.now()
        );

        return duration.toMinutes() / 60.0;
    }

    public static int totalCreated() {
        return nextId;
    }

    public abstract double estimateEffortHours();
}