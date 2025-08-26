package ListManager;

public class Task {
    private String taskDescriptor;
    private boolean isComplete;
    protected String taskName;

    public Task(String taskName) {
        this.taskDescriptor = taskDescriptor;
    }


    public String getTaskWithStatus() {
        return "[" + getStatus() + "] "
                + getName();
    }

    public String getName() {
        return taskName;
    }

    public String getStatus() {
        if (isComplete) {
            return "X";
        } else {
            return " ";
        }
    }

    public void changeStatus(boolean status) {
        this.isComplete = status;
    }
}
