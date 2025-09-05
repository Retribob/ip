package taskFinder;

import customExceptions.EmptyListException;
import listManager.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskFinder {
    private List<Task> filteredList = new ArrayList<>();

    public void setFilteredList(List<Task> taskList, String keyword) throws EmptyListException{
        if (taskList.isEmpty()) {
            throw new EmptyListException("There are no tasks to filter, please input some tasks");
        }
        for (Task task : taskList) {
            if (task.getName().contains(keyword)) {
                filteredList.add(task);
            }
        }
        displayFilteredList();
    }

    private void displayFilteredList() {
        Iterator<Task> iterator = filteredList.iterator();
        int count = 1;
        while(iterator.hasNext()) {
            Task task = iterator.next();
            System.out.println(count + "." + task.getTaskWithStatus());
            count++;
        }
    }
}
