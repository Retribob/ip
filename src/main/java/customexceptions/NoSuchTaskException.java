package customExceptions;

public class NoSuchTaskException extends Exception {
    public NoSuchTaskException(String m) {
        super(m);
    }
}
