package bobby;

import listmanager.ListManager;

import uimanager.UI;

import parser.Parser;

import java.util.Scanner;

/**
 * A Chatbot that stores tasks. <code>Bobby</code> consists of a
 * <code>ListManager</code> that stores tasks, <code>UI</code> object that
 * manages responses and  a <code>Parser</code> that handles user input.
 *
 */
public class Bobby {
    private static boolean isRunning;
    private Scanner scanner;
    private ListManager listManager;
    private Parser parser;
    private UI ui;

    /**
     * Sets isRunning to true.
     * Initializes ListManager, parser and ui instances.
     */
    public Bobby() {
        isRunning = true;
        parser = new Parser();
        listManager = new ListManager();
        ui = new UI();
    }

    /**
     * Sets boolean isRunning to false.
     * Closes UI and ListManager instances.
     */
    public void endChat() {
        listManager.closeList();
        ui.onEnd();
        isRunning = false;
    }

    private void run() {
        while (isRunning) {
            try {
                boolean isAction = parser.parseInput(listManager);
                ui.printLine();

                if (!isAction) {
                    endChat();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Bobby chatBot = new Bobby();
        chatBot.run();
    }
}
