package valio.admin_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import valio.admin_service.services.TaskAssignService;

@RestController
@RequestMapping("/internal/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class TaskAssignController {
	
	private final TaskAssignService assignService;
	
//	public ResponseEntity<?> assignTo(@RequestBody ){
//		
//	}

}
