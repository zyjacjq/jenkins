package chapter.forezp.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    private static final Logger log = LoggerFactory.getLogger(DruidConfig.class);

    @Value("${druid.login.user_name}")
    private String userName;

    @Value("${druid.login.password}")
    private String password;

    @Value("${druid.allow.ip}")
    private String allowIp;

    @Resource
    private JdbcParamConfig jdbcParamConfig ;

    /**
     * 必须配置数据源，不然无法获取到sql监控，与sql防火墙监控
     */
    @Bean(name = "default_databaseSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
//        DruidDataSource datasource = new DruidDataSource();
//        datasource.setUrl(jdbcParamConfig.getUrl());
//        datasource.setDriverClassName(jdbcParamConfig.getDriverClassName());
//        datasource.setInitialSize(jdbcParamConfig.getInitialSize());
//        datasource.setMinIdle(jdbcParamConfig.getMinIdle());
//        datasource.setMaxActive(jdbcParamConfig.getMaxActive());
//        datasource.setMaxWait(jdbcParamConfig.getMaxWait());
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        log.info("init Druid Servlet Configuration ");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("loginUsername", userName);// 用户名
        initParameters.put("loginPassword", password);// 密码
        initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能
        //initParameters.put("allow", allowIp); // IP白名单 (没有配置或者为空，则允许所有访问)
        //initParameters.put("deny", "");// IP黑名单 (存在共同时，deny优先于allow)
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    @Bean
    public Filter statFilter(){
        StatFilter st = new StatFilter();
        st.setSlowSqlMillis(100);
        st.setLogSlowSql(true);
        st.setMergeSql(true);
        return st;
    }


}