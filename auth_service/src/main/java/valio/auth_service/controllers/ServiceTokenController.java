package valio.auth_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import valio.auth_service.dtos.responses.TokenResponseDTO;
import valio.auth_service.services.ClientCredentialsService;

@RestController
@RequestMapping("/oauth2")
@RequiredArgsConstructor
public class ServiceTokenController {

    private final ClientCredentialsService clientCredentialsService;

    @PostMapping("/token")
    public ResponseEntity<TokenResponseDTO> issueToken(
            @RequestParam String client_id,
            @RequestParam String client_secret,
            @RequestParam String grant_type) {

        if (!"client_credentials".equals(grant_type)) {
            return ResponseEntity.badRequest().build();
        }

        String accessToken = clientCredentialsService.authenticate(client_id, client_secret);
        return ResponseEntity.ok(new TokenResponseDTO(accessToken, "Bearer", 300));
    }
}
