package valio.auth_service.mappers;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import valio.auth_service.dtos.responses.UsersResponseDTO;
import valio.auth_service.entities.Register;
import valio.auth_service.entities.Role;

@Mapper(componentModel = "spring")
public interface UsersMapper {
	
	@Mapping(target = "roles", source = "roles", qualifiedByName = "rolesToNames")
	UsersResponseDTO toUsersResponseDTO(Register toRegister);
	
	@Named("rolesToNames")
    default Set<String> rolesToNames(Set<Role> roles) {
        if (roles == null) {
            return Set.of();
        }
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }
}
