import TextTypes.*;

public class Bobby {
    private static boolean isRunning;

    public Bobby() {
        isRunning = true;
    }

    public void printLine() {
        System.out.println("________________________________________________________");
    }

    public void generateResponse(Text text) {
        printLine();
        System.out.println(text.getText());
        printLine();
    }


    public static void main(String[] args) {
        Bobby chatBot = new Bobby();
        chatBot.generateResponse(new StartText());
        chatBot.generateResponse(new EndText());
        isRunning = false;

    }
}
