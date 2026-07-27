package valio.auth_service.services;

public interface ClientCredentialsService {
	String authenticate(String clientId, String clientSecret);
}
