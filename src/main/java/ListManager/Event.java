package ListManager;

public class Event extends Task{
    public Event(String taskName) {
        super(taskName);
    }

    @Override
    public String getTaskWithStatus() {
        return "[E]"
                + "[" + getStatus() + "] "
                + getName();
    }
}
