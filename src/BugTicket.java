public class BugTicket extends Ticket implements Escalatable {

    private Severity severity;
    private String stepsToReproduce;

    private boolean escalated;
    private int escalationLevel;

    public BugTicket(String title,
                     String description,
                     String requester,
                     Priority priority,
                     Severity severity,
                     String stepsToReproduce) {

        super(title, description, requester, priority);

        this.severity = severity;
        this.stepsToReproduce = stepsToReproduce;
        this.escalated = false;
        this.escalationLevel = 0;
    }

    public Severity getSeverity() {
        return severity;
    }

    public String getStepsToReproduce() {
        return stepsToReproduce;
    }

    @Override
    public double estimateEffortHours() {
     switch (severity){
         case LOW:
             return 1.0;
         case MEDIUM:
             return 3.0;
         case HIGH:
             return 6.0;
         case CRITICAL:
             return 10.0;
         default:
             throw new IllegalArgumentException("Invalid severity");
     }
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
        return "BugTicket{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", priority=" + getPriority() +
                ", severity=" + severity +
                ", status=" + getStatus() +
                '}';
    }
}