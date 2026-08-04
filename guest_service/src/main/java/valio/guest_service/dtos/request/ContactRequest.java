package valio.guest_service.dtos.request;

public record ContactRequest(String firstname, String lastName, String emailAddress, String message) {

}
