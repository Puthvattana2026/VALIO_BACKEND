package valio.auth_service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import valio.auth_service.dtos.responses.UsersResponseDTO;
import valio.auth_service.entities.Register;

@Mapper(componentModel = "spring")
public interface UsersMapper {
	
	@Mapping(target = "roles" , ignore = true)
	UsersResponseDTO toUsersResponseDTO(Register toRegister);
}
