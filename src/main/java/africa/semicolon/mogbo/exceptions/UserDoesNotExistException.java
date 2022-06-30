package africa.semicolon.mogbo.exceptions;

public class UserDoesNotExistException extends EmailNotFoundException {
    public UserDoesNotExistException(String s) {
        super(s);
    }
}
