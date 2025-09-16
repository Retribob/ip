package parser;

import customexceptions.EmptyListException;
import customexceptions.IncompleteTaskException;
import customexceptions.NoSuchTaskException;

import customexceptions.UnknownInputException;
import listmanager.ListManager;


/**
 * Uses <code>Scanner</code> object to read userInput
 * and processes it to perform actions.
 */
public class Parser {

    /**
     * Initializes scanner.
     */
    public Parser() {
    }

    /**
     * Scans nextline to obtain userInput.
     * Breaks down user input into chunks to perform certain actions:
     * Can end chatbot conversation. "bye"
     * Can display list. "list"
     * Can mark task as complete/incomplete. "mark/unmark <>list number</>"
     * Can delete task. "delete <>list number</>"
     * Can store task in <code>ListManager</code> object.
     *
     * @param listManager ListManager instance that stores tasks
     * @return False if the user wants to end the conversation, otherwise True.
     * @throws NoSuchTaskException If taskDescriptor in input does not match any known format.
     * @throws IncompleteTaskException If taskDescriptor in input matches known format but is incomplete.
     * @throws EmptyListException If user wants to display taskList but there are no tasks.
     */
    public String parseInput(ListManager listManager, String input)
            throws NoSuchTaskException, IncompleteTaskException, EmptyListException{
        String userText;
        userText = input;
        String[] words = userText.split(" ");
        if (userText.equals("bye")) {
            return userText;
        } else if (userText.equals("list")) {
            return listManager.displayList();
        } else if (words[0].equals("unmark")) {
            if (words.length > 1) {
                return listManager.updateTask(false, Integer.parseInt(words[1]) - 1);
            }
        } else if (words[0].equals("mark")) {
            if (words.length > 1) {
                return listManager.updateTask(true, Integer.parseInt(words[1]) - 1);
            }
        } else if (words[0].equals("delete")) {
            return listManager.deleteTasks(Integer.parseInt(words[1]) - 1);
        } else if (words[0].equals("find")) {
            return listManager.findTasks(userText);
        }

        return listManager.add(userText);
    }
}
