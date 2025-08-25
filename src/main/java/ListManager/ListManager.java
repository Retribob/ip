package ListManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListManager {
    private List<Task> taskList;

    public ListManager() {
        taskList = new ArrayList<>();
    }

    public void add(String task) {
        taskList.add(taskClassifier(task));
        System.out.println("Task Added: " + task);
    }

    public void displayList() {
        Iterator<Task> iterator = taskList.iterator();
        int count = 1;
        while(iterator.hasNext()) {
            Task task = iterator.next();
            System.out.println(count + "." + task.getTaskWithStatus());
            count++;
        }
    }

    public void updateTask(boolean status, int index) {
        Task task = taskList.get(index);
        task.changeStatus(status);
        System.out.println("You have " + (status ? "marked" : "unmarked") + " this task.\n"
                            + task.getTaskWithStatus());
    }

    public Task taskClassifier(String task) {
        //split the task string into keywords
        String[] taskKeyWords = task.split(" ");

        //by splitting the string up we can now compare the first word to identify the task type
        if (taskKeyWords[0].equals("todo")) {
            return new Todo(taskKeyWords[1]);
        } else if (taskKeyWords[0].equals("deadline")) {
            //Split at the "/by" key words to get the deadline and the task
            taskKeyWords = task.split(" /by ");

            //split again the separate the deadline keyword and the task name
            return new Deadline(taskKeyWords[0].split(" ", 2)[1], taskKeyWords[1]);
        } else {
            //Split at the "/from " key word to get the event period and the task name
            taskKeyWords = task.split(" /from ");

            System.out.println(taskKeyWords[0]);

            //Split again the separate the event keyword as well as the event start and end date
            return new Event(taskKeyWords[0].split(" ", 2)[1],
                             taskKeyWords[1].split(" /to ")[0],
                             taskKeyWords[1].split( " /to ")[1]);
        }
    }
}
