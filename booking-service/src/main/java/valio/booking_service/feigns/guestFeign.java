package valio.booking_service.feigns;

import org.springframework.cloud.openfeign.FeignClient;

import valio.booking_service.configs.FeignConfig;

@FeignClient(name = "valio-booking-service", configuration = FeignConfig.class)
public interface guestFeign {
	
	
}
