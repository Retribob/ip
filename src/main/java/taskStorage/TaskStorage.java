package taskStorage;

import customExceptions.IncompleteTaskException;
import listManager.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskStorage {

    public void saveTasks(List<Task> taskList) {
        System.out.println("Saving tasks");
        try ( PrintWriter writer = new PrintWriter("Tasks.txt")){
            for (Task task : taskList) {
                writer.println(task.toStringFormat());
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public List<Task> loadTasks() {
        List<Task> taskList = new ArrayList<Task>();
        try (Scanner scanner = new Scanner(new File("Tasks.txt"))) {
            while(scanner.hasNextLine()) {
                String taskLine = scanner.nextLine().trim(); //remove trailing spaces to fix error
                if (!taskLine.isEmpty()) {
                    taskList.add(Task.stringToTask(taskLine));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IncompleteTaskException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }
}
