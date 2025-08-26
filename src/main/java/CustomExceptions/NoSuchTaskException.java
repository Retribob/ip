package CustomExceptions;

public class NoSuchTaskException extends Exception{
    public NoSuchTaskException(String m) {
        super(m);
    }
}
