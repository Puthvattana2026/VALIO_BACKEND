package valio.auth_service.strategies.Jwt.impl;
import org.springframework.stereotype.Service;

import valio.auth_service.dtos.requests.LoginRequestDTO;
import valio.auth_service.strategies.Jwt.ValidationRuleStrategy;
import valio.library_plateform.exceptions.InvalidUsernamePasswordException;

@Service
public class UsernamePasswordValidationStrategy implements ValidationRuleStrategy<LoginRequestDTO> {
    @Override
    public void validate(LoginRequestDTO loginRequest) {
        if(loginRequest.getEmail() == null ||
                loginRequest.getEmail().isEmpty() ||
                loginRequest.getPassword() == null ||
                loginRequest.getPassword().isEmpty()) {
            throw new InvalidUsernamePasswordException();
        }
    }
}
