package com.mly.config;

import com.mly.filter.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mly
 * @create 2024-07-03 23:30
 */
@Configuration
public class CorsConfig {

    @Bean
    public FilterRegistrationBean corsFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.addUrlPatterns("/*"); // 所有路径请求
        registrationBean.setName("CorsFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
