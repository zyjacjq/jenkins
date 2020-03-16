package service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by JF on 2019/9/3.
 */
//@Component
@FeignClient(value = "service-hii")
//@Service
public interface HelloService {

    @RequestMapping("/hi")
    public String sayName(@RequestParam(value = "name") String name);
}
