package chapter.forezp.controller;

import chapter.forezp.config.TestClickHouse;
import chapter.forezp.dao.UserMapper;
import chapter.forezp.entity.User;
import chapter.forezp.service.HelloService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by JF on 2019/7/9.
 */
@Controller
public class HelloController {
    private static Logger log = Logger.getLogger(HelloController.class.toString());
    @Autowired
    HelloService helloService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    TestClickHouse testClickHouse;


    @Resource
    private ResourceLoader resourceLoader;


    @RequestMapping("/getMember/{idx}")
    public String getMember(@PathVariable Integer idx){
        List<String> list = new ArrayList<String>();
        list.add("zhangsan");
        list.add("zhangsan1");
        list.add("zhangsan2");
        list.add("zhangsan3");
        list.add("8764");
        Map map =new HashMap();
        map.put("idx",idx);
        helloService.getresult(map);
        List resultlist = helloService.resultlist();
        Object sum = map.get("sum");
        return "hello";
    }
    @RequestMapping("/getUser/{pageSize}/{currenPage}")
    public String getUserList(@PathVariable int pageSize,@PathVariable int currenPage){
        return helloService.getAllUser1(currenPage,pageSize);
    }

    @RequestMapping("/getUserByusername/{username}")
    public User getUserByusername(@PathVariable String username){
        return helloService.getUserByUsername(username);
    }

    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response, HttpServletRequest request) {
        InputStream inputStream = null;
        ServletOutputStream servletOutputStream = null;
        try {
            String filename = "外包人员信息导入模板.xlsx";
            String path = "mappers/userMapper.xml";
            org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:"+path);
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.addHeader("charset", "utf-8");
            response.addHeader("Pragma", "no-cache");
            String encodeName = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodeName + "\"; filename*=utf-8''" + encodeName);

            inputStream = resource.getInputStream();
            servletOutputStream = response.getOutputStream();
            IOUtils.copy(inputStream, servletOutputStream);
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (servletOutputStream != null) {
                    servletOutputStream.close();
                    servletOutputStream = null;
                }
                if (inputStream != null) {
                    inputStream.close();
                    inputStream = null;
                }
                // 召唤jvm的垃圾回收器
                System.gc();
            } catch (Exception e) {
//                e.printStackTrace();
                log.info(e.getMessage());
            }
        }
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
//        List<Map> maps = testClickHouse.exeSql("select * from user");
//        return userMapper.getAllUser().toString();
        return userMapper.getAllUser().toString();
    }

    @RequestMapping("/login")
    public String login(){
        return "webscoket";
    }

}
