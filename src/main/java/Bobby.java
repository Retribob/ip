import TextTypes.*;
import java.util.Scanner;

public class Bobby {
    private static boolean isRunning;
    private Scanner scanner;

    public Bobby() {
        isRunning = true;
        scanner = new Scanner(System.in);

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
        String echoText;
        echoText = scanner.nextLine();

        if (echoText.equals("bye")) {
            endChat();
        } else {
            generateResponse(new EchoText(echoText));
        }
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
