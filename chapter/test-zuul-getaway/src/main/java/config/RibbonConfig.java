package config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by JF on 2019/9/3.
 */
@Configuration
public class RibbonConfig {
//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }

    @Bean
    public IRule ribbonRile(){
        return new RandomRule();
    }

}
