package listmanager;

import customexceptions.IncompleteTaskException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Subtype of <code>Task</code>, aside from a task name
 * it also has a start and end date
 */
public class Event extends Task {
    private String start;
    private String end;
    private LocalDate startDate;
    private LocalDate endDate;

    public Event(String taskDescriptor) throws IncompleteTaskException {
        super(taskDescriptor);
        super.taskName = descriptorProcessor(taskDescriptor);
    }

    /**
     * Displays event task in string format with
     * completion status and start and end date.
     *
     * @return Task name with status and start and end date in string format.
     */
    @Override
    public String getTaskWithStatus() {
        return "[E]"
                + "[" + getStatus() + "] "
                + getName() + " " + getEventPeriod();
    }

    /**
     * Converts task to string format for storing to file.
     *
     * @return String that contains information of the task for storing.
     */
    @Override
    public String toStringFormat() {
        return "Event," + super.taskDescriptor + "," + super.isComplete;
    }


    public String getEventPeriod() {
        return "(from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Processes taskDescriptor and splits it into task name and start and end date.
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
            //Split at the "/from " key word to get the event period and the task name
            words = taskDescriptor.split(" /from ");

            //Split again the separate the event keyword as well as the event start and end date
            if (words.length > 1) {
                this.start = words[1].split(" /to ")[0];
                this.end = words[1].split(" /to ")[1];
                this.startDate = LocalDate.parse(this.start);
                this.endDate = LocalDate.parse(this.end);
                return words[0].split(" ", 2)[1];
            } else {
                throw new IncompleteTaskException("please include start and end time using /from and /to for events");
            }

        }
    }
}
