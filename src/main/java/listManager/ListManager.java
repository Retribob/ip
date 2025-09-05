package listManager;

import taskStorage.TaskSaver;

import customExceptions.EmptyListException;
import customExceptions.IncompleteTaskException;
import customExceptions.NoSuchTaskException;

import java.util.Iterator;
import java.util.List;


/**
 * Stores <code>Task</code> objects in a <code>List</code> taskList.
 * Tracks taskCount, completedTasks and uncompletedTasks.
 * Contains <code>TaskStorage</code> object which saves stored tasks to a file on end.
 */
public class ListManager {
    private List<Task> taskList;
    private static int taskCount = 0;
    private static int completedTasks = 0;
    private static int uncompletedTasks = 0;

    private TaskSaver taskSaver;

    /**
     *Initializes <code>TaskStorage</code> instance.
     * Checks for existing save file and stores to <code>TaskList</code> object.
     */
    public ListManager() {
        taskSaver = new TaskSaver();
        taskList = taskSaver.loadTasks();
    }

    /**
     * Adds a task to the <code>TaskList</code> based on the input string.
     *
     * @param task task description in string format.
     * @throws NoSuchTaskException If task description doesn't match any known format.
     * @throws IncompleteTaskException If task description matches known format but is incomplete.
     */
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

    /**
     * Displays the currently stored tasks in <code>TaskList</code>.
     * The task status along with the task name and relevant information is shown.
     *
     * @throws EmptyListException If the list is empty
     */
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

    /**
     * Updates completion status of the task at the specified index
     *
     * @param isComplete True to set task as complete, false to set task as incomplete.
     * @param index The task number in the List.
     * @throws NoSuchTaskException If index > taskList.size().
     */
    public void updateTask(boolean isComplete, int index) throws NoSuchTaskException{
        if (index > taskList.size() - 1) {
            throw new NoSuchTaskException("There is no task corresponding to the number" + (index + 1));
        }
        Task task = taskList.get(index);
        task.changeStatus(isComplete);
        if (isComplete) {
            completedTasks++;
            uncompletedTasks--;
        } else {
            uncompletedTasks++;
            completedTasks--;
        }
        System.out.println("You have " + (isComplete ? "marked" : "unmarked") + " this task.\n"
                            + task.getTaskWithStatus());
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The task number in the list.
     * @throws NoSuchTaskException If index > taskList.size().
     */
    public void deleteTasks(int index) throws NoSuchTaskException{
        if (index > taskList.size() - 1) {
            throw new NoSuchTaskException("There is no task corresponding to the number" + (index + 1));
        }
        Task deletedTask = taskList.remove(index);
        System.out.println("You have deleted " + deletedTask.getTaskWithStatus());
    }

    /**
     * Classifies the task into one of three types:
     * <code>Todo</code>, <code>Deadline</code>, <code>Event</code>.
     *
     * @param task Task descriptor in <code>String</code> format.
     * @return <code>Task</code> object that has been initialized as one of the three types.
     * @throws NoSuchTaskException If task descriptor does not match known format.
     * @throws IncompleteTaskException If task descriptor matches known format but is incomplete.
     */
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

    /**
     * Calls taskSaver to save currently stored tasks.
     */
    public void closeList() {
        taskSaver.saveTasks(taskList);
    }


}
