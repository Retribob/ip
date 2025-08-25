package ListManager;

public class Deadline extends Task{
    public Deadline(String taskName) {
        super(taskName);
    }

    @Override
    public String getTaskWithStatus() {
        return "[D]"
                + "[" + getStatus() + "] "
                + getName();
    }
}
