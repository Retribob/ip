package listManager;

import customExceptions.IncompleteTaskException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task{
    private String deadline;
    private LocalDate date;

    public Deadline(String taskDescriptor) throws IncompleteTaskException {
        super(taskDescriptor);
        super.taskName = descriptorProcessor(taskDescriptor);
    }

    @Override
    public String getTaskWithStatus() {
        return "[D]"
                + "[" + getStatus() + "] "
                + getName() + " "
                + getDeadline();
    }

    @Override
    public String toStringFormat() {
        return "Deadline," + super.taskDescriptor + "," + super.isComplete;
    }

    public String getDeadline() {
        return "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String descriptorProcessor(String taskDescriptor) throws IncompleteTaskException {
        String[] words = taskDescriptor.split(" ", 2);
        if (words.length != 2) {
            throw new IncompleteTaskException("please include the task name, thank you.");
        } else {
            //Split at the "/by" key words to get the deadline and the task
            words = taskDescriptor.split(" /by ");

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
