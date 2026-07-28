package valio.auth_service.services.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import valio.auth_service.dtos.responses.UsersResponseDTO;
import valio.auth_service.entities.Register;
import valio.auth_service.entities.Role;
import valio.auth_service.mappers.UsersMapper;
import valio.auth_service.repositories.RegisterRepository;
import valio.auth_service.services.UsersService;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService{
	
	private final RegisterRepository registerRepository;
	private final UsersMapper usersMapper;
	
	@Override
	public List<UsersResponseDTO> getAllUsers(String role) {
		 List<Register> users = (role != null) ? registerRepository.findByRoles_Name(role) : registerRepository.findAll();
	
	    return users.stream()
	            .map(this::toUsersResponseDTO)
	            .toList();
	}

	private UsersResponseDTO toUsersResponseDTO(Register register) {
		UsersResponseDTO dto = usersMapper.toUsersResponseDTO(register);
		return new UsersResponseDTO(
	            dto.id(),
	            dto.username(),
	            dto.email(),
	            roles(register.getRoles()),
	            dto.isEnabled()
	    );
	}

	private Set<String> roles (Set<Role> roles){
		if(roles == null) return Set.of();
		return roles.stream()
				    .map(Role::getName)
				    .collect(Collectors.toSet());
	}
}
