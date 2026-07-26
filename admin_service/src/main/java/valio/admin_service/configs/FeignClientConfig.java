package valio.admin_service.configs;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class FeignClientConfig implements ClientHttpRequestInterceptor {

    private static final String BEARER_PREFIX = "Bearer ";
    private static final String ACCESS_TOKEN_COOKIE = "access_token";

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        HttpServletRequest incomingRequest = currentRequest();
        if (incomingRequest != null) {
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

            if (tokenToForward != null) {
                request.getHeaders().set(HttpHeaders.AUTHORIZATION, tokenToForward);
            }
        }

        return execution.execute(request, body);
    }

    private HttpServletRequest currentRequest() {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes == null ? null : attributes.getRequest();
    }

    private String extractAccessTokenCookie(HttpServletRequest request) {
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