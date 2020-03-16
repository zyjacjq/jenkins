package webscoket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigure
{
    @Bean
    public MyEndpointConfigure newConfigure()
    {
        return new MyEndpointConfigure();
    }

//    @Bean
//    public ServerEndpointExporter serverEndpointExporter () {
//        return new ServerEndpointExporter();
//    }


}