package ListManager;

import CustomExceptions.EmptyListException;
import CustomExceptions.IncompleteTaskException;
import CustomExceptions.NoSuchTaskException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListManager {
    private List<Task> taskList;
    private static int taskCount = 0;
    private static int completedTasks = 0;
    private static int uncompletedTasks = 0;

    public ListManager() {
        taskList = new ArrayList<>();
    }

    public void add(String task) throws NoSuchTaskException, IncompleteTaskException{
        Task taskType = taskClassifier(task);
        taskList.add(taskType);
        taskCount += 1;
        uncompletedTasks++;
        System.out.println("Task Added: " + taskType.getName()
                           + "\nDo remember to take care of your health."
                           + "\nYou have " + taskCount + " tasks in list, " + completedTasks + " completed "
                           + uncompletedTasks + " uncompleted.");
    }

    public void displayList() throws EmptyListException {
        if (taskList.isEmpty()) {
            throw new EmptyListException("There are no tasks to display, please input some tasks");
        }
        Iterator<Task> iterator = taskList.iterator();
        int count = 1;
        while(iterator.hasNext()) {
            Task task = iterator.next();
            System.out.println(count + "." + task.getTaskWithStatus());
            count++;
        }
    }

    public void updateTask(boolean status, int index) throws NoSuchTaskException{
        if (index > taskList.size() - 1) {
            throw new NoSuchTaskException("There is no task corresponding to the number" + (index + 1));
        }
        Task task = taskList.get(index);
        task.changeStatus(status);
        if (status) {
            completedTasks++;
            uncompletedTasks--;
        } else {
            uncompletedTasks++;
            completedTasks--;
        }
        System.out.println("You have " + (status ? "marked" : "unmarked") + " this task.\n"
                            + task.getTaskWithStatus());
    }

    public Task taskClassifier(String task) throws NoSuchTaskException, IncompleteTaskException{
        //split the task string into keywords
        String[] taskKeyWords = task.split(" ", 2);


        //by splitting the string up we can now compare the first word to identify the task type
        if (taskKeyWords[0].equals("todo")) {
            return new Todo(task);
        } else if (taskKeyWords[0].equals("deadline")) {
            return new Deadline(task);
        } else if (taskKeyWords[0].equals("event")){
            return new Event(task);
        } else {
            throw new NoSuchTaskException("Sorry I don't recognize this task, can you please use the keywords?");
        }
    }
}
