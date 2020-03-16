package feignforezp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
@ComponentScan(basePackages = { "feignforezp.controller","feignforezp.config" })
public class FeignforezpApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignforezpApplication.class, args);
	}

}
