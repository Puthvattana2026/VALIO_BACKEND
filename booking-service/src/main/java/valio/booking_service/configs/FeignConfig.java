package valio.booking_service.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import feign.RequestInterceptor;

@Configuration
public class FeignConfig {
	
	@Bean
	public RequestInterceptor forwardJwt() {
		return template -> {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if( auth != null && auth.getCredentials() instanceof String token) {
				template.header("Authorization", "Bearer " + token);
			}
		};
	}
}
