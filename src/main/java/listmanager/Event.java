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
 * it also has a start and end date
 */
public class Event extends Task {
    private String start;
    private String end;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;
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
        StringBuilder sb = new StringBuilder();
        sb.append("(from: ");
        appendStartDate(sb);

        sb.append(" to: ");
        appendEndDate(sb);
        sb.append(")");
        return sb.toString();
    }

    private void appendStartDate(StringBuilder stringBuilder) {
        if (startDate == null) {
            stringBuilder.append(start);
        } else if(startTime == null) {
            stringBuilder.append(startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                    .append(" ");
        } else {
            stringBuilder.append(startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                    .append(" ")
                    .append(startTime.format(DateTimeFormatter.ofPattern("h a")));
        }
    }

    private void appendEndDate(StringBuilder stringBuilder) {
        if (endDate == null) {
            stringBuilder.append(end);
        } else if(endTime == null) {
            stringBuilder.append(endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                    .append(" ");
        } else {
            stringBuilder.append(endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                    .append(" ")
                    .append(endTime.format(DateTimeFormatter.ofPattern("h a")));
        }
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
            dateTimeProcessor(start, true);
            dateTimeProcessor(end, false);
            
        }
    }


    private void dateTimeProcessor(String input, boolean isStart) {
        List<String> dateAndTime = parser.stringSplitter(input, " ");

        if (isStart) {
            if (dateAndTime.size() == 2) {
                try {
                    DateTimeFormatter standardFormat = DateTimeFormatter.ofPattern("HHmm");
                    startTime = LocalTime.parse(dateAndTime.get(1), standardFormat);
                } catch (DateTimeParseException e) {
                    startTime = null;
                }
            }

            try {
                startDate = LocalDate.parse(dateAndTime.get(0));
            } catch (DateTimeParseException e) {
                startDate = null;
            }
        } else {
            if (dateAndTime.size() == 2) {
                try {
                    DateTimeFormatter standardFormat = DateTimeFormatter.ofPattern("HHmm");
                    endTime = LocalTime.parse(dateAndTime.get(1), standardFormat);
                } catch (DateTimeParseException e) {
                    endTime = null;
                }
            }

            try {
                endDate = LocalDate.parse(dateAndTime.get(0));
            } catch (DateTimeParseException e) {
                endDate = null;
            }
        }
    }
}
