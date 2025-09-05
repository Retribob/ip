package listManager;

import customExceptions.IncompleteTaskException;

public class Task {
    protected String taskDescriptor;
    protected boolean isComplete;
    protected String taskName;

    public Task(String taskDescriptor) {
        this.taskDescriptor = taskDescriptor;
    }

    //converts object to string for saving to a file
    public String toStringFormat() {
        return taskName + "," + isComplete;
    }

    //Create object from string
    public static Task stringToTask(String fileOutput) throws IncompleteTaskException {
        String[] words = fileOutput.split(",");
        String taskType = words[0];
        String taskDescriptor = words[1];
        boolean isComplete = Boolean.parseBoolean(words[2]);
        Task returnTask;
        if (taskType.equals("Todo")) {
            returnTask = new Todo(taskDescriptor);
            returnTask.changeStatus(isComplete);
        } else if (taskType.equals("Deadline")) {
            returnTask = new Deadline(taskDescriptor);
            returnTask.changeStatus(isComplete);
        } else {
            returnTask = new Event(taskDescriptor);
            returnTask.changeStatus(isComplete);
        }
        return returnTask;
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

    public void changeStatus(boolean isComplete) {
        this.isComplete = isComplete;
    }
}
