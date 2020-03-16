package chapter.forezp.controller;

import chapter.forezp.service.impl.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by JF on 2019/9/16.
 */
@Controller
public class RedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/addKey/{key}/{value}")
    @ResponseBody
    public boolean addKey(@PathVariable String key,@PathVariable String value){
        return redisService.set(key,value);
    }

    @RequestMapping("/addKeyTime/{key}/{value}/{time}")
    @ResponseBody
    public boolean addKeyTime(@PathVariable String key,@PathVariable String value,@PathVariable long time){
        return redisService.set(key,value,time);
    }

    @RequestMapping("/getKey/{key}")
    @ResponseBody
    public String addKey(@PathVariable String key){
        return redisService.get(key).toString();
    }
}
