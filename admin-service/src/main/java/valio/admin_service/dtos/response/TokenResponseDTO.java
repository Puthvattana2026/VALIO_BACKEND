package valio.admin_service.dtos.response;

public record TokenResponseDTO(
	    String accessToken,
	    String tokenType,
	    long expiresIn
	) {}