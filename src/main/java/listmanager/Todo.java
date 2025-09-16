package listmanager;

import customexceptions.IncompleteTaskException;

/**
 * Subtype of <code>Task</code>, it just has a task name.
 */
public class Todo extends Task {

    public Todo(String taskDescriptor) throws IncompleteTaskException {
        super(taskDescriptor);
        super.taskName = descriptorProcessor(taskDescriptor);
    }

    /**
     * Returns task name with status in string format.
     * @return String containing task name and completion status.
     */
    @Override
    public String getTaskWithStatus() {
        return "[T]"
                + "[" + getStatus() + "] "
                + getName();
    }

    /**
     * Converts task to string format for storing to file.
     *
     * @return String that contains information of the task for storing.
     */
    @Override
    public String toStringFormat() {
        return "Todo," + super.taskDescriptor + "," + super.isComplete;
    }


    /**
     * Processes taskDescriptor and splits it into task name.
     *
     * @param taskDescriptor String of user input passed into constructor.
     * @return Task name in string format.
     * @throws IncompleteTaskException If taskDescriptor is in known format but incomplete.
     */
    public String descriptorProcessor(String taskDescriptor) throws IncompleteTaskException {
        String[] words = taskDescriptor.split(" ", 2);
        if (words.length != 2) {
            throw new IncompleteTaskException("please include the task name, thank you.");
        } else {
            return words[1];
        }
    }

}
