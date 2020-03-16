package chapter.forezp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.logging.Logger;

/**
 * 项目名:SpringBootDemo
 * 创建人:贺小五
 * 创建时间:16/12/4 下午7:05
 * 类名:AspectDemo
 * 类描述:
 */
//申明是个切面
@Aspect
//申明是个spring管理的bean
@Component
@Order(1)
public class AspectDemo {
Logger logger=Logger.getLogger(AspectDemo.class.toString());
    //申明一个切点 里面是 execution表达式
    @Pointcut("execution(public * chapter.forezp.ServiceproduceApplication.*(..))")
    private void controllerAspect() {
        System.out.println("hksdkghdksghgfdkgsk");
    }

    //请求method前打印内容
    @Before(value = "controllerAspect()")
    public void methodBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = requestAttributes.getRequest();
        //打印请求内容
        System.out.println("===============请求内容===============");
//        System.out.println("请求地址:" + request.getRequestURL().toString());
//        System.out.println("请求方式:" + request.getMethod());
//        System.out.println("请求类方法:" + joinPoint.getSignature());
//        System.out.println("请求类方法参数:" + Arrays.toString(joinPoint.getArgs()));
        System.out.println("===============请求内容===============");
    }

    //在方法执行完结后打印返回内容
    @AfterReturning(returning = "o", pointcut = "controllerAspect()")
    public void methodAfterReturing(Object o) {
        System.out.println("--------------返回内容----------------");
//        System.out.println("Response内容:" + gson.toJson(o));
//        System.out.println("--------------返回内容----------------");
        logger.info("--------------返回内容----------------");
    }
}