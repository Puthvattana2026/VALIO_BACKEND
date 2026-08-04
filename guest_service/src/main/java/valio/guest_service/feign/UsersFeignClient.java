package valio.guest_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import valio.guest_service.configs.FeignConfig;
import valio.guest_service.dtos.response.UsersResponseDTO;

@FeignClient(name = "valio-guest-service", contextId = "guestFeignClient", path = "/internal/users", configuration = FeignConfig.class)
public interface UsersFeignClient {
	
	@GetMapping
	List<UsersResponseDTO> getAllUsersByRole(@RequestParam(required = false) String role);
}

