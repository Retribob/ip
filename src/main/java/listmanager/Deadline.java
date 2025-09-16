package listmanager;

import customexceptions.IncompleteTaskException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Subtype of <code>Task</code>, aside from a task name
 * it also has a deadline date.
 */
public class Deadline extends Task {
    private String deadline;
    private LocalDate date;

    public Deadline(String taskDescriptor) throws IncompleteTaskException {
        super(taskDescriptor);
        super.taskName = descriptorProcessor(taskDescriptor);
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
                + getDeadline();
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
    public String descriptorProcessor(String taskDescriptor) throws IncompleteTaskException {
        String[] words = taskDescriptor.split(" ", 2);
        if (words.length != 2) {
            throw new IncompleteTaskException("please include the task name, thank you.");
        } else {
            //Split at the "/by" key words to get the deadline and the task
            words = taskDescriptor.split(" /by ");

            //words length should at most be 2.
            assert (words.length <= 2): "word segments exceed expected amount";

            //split again the separate the deadline keyword and the task name
            if (words.length > 1) {
                this.deadline = words[1];
                date = LocalDate.parse(this.deadline);
                return words[0].split(" ", 2)[1];
            } else {
                throw new IncompleteTaskException("Please add a deadline.\n Example: deadline go home /by 2pm");
            }
        }
    }
}
