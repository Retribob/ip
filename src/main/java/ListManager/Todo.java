package ListManager;

public class Todo extends Task{
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String getTaskWithStatus() {
        return "[T]"
                + "[" + getStatus() + "] "
                + getName();
    }

}
