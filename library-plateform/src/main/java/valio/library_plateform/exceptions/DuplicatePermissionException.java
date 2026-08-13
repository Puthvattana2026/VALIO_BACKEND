package valio.library_plateform.exceptions;

import lombok.Data;

@Data
public class DuplicatePermissionException extends RuntimeException{
	private String message;
}
