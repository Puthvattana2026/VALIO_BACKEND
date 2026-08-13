package valio.library_plateform.exceptions;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException{
    private String message;
}

