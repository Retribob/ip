package Parser;

import CustomExceptions.EmptyListException;
import CustomExceptions.IncompleteTaskException;
import CustomExceptions.NoSuchTaskException;

import ListManager.ListManager;
import UIManager.UI;

import java.util.Scanner;

public class Parser {
    private Scanner scanner;


    public Parser() {
        scanner = new Scanner(System.in);
    }

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
