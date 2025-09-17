package uimanager;

import listmanager.Task;
import texttypes.EndText;
import texttypes.StartText;



public class UI {

    public String onStart() {
        return new StartText().getText();
    }

    public String onEnd() {
        return new EndText().getText();
    }

    public String onNewTask(Task task, int taskCount, int completedTasks, int uncompletedTasks) {
        return "Task Added: " + task.getName()
                + "\nDo remember to take care of your health."
                + "\nYou have " + taskCount + " tasks in list, " + completedTasks + " completed "
                + uncompletedTasks + " uncompleted.";
    }

    public String onUpdateTask(boolean isComplete, Task task) {
        return "You have " + (isComplete ? "marked" : "unmarked") + " this task.\n"
                + task.getTaskWithStatus();
    }

    public String onDeleteTask(Task task) {
        return "You have deleted " + task.getTaskWithStatus();
    }

    public String onTagTask(String tagName, Task task) {
        return "Added " + tagName + " tag to " + task.getName()
                + ".\nHave a bobulous day!";
    }

}
