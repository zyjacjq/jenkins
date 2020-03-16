package getaway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GetawayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetawayApplication.class, args);
	}

//	@Bean
//	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route(p -> p
//						.path("/get")
//						.filters(f -> f.addRequestHeader("Hello", "World"))
//						.uri("http://www.baidu.com"))
//				.build();
//	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("baidu_route", r -> r.path("/baidu")
						.filters(f -> f.addResponseHeader("hello", "Gateway"))
						.uri("http://baidu.com:6666")
						.order(1))

				.build();
	}
}
