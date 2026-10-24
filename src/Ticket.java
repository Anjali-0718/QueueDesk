import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public abstract class Ticket implements Comparable<Ticket> {
    private static int nextId = 0;

    private int id;
    private String title;
    private String description;
    private String requester;
    private Priority priority;
    private Status status;
    private LocalDateTime createdAt;

    private String assignee;
    private Set<String> tags;

    public Ticket(String title,
                  String description,
                  String requester,
                  Priority priority) {

        this.id = ++nextId;
        this.title = title;
        this.description = description;
        this.requester = requester;
        this.priority = priority;
        this.status = Status.OPEN;
        this.createdAt = LocalDateTime.now();

        this.assignee = null;
        this.tags = new HashSet<>();
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

    public String getAssignee() {
        return assignee;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            return;
        }

        this.description = description.trim();
    }

    public void setPriority(Priority priority) {
        if (priority == null) {
            return;
        }

        this.priority = priority;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public double ageInHours() {
        Duration duration = Duration.between(createdAt, LocalDateTime.now());
        return duration.toMinutes() / 60.0;
    }

    public static int totalCreated() {
        return nextId;
    }

    @Override
    public int compareTo(Ticket other) {
      if(this.priority!=other.priority){
          return other.priority.ordinal()-this.priority.ordinal();
      }
        return this.createdAt.compareTo(other.createdAt);
    }

    public abstract double estimateEffortHours();
}