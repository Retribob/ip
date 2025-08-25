import TextTypes.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;

public class Bobby {
    private static boolean isRunning;
    private Scanner scanner;
    private List<String> list;

    public Bobby() {
        isRunning = true;
        scanner = new Scanner(System.in);
        list = new ArrayList<>();

        generateResponse(new StartText());
    }

    public void printLine() {
        System.out.println("________________________________________________________");
    }

    public void generateResponse(Text text) {
        printLine();
        System.out.println(text.getText());
        printLine();
    }

    public void userInput() {
        String userText;
        userText = scanner.nextLine();
        if (userText.equals("bye")) {
            endChat();
        } else if (userText.equals("list")) {
            displayList();
        } else {
            generateResponse(new EchoText(userText));
            list.add(userText);
        }
    }

    public void displayList() {
        printLine();
        Iterator<String> iterator = list.iterator();
        int count = 0;
        while(iterator.hasNext()) {
            System.out.println(count + ". " + iterator.next());
            count++;
        }
        printLine();
    }

    public void endChat() {
        generateResponse(new EndText());
        isRunning = false;
    }

    public static void main(String[] args) {
        Bobby chatBot = new Bobby();
        while (isRunning) {
            chatBot.userInput();
        }
    }
}
