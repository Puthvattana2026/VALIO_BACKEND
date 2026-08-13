package valio.library_plateform.exceptions;

public class ContentLengthException extends ApiException {
    public ContentLengthException() {
        super("Request body is required");
    }
}
