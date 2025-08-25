package ListManager;

public class Deadline extends Task{
    private String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String getTaskWithStatus() {
        return "[D]"
                + "[" + getStatus() + "] "
                + getName() + " "
                + getDeadline();
    }

    public String getDeadline() {
        return "(by: " + this.deadline + ")";
    }
}
