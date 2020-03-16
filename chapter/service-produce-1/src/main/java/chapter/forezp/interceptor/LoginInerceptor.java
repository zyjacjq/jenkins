package chapter.forezp.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginInerceptor implements HandlerInterceptor{


	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
		// TODO Auto-generated method stub
		
	}


	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub


	}


	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		boolean flag;


 //决定会话是否在username
		String username=(String)request.getSession().getAttribute("username");


//如果会话不存在username,那么定义向到 "/"
		if (username== null) {
			request.getRequestDispatcher("/login").forward(request, response);
			flag=false;
		}
		
		else {
			
			flag=true;
		}
		
		
		return flag;
	}


}