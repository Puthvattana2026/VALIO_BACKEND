package valio.auth_service.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import valio.auth_service.dtos.responses.UsersResponseDTO;
import valio.auth_service.entities.Register;
import valio.auth_service.mappers.UsersMapper;
import valio.auth_service.repositories.RegisterRepository;
import valio.auth_service.services.UsersService;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService{
	
	private final RegisterRepository registerRepository;
	private final UsersMapper usersMapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<UsersResponseDTO> usersFeign(String role) {
		 List<Register> users = (role != null) ? registerRepository.findByRoles_Name(role) : registerRepository.findAll();
	
	    return users.stream()
	            .map(this::toUsersResponseDTO)
	            .toList();
	}

	private UsersResponseDTO toUsersResponseDTO(Register register) {
		return usersMapper.toUsersResponseDTO(register);
	}
}
