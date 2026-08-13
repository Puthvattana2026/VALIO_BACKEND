package valio.library_plateform.exceptions;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }
}

