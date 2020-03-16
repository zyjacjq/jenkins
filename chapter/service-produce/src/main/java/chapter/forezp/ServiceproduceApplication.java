package chapter.forezp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ServiceproduceApplication {
	@Value("${server.port}")
	String port;

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@RequestMapping("/hi")
	public String home(@RequestParam String name)
	{
		return "hi " + name + ",i am from port:" + port;
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceproduceApplication.class, args);
	}

	@RequestMapping("/ll")
	public String callHome(){
		return restTemplate.getForObject("http://localhost:8764/getMember", String.class);
	}

}
