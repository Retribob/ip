package textTypes;

public class EchoText extends Text {
    private String echo;

    public EchoText(String echo) {
        this.echo = echo;
    }

    @Override
    public String getText() {
        return "added: " + this.echo;
    }
}
