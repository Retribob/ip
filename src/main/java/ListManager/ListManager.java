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
        taskList.add(new Task(task));
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
}
