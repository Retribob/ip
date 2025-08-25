package ListManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListManager {
    private List<String> taskList;

    public ListManager() {
        taskList = new ArrayList<>();
    }

    public void add(String task) {
        taskList.add(task);
        System.out.println("Task Added: " + task);
    }

    public void displayList() {
        Iterator<String> iterator = taskList.iterator();
        int count = 0;
        while(iterator.hasNext()) {
            System.out.println(count + ". " + iterator.next());
            count++;
        }
    }
}
