package valio.auth_service.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "service-clients")
@Data
public class ServiceClientProperties {
    private List<Client> clients;

    @Data
    public static class Client {
        private String clientId;
        private String clientSecret;
        private List<String> scopes;
    }
}

