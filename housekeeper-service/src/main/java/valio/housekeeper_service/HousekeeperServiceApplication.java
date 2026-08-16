package valio.housekeeper_service;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
public class HousekeeperServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HousekeeperServiceApplication.class, args);
	}

}
