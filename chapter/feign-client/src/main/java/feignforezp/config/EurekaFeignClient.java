package feignforezp.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by JF on 2019/7/9.
 */
@FeignClient(value = "service-hii",configuration = FeignConfig.class)
public interface EurekaFeignClient {
    @RequestMapping("/hi")
    String sayHiFromClientEureka(@RequestParam(value = "name") String name);
}
