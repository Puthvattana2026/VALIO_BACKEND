package valio.auth_service.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import valio.auth_service.dtos.responses.UsersResponseDTO;
import valio.auth_service.services.UsersService;

@RestController
@RequestMapping("/internal/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UsersController {
	
	private final UsersService usersServiceImpl;
	
	@GetMapping
	public ResponseEntity<List<UsersResponseDTO>> UsersFeign(@RequestParam(required = false) String role){
		return ResponseEntity.ok(usersServiceImpl.usersFeign(role));
	}
}
