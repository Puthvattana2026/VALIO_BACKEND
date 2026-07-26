package valio.admin_service.services;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import valio.admin_service.configs.FeignConfig;
import valio.admin_service.dtos.request.PermissionRequestDTO;
import valio.admin_service.dtos.request.RoleRequestDTO;
import valio.admin_service.dtos.response.PermissionResponseDTO;
import valio.admin_service.dtos.response.RoleResponseDTO;

@FeignClient(name = "valio-auth-service", path = "/admin", configuration = FeignConfig.class)
public interface RolePermissionFeignClient {
	
	/* 
    |=================
    | ROLE CRUD
    |=================
 	*/
    @PostMapping("/roles")
    ResponseEntity<RoleResponseDTO> createRole(@RequestBody RoleRequestDTO request);

    @PutMapping("/roles/{roleId}")
    ResponseEntity<RoleResponseDTO> updateRole(@PathVariable UUID roleId, @RequestBody RoleRequestDTO request);

    @DeleteMapping("/roles/{roleId}")
    ResponseEntity<Void> deleteRole(@PathVariable UUID roleId);

    @GetMapping("/roles/{roleId}")
    ResponseEntity<RoleResponseDTO> getRole(@PathVariable UUID roleId);

    @GetMapping("/roles")
    ResponseEntity<List<RoleResponseDTO>> listRoles();

	/* 
    |=================
    | PERMISSION CRUD
    |=================
 	*/

    @PostMapping("/permissions")
    ResponseEntity<PermissionResponseDTO> createPermission(@RequestBody PermissionRequestDTO request);

    @PutMapping("/permissions/{id}")
    ResponseEntity<PermissionResponseDTO> updatePermission(@PathVariable UUID id, @RequestBody PermissionRequestDTO request);

    @DeleteMapping("/permissions/{id}")
    ResponseEntity<Void> deletePermission(@PathVariable UUID id);

    @GetMapping("/permissions/{id}")
    ResponseEntity<PermissionResponseDTO> getPermission(@PathVariable UUID id);

    @GetMapping("/permissions")
    ResponseEntity<List<PermissionResponseDTO>> listPermissions();

	/* 
    |=================
    | ASSIGNMENT
    |=================
 	*/

    @PostMapping("/roles/{roleId}/permissions/{permissionId}")
    ResponseEntity<RoleResponseDTO> attachPermission(@PathVariable UUID roleId, @PathVariable UUID permissionId);

    @DeleteMapping("/roles/{roleId}/permissions/{permissionId}")
    ResponseEntity<RoleResponseDTO> detachPermission(@PathVariable UUID roleId, @PathVariable UUID permissionId);
}
