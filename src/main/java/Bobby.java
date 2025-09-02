
import ListManager.ListManager;

import UIManager.UI;

import Parser.Parser;

import java.util.Scanner;


public class Bobby {
    private static boolean isRunning;
    private Scanner scanner;
    private ListManager listManager;
    private Parser parser;
    private UI ui;

    public Bobby() {
        isRunning = true;
        parser = new Parser();
        listManager = new ListManager();
        ui = new UI();
    }


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
