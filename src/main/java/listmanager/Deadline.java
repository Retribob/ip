package listmanager;

import customexceptions.IncompleteTaskException;

import parser.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Subtype of <code>Task</code>, aside from a task name
 * it also has a deadline date.
 */
public class Deadline extends Task {
    private String deadline;
    private LocalDate date;
    private Parser parser = new Parser();

    public Deadline(String taskDescriptor) throws IncompleteTaskException {
        super(taskDescriptor);
        descriptorProcessor(taskDescriptor);
    }

    /**
     * Displays deadline task in String format complete with status and deadline.
     *
     * @return Task name, with completion status and deadline.
     */
    @Override
    public String getTaskWithStatus() {
        return "[D]"
                + "[" + getStatus() + "] "
                + getName() + " "
                + getDeadline() + " "
                + super.getTags();
    }

    /**
     * Converts task to a String format for storage.
     *
     * @return String to be stored into save file.
     */
    @Override
    public String toStringFormat() {
        return "Deadline," + super.taskDescriptor + "," + super.isComplete;
    }

    public String getDeadline() {
        return "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Processes taskDescriptor and splits it into task name and deadline time.
     *
     * @param taskDescriptor String of user input passed into constructor.
     * @return Task name in string format.
     * @throws IncompleteTaskException If taskDescriptor is in known format but incomplete.
     */
    public void descriptorProcessor(String taskDescriptor) throws IncompleteTaskException {
        List<String> words = parser.stringSplitter(taskDescriptor, " ", " /by ");
        if (words.size() < 2) {
            throw new IncompleteTaskException("please include the task name, thank you.");
        }

        if (words.size() < 3) {
            throw new IncompleteTaskException("Please add a deadline.\n Example: deadline go home /by 2pm");
        }

        //words length should at most be 3.
        assert (words.size() <= 3): "word segments exceed expected amount";

        this.deadline = words.get(2);
        date = LocalDate.parse(this.deadline);
        super.taskName = words.get(1);
    }
}
