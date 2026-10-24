public class FeatureRequestTicket extends Ticket {

    private int votes;
    private String businessJustification;

    public FeatureRequestTicket(String title,
                                String description,
                                String requester,
                                Priority priority,
                                int votes,
                                String businessJustification) {

        super(title, description, requester, priority);

        this.votes = votes;
        this.businessJustification = businessJustification;
    }

    public int getVotes() {
        return votes;
    }

    public String getBusinessJustification() {
        return businessJustification;
    }

    @Override
    public double estimateEffortHours() {
        return 8.0;
    }

    @Override
    public String toString() {
        return "FeatureRequestTicket{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", priority=" + getPriority() +
                ", votes=" + votes +
                ", status=" + getStatus() +
                '}';
    }
}