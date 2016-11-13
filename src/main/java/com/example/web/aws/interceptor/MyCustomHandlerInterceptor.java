package com.example.web.aws.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.web.aws.CacheAnnotation;
import com.example.web.aws.NoCacheAnnotation;

public class MyCustomHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("MyCustomHandlerInterceptor in");
		if (!(handler instanceof HandlerMethod)) {
			return;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		System.out.println(handlerMethod.getMethod().getName());
		if (handlerMethod.hasMethodAnnotation(NoCacheAnnotation.class)) {
			addNoCacheHeaders(response);
		} else if (handlerMethod.hasMethodAnnotation(CacheAnnotation.class)) {
			CacheAnnotation annotation = handlerMethod.getMethodAnnotation(CacheAnnotation.class);
			int maxAge = (int) AnnotationUtils.getValue(annotation, "maxAge");
			addCacheHeaders(response, maxAge);
		};
	}

	private void addNoCacheHeaders(HttpServletResponse response) {
		response.addHeader("Cache-Control", "no-store");
	}

	private void addCacheHeaders(HttpServletResponse response, int maxAge) {
		response.addHeader("Cache-Control", "max-age=" + maxAge);
		//response.addDateHeader("Expires", new Date().getTime() + (long)(maxAge * 1000));
	}

	private void addCorsHeaders(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Token");
		response.setHeader("Access-Control-Allow-Credentials", "true");
	}

}
