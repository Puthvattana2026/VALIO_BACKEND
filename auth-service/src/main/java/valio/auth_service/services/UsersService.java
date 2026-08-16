package valio.auth_service.services;

import java.util.List;

import valio.auth_service.dtos.responses.UsersResponseDTO;

public interface UsersService {
	List<UsersResponseDTO> usersFeign(String role);
}
