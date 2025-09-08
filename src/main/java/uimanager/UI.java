package uimanager;

import texttypes.Text;
import texttypes.EndText;
import texttypes.StartText;


public class UI {

    public UI() {
        onStart();
    }

    public void printLine() {
        System.out.println("________________________________________________________");
    }

    private void generateResponse(Text text) {
        System.out.println(text.getText());
        printLine();
    }

    private void onStart() {
        System.out.println(new StartText().getText());
        printLine();
    }

    public void onEnd() {
        System.out.println(new EndText().getText());
    }
}
