package me.shufork.common.config.interceptor;

import me.shufork.common.interceptor.HystrixRequestInterceptor;
import me.shufork.common.interceptor.ServletRequestLogger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CommonWebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ServletRequestLogger()).addPathPatterns("/**");
        registry.addInterceptor(new HystrixRequestInterceptor()).addPathPatterns("/**");

        super.addInterceptors(registry);
    }
}
