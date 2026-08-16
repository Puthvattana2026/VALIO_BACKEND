package valio.auth_service.strategies.Jwt.impl;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import valio.auth_service.strategies.Jwt.ValidationRuleStrategy;
import valio.library_plateform.exceptions.ContentLengthException;

@Service
public class ContentLengthValidationStrategy implements ValidationRuleStrategy<HttpServletRequest> {

    @Override
    public void validate(HttpServletRequest request) {
        if (request.getContentLength() <= 0) {
            throw new ContentLengthException();
        }
    }
}
