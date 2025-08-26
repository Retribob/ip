package ListManager;

import CustomExceptions.IncompleteTaskException;

public class Todo extends Task{
    private String taskDescriptor;


    public Todo(String taskDescriptor) throws IncompleteTaskException{
        super(taskDescriptor);
        super.taskName = descriptorProcessor(taskDescriptor);
    }

    @Override
    public String getTaskWithStatus() {
        return "[T]"
                + "[" + getStatus() + "] "
                + getName();
    }

    private String descriptorProcessor(String taskDescriptor) throws IncompleteTaskException {
        String[] words = taskDescriptor.split(" ", 2);
        if (words.length != 2) {
            throw new IncompleteTaskException("please include the task name, thank you.");
        } else {
            return words[1];
        }
    }

}
