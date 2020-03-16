package feignforezp.config;

import feign.Retryer;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

/**
 * Created by JF on 2019/7/9.
 */
@Configurable
public class FeignConfig {
    @Bean
    public Retryer feignRetryer(){
        return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1),5);
    }
}
