package taskstorage;

import customexceptions.IncompleteTaskException;
import listmanager.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Stores <code>Task</code> objects in the task list to a file
 * Can load in tasks from an external file.
 */
public class TaskSaver {

    /**
     * Saves currently stored <code>Task</code> objects to a file in string format.
     *
     * @param taskList List containing stored <code>Task</code> objects.
     */
    //The idea of utilizing Printwriter originates from consulting with Claude AI on ways to read and write to files.
    public void saveTasks(List<Task> taskList) {
        System.out.println("Saving tasks");
        try (PrintWriter writer = new PrintWriter("Tasks.txt")) {
            for (Task task : taskList) {
                writer.println(task.toStringFormat());
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Load tasks from a file.
     * Prints "File not found" if no file exists.
     * @return A <code>List</code> object containing <code>Task</code> objects.
     */
    //Use of Claude AI to figure out scanner.nextLine().trim() to fix errors.
    public List<Task> loadTasks() {
        List<Task> taskList = new ArrayList<Task>();
        try (Scanner scanner = new Scanner(new File("Tasks.txt"))) {
            while (scanner.hasNextLine()) {
                String taskLine = scanner.nextLine().trim(); //remove trailing spaces to fix error (suggested by Claude)
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
