package listManager;

import customExceptions.IncompleteTaskException;
import customExceptions.NoSuchTaskException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListManagerTest {
    @Test
    public void taskClassifier_todoTaskDescriptor_todoTask()
            throws IncompleteTaskException, NoSuchTaskException {
        ListManager listManager = new ListManager();
        assertEquals(new Todo("todo Todo Task").getTaskWithStatus(),
                listManager.taskClassifier("todo Todo Task").getTaskWithStatus());
    }

    @Test
    public void taskClassifier_deadlineTaskDescriptor_deadlineTask()
            throws IncompleteTaskException, NoSuchTaskException{
        ListManager listManager = new ListManager();
        assertEquals(new Deadline("deadline Deadline Task /by 2022-01-01").getTaskWithStatus(),
                        listManager.taskClassifier("deadline Deadline Task /by 2022-01-01").getTaskWithStatus());
    }

}
