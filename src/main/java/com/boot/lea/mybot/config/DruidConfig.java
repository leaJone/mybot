package com.boot.lea.mybot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DruidConfig
 * @Description: 德鲁伊 监控
 * @author  LiJing
 * @date 2019/8/1 14:13
 */

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    // 配置 Druid 监控
    //1) 配置一个管理后台的Servlet
    //2) 访问地址 http://localhost:8080/api/druid/login.html
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        // 这里是 druid monitor(监视器)的 账号密码, 可以任意设置
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123");
        //白名单
        initParams.put("allow", "");
        //黑名单
        initParams.put("deny", "");
        // 设置初始化参数
        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * 配置一个监控的 filter
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String, String> initParams = new HashMap<>(5);
        // 不拦截那些属性
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        // 设置初始化参数
        bean.setInitParameters(initParams);
        // 默认拦截所有
        bean.setUrlPatterns(Arrays.asList("/*", "/api/*"));
        return bean;
    }
}
