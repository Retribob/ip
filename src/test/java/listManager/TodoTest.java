package listManager;

import customExceptions.IncompleteTaskException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void getTaskWithStatus_incompleteStatus_outputIncompleteTodoTask() throws IncompleteTaskException {
        String expectedTask = "[T][ ] Todo Task";
        assertEquals(expectedTask, new Todo("todo Todo Task").getTaskWithStatus());
    }

    @Test
    public void toStringFormat_correctTaskDescriptor_returnTaskString() throws IncompleteTaskException{
        String expectedName = "Todo,todo Todo Task,false";
        assertEquals(expectedName, new Todo("todo Todo Task").toStringFormat());
    }
}
