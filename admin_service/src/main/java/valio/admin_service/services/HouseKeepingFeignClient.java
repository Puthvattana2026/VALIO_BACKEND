package valio.admin_service.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import valio.admin_service.configs.FeignConfig;
import valio.admin_service.dtos.response.UsersResponseDTO;

@FeignClient(name = "valio-auth-service", contextId = "houseKeepingFeignClient", path = "/internal/users", configuration = FeignConfig.class)
public interface HouseKeepingFeignClient {
	
	@GetMapping
	List<UsersResponseDTO> getAllUsersByRole(@RequestParam(required = false) String role);
}
