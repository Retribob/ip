package listManager;

import customExceptions.IncompleteTaskException;
import customExceptions.NoSuchTaskException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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

    @Test
    public void taskClassifier_eventTaskDescriptor_eventTask()
            throws IncompleteTaskException, NoSuchTaskException{
        ListManager listManager = new ListManager();
        assertEquals(new Event("event Event Task /from 2022-01-01 /to 2022-01-01").getTaskWithStatus(),
                listManager.taskClassifier("event Event Task /from 2022-01-01 /to 2022-01-01").getTaskWithStatus());
    }

    @Test
    public void taskClassifier_randomString_NoSuchTaskException() {
        ListManager listManager = new ListManager();
        try {
            listManager.taskClassifier("randomString");
            fail();
        } catch (Exception e) {
            assertEquals("Sorry I don't recognize this task, can you please use the keywords?",
                    e.getMessage());
        }
    }
}
