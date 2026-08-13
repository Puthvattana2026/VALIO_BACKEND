package valio.library_plateform.exceptions;

import lombok.Data;

@Data
public class DuplicateRoleException extends RuntimeException{
	String message;
}
