package feignforezp.controller;

import feignforezp.config.EurekaFeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by JF on 2019/7/9.
 */
@RestController
public class HiController {

    @Resource
    private EurekaFeignClient eurekaFeignClient;

    @RequestMapping("/hi")
    public String sayHi(@RequestParam(defaultValue = "zyj",required = false) String name){
        return eurekaFeignClient.sayHiFromClientEureka(name);

    }

}
