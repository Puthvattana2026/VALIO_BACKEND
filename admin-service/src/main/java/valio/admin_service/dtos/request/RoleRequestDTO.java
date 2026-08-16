package valio.admin_service.dtos.request;

import java.util.Set;
import java.util.UUID;

import lombok.Data;

@Data
public class RoleRequestDTO {
	String name;
	String description;
	Set<UUID> permissionIds;
}
