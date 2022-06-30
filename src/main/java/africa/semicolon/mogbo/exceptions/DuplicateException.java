package africa.semicolon.mogbo.exceptions;

public class DuplicateException extends EmailNotFoundException{
    public DuplicateException(String message) {
        super(message);
    }
}
