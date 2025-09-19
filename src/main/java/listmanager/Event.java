package listmanager;

import customexceptions.IncompleteTaskException;
import parser.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Subtype of <code>Task</code>, aside from a task name
 * it also has a start and end date
 */
public class Event extends Task {
    private String start;
    private String end;
    private LocalDate startDate;
    private LocalDate endDate;
    private Parser parser = new Parser();

    public Event(String taskDescriptor) throws IncompleteTaskException {
        super(taskDescriptor);
        descriptorProcessor(taskDescriptor);
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
                + getName() + " "
                + getEventPeriod()  + " "
                + super.getTags();
    }

    /**
     * Converts task to string format for storing to file.
     *
     * @return String that contains information of the task for storing.
     */
    @Override
    public String toStringFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("Event,").append(super.taskDescriptor + ",").append(super.isComplete);

        //Append tags
        for (int i = 0; i < super.taskTags.size(); i++) {
            sb.append("," + super.taskTags.get(i).getName());
        }
        return sb.toString();
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
    public void descriptorProcessor(String taskDescriptor) throws IncompleteTaskException {
        List<String> words = parser.stringSplitter(taskDescriptor, " ", " /from ", " /to ");
        if (words.size() < 2) {
            throw new IncompleteTaskException("please include the task name, thank you.");
        } else {

            //words length should at most be 4.
            assert (words.size() <= 4): "word segments exceed expected amount";

             if (words.size() < 4) {
                throw new IncompleteTaskException("please include start and end time using /from and /to for events");
            }
             
            super.taskName = words.get(1);
            this.start = words.get(2);
            this.end = words.get(3);
            this.startDate = LocalDate.parse(this.start);
            this.endDate = LocalDate.parse(this.end);
            
        }
    }
}
