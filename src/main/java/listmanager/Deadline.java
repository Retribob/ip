package listmanager;

import customexceptions.IncompleteTaskException;

import parser.Parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Subtype of <code>Task</code>, aside from a task name
 * it also has a deadline date.
 */
public class Deadline extends Task {
    private String deadline;
    private LocalDate date;
    private LocalTime time;
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
        StringBuilder sb = new StringBuilder();
        sb.append("Deadline,").append(super.taskDescriptor + ",").append(super.isComplete);

        //Append tags
        for (int i = 0; i < super.taskTags.size(); i++) {
            sb.append("," + super.taskTags.get(i).getName());
        }
        return sb.toString();

    }

    public String getDeadline() {
        StringBuilder sb = new StringBuilder();
        sb.append("(by: ");
        if (date == null) {
            sb.append(this.deadline).append(")");
            return sb.toString();
        }

        if (time == null) {
            sb.append(date.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                    .append(")");
            return sb.toString();
        }

        sb.append(date.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                .append(" ")
                .append(time.format(DateTimeFormatter.ofPattern("h a")))
                .append(")");

        return sb.toString();
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

        super.taskName = words.get(1);
        this.deadline = words.get(2);
        dateTimeProcessor(this.deadline);

    }


    private void dateTimeProcessor(String deadline) {
        List<String> dateAndTime = parser.stringSplitter(deadline, " ");

        if (dateAndTime.size() == 2) {
            try {
                DateTimeFormatter standardFormat = DateTimeFormatter.ofPattern("HHmm");
                time = LocalTime.parse(dateAndTime.get(1), standardFormat);
            } catch (DateTimeParseException e) {
                time = null;
            }
        }

        try {
            date = LocalDate.parse(dateAndTime.get(0));
        } catch (DateTimeParseException e) {
            date = null;
        }
    }
}
