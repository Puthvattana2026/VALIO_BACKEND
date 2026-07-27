package valio.admin_service.utils;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import valio.admin_service.dtos.response.TokenResponseDTO;

@Component
public class ServiceTokenProvider {

    private final RestClient restClient = RestClient.create();
    private volatile String cachedToken;
    private volatile Instant expiresAt = Instant.EPOCH;

    @Value("${service.client-id}")
    private String clientId;
    @Value("${service.client-secret}")
    private String clientSecret;

    public synchronized String getToken() {
        if (cachedToken == null || Instant.now().isAfter(expiresAt.minusSeconds(30))) {
            refreshToken();
        }
        return cachedToken;
    }

    private void refreshToken() {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);
        form.add("grant_type", "client_credentials");

        TokenResponseDTO response = restClient.post()
            .uri("http://valio-auth-service/oauth2/token")
            .body(form)
            .retrieve()
            .body(TokenResponseDTO.class);

        this.cachedToken = response.accessToken();
        this.expiresAt = Instant.now().plusSeconds(response.expiresIn());
    }
}
