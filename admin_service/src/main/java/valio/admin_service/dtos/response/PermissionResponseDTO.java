package valio.admin_service.dtos.response;

import java.util.UUID;

import lombok.Data;

@Data
public class PermissionResponseDTO {
	UUID id;
	String name;
	String description;
}
