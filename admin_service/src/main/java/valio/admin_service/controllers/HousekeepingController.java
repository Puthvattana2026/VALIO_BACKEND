package valio.admin_service.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import valio.admin_service.dtos.response.UsersResponseDTO;
import valio.admin_service.services.HouseKeepingFeignClient;

@RestController
@RequestMapping("/internal/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class HousekeepingController {
	
	private final HouseKeepingFeignClient houseKeepingFeignClient;
	
	@GetMapping
	public ResponseEntity<List<UsersResponseDTO>> getAllUsersByRole(@RequestParam(required = false) String role){
		return houseKeepingFeignClient.getAllUsersByRole(role);
	};
}
