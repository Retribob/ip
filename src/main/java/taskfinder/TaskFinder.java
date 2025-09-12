package taskfinder;

import customexceptions.EmptyListException;
import listmanager.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * TaskFinder class adds found tasks to a filteredList and then displays the tasks.
 */
public class TaskFinder {
    private List<Task> filteredList = new ArrayList<>();

    /**
     * setFilteredList filters out tasks based on userInput.
     *
     * @param taskList Contains all stored <code>Task</code> objects.
     * @param userInput Find command containing search keyword.
     * @throws EmptyListException If taskList is empty.
     */
    public String setFilteredList(List<Task> taskList, String userInput) throws EmptyListException {
        filteredList = new ArrayList<>();
        String keyword = userInput.split(" ")[1];
        if (taskList.isEmpty()) {
            throw new EmptyListException("There are no tasks to filter, please input some tasks");
        }
        for (Task task : taskList) {
            if (task.getName().contains(keyword)) {
                filteredList.add(task);
            }
        }
        return displayFilteredList();
    }

    private String displayFilteredList() {
        String returnString = "";
        Iterator<Task> iterator = filteredList.iterator();
        int count = 1;
        while(iterator.hasNext()) {
            Task task = iterator.next();
            returnString += count + "." + task.getTaskWithStatus() + "\n";
            count++;
        }
        return returnString;
    }
}
