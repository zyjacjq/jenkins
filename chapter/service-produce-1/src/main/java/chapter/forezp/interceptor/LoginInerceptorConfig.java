package chapter.forezp.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SuppressWarnings("deprecation")
@Configuration


public class LoginInerceptorConfig extends WebMvcConfigurerAdapter{


	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
	 //一定要注单excludePathPatterns和addPathPatterns不要冲突了
//		registry.addInterceptor(new LoginInerceptor()).excludePathPatterns("/hello","/").addPathPatterns("/*");
	}
	
	


}