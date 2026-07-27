package valio.auth_service.dtos.responses;

public record TokenResponseDTO(
	    String accessToken,
	    String tokenType,
	    long expiresIn
	) {}
