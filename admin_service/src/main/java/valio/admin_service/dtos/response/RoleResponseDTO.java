package valio.admin_service.dtos.response;

import java.util.Set;
import java.util.UUID;

import lombok.Data;

@Data
public class RoleResponseDTO {
	private UUID id;
	private String name;
	private String description;
	private Set<PermissionResponseDTO> permissions;
}
