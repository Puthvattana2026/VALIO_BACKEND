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
    						  .path("/valio/admin/**")
    						  .filters(f -> f.rewritePath("/valio/admin/(?<segment>.*)", "/${segment}"))
    						  .uri("lb://VALIO-ADMIN-SERVICE"))
    				  
    			.build();
    }
}