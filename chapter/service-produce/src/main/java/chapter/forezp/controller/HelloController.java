package chapter.forezp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JF on 2019/7/9.
 */
@RestController
public class HelloController {
    @RequestMapping("/getMember")
    public String getMember(){
        List<String> list = new ArrayList<String>();
        list.add("zhangsan");
        list.add("zhangsan1");
        list.add("zhangsan2");
        list.add("zhangsan3");
        list.add("8763");
        return list.toString();
    }
}
