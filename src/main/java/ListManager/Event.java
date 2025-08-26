package ListManager;

import CustomExceptions.IncompleteTaskException;

public class Event extends Task{
    private String start;
    private String end;

    public Event(String taskDescriptor) throws IncompleteTaskException{
        super(taskDescriptor);
        super.taskName = descriptorProcessor(taskDescriptor);
    }

    @Override
    public String getTaskWithStatus() {
        return "[E]"
                + "[" + getStatus() + "] "
                + getName() + " "
                + getEventPeriod();
    }

    public String getEventPeriod() {
        return "(from: " + start
                + " to: " + end + ")";
    }

    private String descriptorProcessor(String taskDescriptor) throws IncompleteTaskException {
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
                return words[0].split(" ", 2)[1];
            } else {
                throw new IncompleteTaskException("please include start and end time using /from and /to for events");
            }

        }
    }
}
