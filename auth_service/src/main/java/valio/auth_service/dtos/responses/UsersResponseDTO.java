package valio.auth_service.dtos.responses;

import java.util.Set;
import java.util.UUID;

import lombok.Data;
import valio.auth_service.entities.Role;

public record UsersResponseDTO(
	    UUID id,
	    String username,
	    String email,
	    Set<String> roles,
	    Boolean isEnabled
	) {}
