package valio.admin_service.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import valio.admin_service.dtos.request.PermissionRequestDTO;
import valio.admin_service.dtos.request.RoleRequestDTO;
import valio.admin_service.dtos.response.PermissionResponseDTO;
import valio.admin_service.dtos.response.RoleResponseDTO;
import valio.admin_service.feign.RolePermissionFeignClient;

@RestController
@RequestMapping("/assign")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminRolePermissionController {

    private final RolePermissionFeignClient rolePermissionFeignClient;

    /*
    |=================
    | ROLE CRUD
    |=================
    */

    @PostMapping("/roles")
    public ResponseEntity<RoleResponseDTO> createRole(@RequestBody RoleRequestDTO request) {
        return rolePermissionFeignClient.createRole(request);
    }

    @PutMapping("/roles/{roleId}")
    public ResponseEntity<RoleResponseDTO> updateRole(@PathVariable UUID roleId, @RequestBody RoleRequestDTO request) {
        return rolePermissionFeignClient.updateRole(roleId, request);
    }

    @DeleteMapping("/roles/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable UUID roleId) {
        return rolePermissionFeignClient.deleteRole(roleId);
    }

    @GetMapping("/roles/{roleId}")
    public ResponseEntity<RoleResponseDTO> getRole(@PathVariable UUID roleId) {
        return rolePermissionFeignClient.getRole(roleId);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleResponseDTO>> listRoles() {
        return rolePermissionFeignClient.listRoles();
    }

    /*
    |=================
    | PERMISSION CRUD
    |=================
    */

    @PostMapping("/permissions")
    public ResponseEntity<PermissionResponseDTO> createPermission(@RequestBody PermissionRequestDTO request) {
        return rolePermissionFeignClient.createPermission(request);
    }

    @PutMapping("/permissions/{id}")
    public ResponseEntity<PermissionResponseDTO> updatePermission(@PathVariable UUID id, @RequestBody PermissionRequestDTO request) {
        return rolePermissionFeignClient.updatePermission(id, request);
    }

    @DeleteMapping("/permissions/{id}")
    public ResponseEntity<Void> deletePermission(@PathVariable UUID id) {
        return rolePermissionFeignClient.deletePermission(id);
    }

    @GetMapping("/permissions/{id}")
    public ResponseEntity<PermissionResponseDTO> getPermission(@PathVariable UUID id) {
        return rolePermissionFeignClient.getPermission(id);
    }

    @GetMapping("/permissions")
    public ResponseEntity<List<PermissionResponseDTO>> listPermissions() {
        return rolePermissionFeignClient.listPermissions();
    }

    /*
    |=================
    | ASSIGNMENT
    |=================
    */

    @PostMapping("/roles/{roleId}/permissions/{permissionId}")
    public ResponseEntity<RoleResponseDTO> attachPermission(@PathVariable UUID roleId, @PathVariable UUID permissionId) {
        return rolePermissionFeignClient.attachPermission(roleId, permissionId);
    }

    @DeleteMapping("/roles/{roleId}/permissions/{permissionId}")
    public ResponseEntity<RoleResponseDTO> detachPermission(@PathVariable UUID roleId, @PathVariable UUID permissionId) {
        return rolePermissionFeignClient.detachPermission(roleId, permissionId);
    }
}
