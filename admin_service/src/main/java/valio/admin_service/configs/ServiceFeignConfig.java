package valio.admin_service.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import valio.admin_service.utils.ServiceTokenProvider;

@Configuration
@RequiredArgsConstructor
public class ServiceFeignConfig {

    private final ServiceTokenProvider serviceTokenProvider;

    @Bean
    public RequestInterceptor serviceAuthInterceptor() {
        return template -> template.header(
            "Authorization", "Bearer " + serviceTokenProvider.getToken()
        );
    }
}