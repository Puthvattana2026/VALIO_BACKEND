package valio.admin_service.filters;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import valio.admin_service.utils.SignKey;

public class JwtVerifyFilter extends OncePerRequestFilter {

    private static final String BEARER_PREFIX = "Bearer ";
    private static final String ACCESS_TOKEN_COOKIE = "access_token";

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String accessToken = getAccessTokenFromCookie(request);

        if (accessToken == null) {
            accessToken = getAccessTokenFromHeader(request);
        }

        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Jws<Claims> claimJws = Jwts.parser()
                    .verifyWith(SignKey.getSecretKey())
                    .build()
                    .parseSignedClaims(accessToken);

            Claims body = claimJws.getPayload();
            String username = body.getSubject();

            List<String> authorities = body.get("authorities", List.class);
            Set<SimpleGrantedAuthority> grantedAuthorities = authorities == null
                    ? Set.of()
                    : authorities.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toSet());

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(username, accessToken, grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException | IllegalArgumentException ex) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

    private String getAccessTokenFromCookie(HttpServletRequest request) {
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

    private String getAccessTokenFromHeader(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            return null;
        }

        String token = authHeader.substring(BEARER_PREFIX.length()).trim();
        return token.isEmpty() ? null : token;
    }
}