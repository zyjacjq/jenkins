package chapter.forezp.listence;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @RequestMapping("/count")
    @ResponseBody
    public String count(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        HttpSession session = httpServletRequest.getSession();
        session.setMaxInactiveInterval(30);
        Object count = session.getServletContext().getAttribute("count");
        return "count : " + count;
    }
}