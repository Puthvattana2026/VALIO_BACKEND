package valio.library_plateform.exceptions;

import lombok.Data;

@Data
public class InvalidRefreshTokenException extends RuntimeException{
    private String message;
}
