package com.example.web.aws;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.web.aws.interceptor.MyCustomHandlerInterceptor;
import com.example.web.aws.resolver.MyCustomHandlerMethodArgumentResolver;

@Configuration
public class MyWebMvcConfigurer extends WebMvcConfigurerAdapter {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new MyCustomHandlerMethodArgumentResolver());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MyCustomHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**");
	}

}
