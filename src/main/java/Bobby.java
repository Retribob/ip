import CustomExceptions.EmptyListException;
import CustomExceptions.IncompleteTaskException;
import CustomExceptions.NoSuchTaskException;

import TextTypes.Text;
import TextTypes.EndText;
import TextTypes.StartText;

import ListManager.ListManager;

import java.util.Scanner;


public class Bobby {
    private static boolean isRunning;
    private Scanner scanner;
    private ListManager listManager;

    public Bobby() {
        isRunning = true;
        scanner = new Scanner(System.in);
        listManager = new ListManager();

        generateResponse(new StartText());
    }

    public void printLine() {
        System.out.println("________________________________________________________");
    }

    public void generateResponse(Text text) {
        System.out.println(text.getText());
    }

    public void userInput() throws NoSuchTaskException, IncompleteTaskException, EmptyListException {
        String userText;
        userText = scanner.nextLine();
        String[] words = userText.split(" ");
        if (userText.equals("bye")) {
            endChat();
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
    }

    public void endChat() {
        listManager.saveTasks();
        generateResponse(new EndText());
        isRunning = false;
    }

    public static void main(String[] args) {
        Bobby chatBot = new Bobby();
        while (isRunning) {
            try {
                chatBot.printLine();
                chatBot.userInput();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
