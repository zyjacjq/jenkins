package chapter.forezp;

import chapter.forezp.entity.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages={"chapter.forezp"})
@EnableEurekaClient
@RestController
@MapperScan("chapter.forezp.dao")
@ServletComponentScan

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
		return restTemplate.getForObject("http://localhost:8763/getMember", String.class);
	}

	//测试无参的get请求
	@RequestMapping(value = "/testAspect",method = RequestMethod.GET)
	public User test(){
		User userVo = new User();
		userVo.setPassword("123");
		userVo.setUsername("age");
		return userVo;
	}
	//测试有参的get请求,让aop打印参数内容
	@RequestMapping(value = "/testAspectArgs",method = RequestMethod.GET)
	public User test(String name, String age, String sex){
		User userVo = new User();
		userVo.setPassword(name);
		userVo.setUsername(age);
		return userVo;
	}

}
