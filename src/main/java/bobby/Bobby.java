package bobby;

import listmanager.ListManager;

import uimanager.UI;

import parser.Parser;


/**
 * A Chatbot that stores tasks. <code>Bobby</code> consists of a
 * <code>ListManager</code> that stores tasks, <code>UI</code> object that
 * manages responses and  a <code>Parser</code> that handles user input.
 *
 */
public class Bobby {
    private ListManager listManager;
    private Parser parser;
    private UI ui;

    /**
     * Sets isRunning to true.
     * Initializes ListManager, parser and ui instances.
     */
    public Bobby() {
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
    }


    public String run(String input) {
        /*try {
            boolean isAction = parser.parseInput(listManager, input);
            ui.printLine();

            if (!isAction) {
                endChat();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
        return "Hello";
    }
}
