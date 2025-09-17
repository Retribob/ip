package listmanager;

import customexceptions.IncompleteTaskException;
import parser.Parser;

import java.util.List;

/**
 * Subtype of <code>Task</code>, it just has a task name.
 */
public class Todo extends Task {

    private Parser parser = new Parser();

    public Todo(String taskDescriptor) throws IncompleteTaskException {
        super(taskDescriptor);
        descriptorProcessor(taskDescriptor);
    }

    /**
     * Returns task name with status in string format.
     * @return String containing task name and completion status.
     */
    @Override
    public String getTaskWithStatus() {
        return "[T]"
                + "[" + getStatus() + "] "
                + getName() + " "
                + super.getTags();
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
    public void descriptorProcessor(String taskDescriptor) throws IncompleteTaskException {
        List<String> words = parser.stringSplitter(taskDescriptor, " ");

        //words length should at most be 2.
        assert (words.size() <= 2): "word segments exceed expected amount";

        if (words.size() != 2) {
            throw new IncompleteTaskException("please include the task name, thank you.");
        }
        super.taskName = words.get(1);
    }

}
