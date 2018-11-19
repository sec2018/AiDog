package com.sec.aidog.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.util.List;

//@Configuration
//@Component
public class RestFilterConfig {

    @Autowired
    private AuthTokenFilter filter;
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
            FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);

        //设置（模糊）匹配的url
        List<String> urlPatterns = Lists.newArrayList();
        urlPatterns.add("/api/*");
        registrationBean.setUrlPatterns(urlPatterns);

        registrationBean.setOrder(1);
        registrationBean.setEnabled(true);
        return registrationBean;
    }
}
