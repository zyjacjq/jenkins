package controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {
 
  private final Logger log = LoggerFactory.getLogger(MovieController.class);
 
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private LoadBalancerClient loadBalancerClient;
 
  @GetMapping("/hii")
  public String findById(@RequestParam String name){
    return this.restTemplate.getForObject("http://service-hi/hi?name="+name,String.class);
  }

  @GetMapping("/getMember")
  public String getMember(){
    return this.restTemplate.getForObject("http://service-hi/getMember",String.class);
  }

  @GetMapping("/service/hi")
  @HystrixCommand(fallbackMethod = "hirError",
          commandProperties={
                  @HystrixProperty(name="circuitBreaker.enabled" , value = "false"),//设置熔断
                  @HystrixProperty(name="circuitBreaker.requestVolumeThreshold" , value = "10"),//断路器的最小请求数
                  @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds" , value = "10000"),//休眠时间
                  @HystrixProperty(name="circuitBreaker.errorThresholdPercentage" , value = "60"),//断路频率

          })
  public String getServiceHi(@RequestParam String name){
    return this.restTemplate.getForObject("http://service-hi/hi?name="+name,String.class);
  }

  public String hirError(String name){
    return "服务崩了";
  }




}