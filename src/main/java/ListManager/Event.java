package ListManager;

public class Event extends Task{
    private String start;
    private String end;

    public Event(String taskName, String start, String end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTaskWithStatus() {
        return "[E]"
                + "[" + getStatus() + "] "
                + getName() + " "
                + getEventPeriod();
    }

    public String getEventPeriod() {
        return "(from: " + start
                + " to: " + end + ")";
    }
}
