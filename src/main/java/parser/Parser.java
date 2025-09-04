package parser;

import customExceptions.EmptyListException;
import customExceptions.IncompleteTaskException;
import customExceptions.NoSuchTaskException;

import listManager.ListManager;

import java.util.Scanner;

/**
 * Uses <code>Scanner</code> object to read userInput
 * and processes it to perform actions.
 */
public class Parser {
    private Scanner scanner;

    /**
     * Initializes scanner.
     */
    public Parser() {
        scanner = new Scanner(System.in);
    }

    /**
     *Scans nextline to obtain userInput.
     * Breaks down user input into chunks to perform certain actions:
     * Can end chatbot conversation.
     * Can display list.
     * Can mark task as complete/incomplete.
     * Can delete task.
     * Can store task in <code>ListManager</code> object.
     *
     * @param listManager ListManager instance that stores tasks
     * @return False if the user wants to end the conversation, otherwise True.
     * @throws NoSuchTaskException If taskDescriptor in input does not match any known format.
     * @throws IncompleteTaskException If taskDescriptor in input matches known format but is incomplete.
     * @throws EmptyListException If user wants to display taskList but there are no tasks.
     */
    public boolean parseInput(ListManager listManager) throws NoSuchTaskException, IncompleteTaskException, EmptyListException {
        String userText;
        userText = scanner.nextLine();
        String[] words = userText.split(" ");
        if (userText.equals("bye")) {
            return false;
        } else if (userText.equals("list")) {
            listManager.displayList();
        } else if (words[0].equals("unmark")) {
            listManager.updateTask(false, Integer.parseInt(words[1]) - 1);
        } else if (words[0].equals("mark")) {
            words = userText.split(" ");
            listManager.updateTask(true, Integer.parseInt(words[1]) - 1);
        } else if (words[0].equals("delete")){
            listManager.deleteTasks(Integer.parseInt(words[1]) - 1);
        } else {
            listManager.add(userText);
        }
        return true;
    }
}
