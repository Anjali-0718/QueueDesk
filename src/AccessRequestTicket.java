public class AccessRequestTicket extends Ticket implements Escalatable {

    private String systemName;
    private String accessLevel;

    private boolean escalated;
    private int escalationLevel;

    public AccessRequestTicket(String title,
                               String description,
                               String requester,
                               Priority priority,
                               String systemName,
                               String accessLevel) {

        super(title, description, requester, priority);

        this.systemName = systemName;
        this.accessLevel = accessLevel;
        this.escalated = false;
        this.escalationLevel = 0;
    }

    public String getSystemName() {
        return systemName;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    @Override
    public double estimateEffortHours() {
        return switch (getPriority()) {
            case LOW -> 1.0;
            case MEDIUM -> 2.0;
            case HIGH -> 4.0;
            case CRITICAL -> 6.0;
        };
    }

    @Override
    public void escalate() {
        escalated = true;
        escalationLevel++;
    }

    @Override
    public boolean isEscalated() {
        return escalated;
    }

    @Override
    public int escalationLevel() {
        return escalationLevel;
    }

    @Override
    public String toString() {
        return "AccessRequestTicket{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", priority=" + getPriority() +
                ", systemName='" + systemName + '\'' +
                ", accessLevel='" + accessLevel + '\'' +
                ", status=" + getStatus() +
                '}';
    }
}