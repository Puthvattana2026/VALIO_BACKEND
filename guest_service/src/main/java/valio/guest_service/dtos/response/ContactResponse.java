package valio.guest_service.dtos.response;

import java.util.UUID;

public record ContactResponse(UUID id, String firstName, String lastName, String emailAddress, String message) {

}
