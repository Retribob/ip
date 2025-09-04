package listManager;

import customExceptions.IncompleteTaskException;

/**
 * Task object stores information of the task such as taskDescriptor and taskName.
 * Tracks completion of task using boolean isComplete.
 */
public class Task {
    protected String taskDescriptor;
    protected boolean isComplete;
    protected String taskName;

    public Task(String taskDescriptor) {
        this.taskDescriptor = taskDescriptor;
    }


    public String toStringFormat() {
        return taskName + "," + isComplete;
    }


    /**
     * Public method which converts file string output to a <code>Task</code>.
     * Used for reading external files and generating the corresponding <code>Task</code> object.
     *
     * @param fileOutput String containing information on stored <code>Task</code>.
     * @return <code>Task</code> object that was stored.
     * @throws IncompleteTaskException If fileOutput is corrupted.
     */
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

    public void changeStatus(boolean status) {
        this.isComplete = status;
    }
}
