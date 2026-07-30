package valio.auth_service.services.impl;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import valio.auth_service.entities.Register;
import valio.auth_service.entities.Role;
import valio.auth_service.repositories.RegisterRepository;
import valio.auth_service.repositories.RoleRepository;
import valio.auth_service.services.RegisterService;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final RegisterRepository registerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final String DEFAULT_ROLE = "GUEST";

    @Override
    public Register register(Register userRegister) {
	    Role guestRole = roleRepository.findByNameIgnoreCase(DEFAULT_ROLE).orElseThrow(() -> new IllegalStateException("Oops"));
        userRegister.setRoles(Set.of(guestRole));
        userRegister.setPassword(passwordEncoder.encode(userRegister.getPassword()));
        return registerRepository.save(userRegister);
    }
}
