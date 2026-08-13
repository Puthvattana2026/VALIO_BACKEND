package valio.library_plateform.exceptions;

public class InvalidUsernamePasswordException extends ApiException{
    public InvalidUsernamePasswordException() {
        super("Username and password are required.");
    }
}

