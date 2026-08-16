package valio.booking_service.configs;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.HttpServletResponse;
import valio.booking_service.filters.JwtVerifyFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Bean
	private SecurityFilterChain securityFilterChain(HttpSecurity http) {
		return http
				.cors(null)
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(rq -> rq
						.requestMatchers(HttpMethod.GET, "/").permitAll()
						.anyRequest().authenticated())
                .addFilterBefore(new JwtVerifyFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json");
                            response.getWriter().write(
                                """
                                {
                                    "status": "UNAUTHORIZED",
                                    "message": "Please sign in to continue."
                                }
                                """);
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json");
                            response.getWriter().write(
                                """
                                {
                                    "status": "FORBIDDEN",
                                    "message": "You do not have permission to perform this action."
                                }
                                """);
                        }))
                .build();
	}
	
	@Bean
	private CorsConfigurationSource configurationSource() {
		CorsConfiguration config = new CorsConfiguration();
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("Authorization", "Content-Disposition"));
        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(List.of("*"));
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return (CorsConfigurationSource) source;
	}
}
