package valio.admin_service.dtos.response;

import java.util.Set;
import java.util.UUID;

public record UsersResponseDTO(
	    UUID id,
	    String username,
	    String email,
	    Set<String> roles,
	    Boolean isEnabled
	) {}

