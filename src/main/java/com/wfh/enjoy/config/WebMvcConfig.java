package com.wfh.enjoy.config;

import com.wfh.enjoy.Interceptor.CheckLoginInterceptor;
import com.wfh.enjoy.utils.JacksonObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author wfh
 * @create 2023/3/22 - 17:47
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private CheckLoginInterceptor checkLoginInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/enjoy/**").addResourceLocations("classpath:/templates/backend/");
        registry.addResourceHandler("/app/**").addResourceLocations("classpath:/templates/front/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录检查拦截器
        registry.addInterceptor(checkLoginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/backend/**","/front/**","/error","/index/*","/user/*","/enjoy/login/login.html","/app/login.html","/favicon.ico");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

        messageConverter.setObjectMapper(new JacksonObjectMapper());

        //设置优先级，不使用默认的Jackson转换器
        converters.add(0,messageConverter);
    }
}
