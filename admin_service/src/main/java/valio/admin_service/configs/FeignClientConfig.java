package valio.admin_service.configs;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Mono;

public class FeignClientConfig {

	private static final String BEARER_PREFIX = "Bearer ";
	private static final String ACCESS_TOKEN_COOKIE = "access_token";

	public static ExchangeFilterFunction propagateAuth() {
		return ExchangeFilterFunction.ofRequestProcessor(request -> {
			HttpServletRequest incomingRequest = currentRequest();
			if (incomingRequest == null) {
				return Mono.just(request);
			}
			String authHeader = incomingRequest.getHeader(HttpHeaders.AUTHORIZATION);
			String tokenToForward = null;
			if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
				tokenToForward = authHeader;
			} else {
				String cookieToken = extractAccessTokenCookie(incomingRequest);
				if (cookieToken != null) {
					tokenToForward = BEARER_PREFIX + cookieToken;
				}
			}
			if (tokenToForward == null) {return Mono.just(request);}
			String finalToken = tokenToForward;
			ClientRequest filtered = ClientRequest.from(request).headers(headers -> headers.set(HttpHeaders.AUTHORIZATION, finalToken)).build();
			return Mono.just(filtered);
		});
	}

	private static HttpServletRequest currentRequest() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attributes == null ? null : attributes.getRequest();
	}

	private static String extractAccessTokenCookie(HttpServletRequest request) {
		if (request.getCookies() == null) {
			return null;
		}
		for (Cookie cookie : request.getCookies()) {
			if (ACCESS_TOKEN_COOKIE.equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		return null;
	}
}