package valio.auth_service.configs.seeder;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import valio.auth_service.entities.Role;
import valio.auth_service.repositories.RoleRepository;

@Configuration
@RequiredArgsConstructor
public class GuestInitConfig implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        roleRepository.findByNameIgnoreCase("GUEST").ifPresentOrElse(
            existing -> {},
            () -> roleRepository.save(
                Role.builder()
                	.name("GUEST")
                	.description("The coming guest")
                	.permissions(Set.of())
                	.build()
            )
        );
    }
}