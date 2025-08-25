package ListManager;

public class Task {
    private String taskName;
    private boolean isComplete;

    public Task(String taskName) {
        this.taskName = taskName;
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
