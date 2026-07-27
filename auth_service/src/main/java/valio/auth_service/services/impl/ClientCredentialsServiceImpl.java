package valio.auth_service.services.impl;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import valio.auth_service.exceptions.InvalidClientCredentialsException;
import valio.auth_service.properties.ServiceClientProperties;
import valio.auth_service.services.ClientCredentialsService;
import valio.auth_service.utils.SignKey;

@Service
@RequiredArgsConstructor
public class ClientCredentialsServiceImpl implements ClientCredentialsService{

    private final ServiceClientProperties serviceClientProperties;
    private final PasswordEncoder passwordEncoder;

    private static final long SERVICE_TOKEN_TTL_SECONDS = 300; // 5 min, short-lived

    public String authenticate(String clientId, String clientSecret) {
        ServiceClientProperties.Client client = findClient(clientId)
            .orElseThrow(InvalidClientCredentialsException::new);

        if (!matchesSecret(clientSecret, client.getClientSecret())) {
            throw new InvalidClientCredentialsException();
        }

        List<String> authorities = client.getScopes().stream()
            .map(scope -> "SCOPE_" + scope)
            .toList();

        Instant now = Instant.now();
        return Jwts.builder()
            .subject(client.getClientId())
            .claim("authorities", authorities)
            .claim("token_type", "service")
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plusSeconds(SERVICE_TOKEN_TTL_SECONDS)))
            .issuer("Valio")
            .signWith(SignKey.getSecretKey())
            .compact();
    }

    private Optional<ServiceClientProperties.Client> findClient(String clientId) {
        return serviceClientProperties.getClients().stream()
            .filter(c -> c.getClientId().equals(clientId))
            .findFirst();
    }

    private boolean matchesSecret(String raw, String configured) {
        return configured.equals(raw);
    }
}