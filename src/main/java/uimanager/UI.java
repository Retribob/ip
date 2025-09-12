package uimanager;

import texttypes.Text;
import texttypes.EndText;
import texttypes.StartText;


public class UI {

    public String onStart() {
        return new StartText().getText();
    }

    public String onEnd() {
        return new EndText().getText();
    }
}
