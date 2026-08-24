package valio.apigateway_service.configs;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteCustomizeConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
    	return builder.routes()
	    				  .route(p -> p
	    						  .path("/valio/auth/**")
	    						  .filters(f -> f.rewritePath("/valio/auth/(?<segment>.*)", "/${segment}"))
	    						  .uri("lb://VALIO-AUTH-SERVICE"))
	    				  .route(p -> p
	    						  .path("/valio/admin/**")
	    						  .filters(f -> f.rewritePath("/valio/admin/(?<segment>.*)", "/${segment}"))
	    						  .uri("lb://VALIO-ADMIN-SERVICE"))
	    				  .route(p -> p
	    						  .path("/valio/guest/**")
	    						  .filters(f -> f.rewritePath("/valio/guest/(?<segment>.*)", "/${segment}"))
	    						  .uri("lb://VALIO-GUEST-SERVICE"))
	    				  .route(p -> p
	    						  .path("/valio/housekeeper/**")
	    						  .filters(f -> f.rewritePath("/valio/housekeeper/(?<segment>.*)", "/${segment}"))
	    						  .uri("lb://VALIO-HOUSEKEEPER-SERVICE"))
	    				  .route(p -> p
	    						  .path("/valio/receptionist/**")
	    						  .filters(f -> f.rewritePath("/valio/receptionist/(?<segment>.*)", "/${segment}"))
	    						  .uri("lb://VALIO-RECEPTIONIST-SERVICE"))
	    			.build();
    }
}