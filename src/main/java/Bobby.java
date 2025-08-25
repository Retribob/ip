import TextTypes.*;
import ListManager.*;

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

    public void userInput() {
        String userText;
        userText = scanner.nextLine();
        if (userText.equals("bye")) {
            endChat();
        } else if (userText.equals("list")) {
            listManager.displayList();
        } else {
            listManager.add(userText);
        }
    }

    public void endChat() {
        generateResponse(new EndText());
        isRunning = false;
    }

    public static void main(String[] args) {
        Bobby chatBot = new Bobby();
        while (isRunning) {
            chatBot.printLine();
            chatBot.userInput();
        }
    }
}
