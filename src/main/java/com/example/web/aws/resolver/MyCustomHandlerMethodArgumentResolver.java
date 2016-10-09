package com.example.web.aws.resolver;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.util.WebUtils;

import com.example.web.aws.MyCustomAnnotation;
import com.example.web.aws.model.MyCustomModel;

public class MyCustomHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	private static final Logger logger = LoggerFactory.getLogger(MyCustomHandlerMethodArgumentResolver.class);

	@Override
	public Object resolveArgument(MethodParameter parameter
								   , ModelAndViewContainer container
								   , NativeWebRequest request
								   , WebDataBinderFactory factory) throws Exception {
		logger.debug("*** resolveArgument ***");

		MyCustomModel myCustom = new MyCustomModel();

		String hoge = request.getParameter("hoge");
		String fuga = request.getParameter("fuga");

		myCustom.setHoge(hoge);
		myCustom.setFuga(fuga);

		String myHeader = request.getHeader("User-Agent");

		myCustom.setMyHeader(myHeader);

		HttpServletRequest httpServletRequest = request.getNativeRequest(HttpServletRequest.class);

		Cookie myCookie = WebUtils.getCookie(httpServletRequest, "myCookie");
		if (myCookie != null) {
			myCustom.setMyCookie(myCookie.getValue());
		}

		HttpSession session = httpServletRequest.getSession(false);
		if (session != null && session.getAttribute("mySession") != null) {
			Date mySession = (Date) session.getAttribute("mySession");
			logger.debug("ID:{}, session mySession:{}", session.getId(), mySession);
			myCustom.setMySession(mySession);
		} else {
			logger.debug("session is null");
		}

		container.addAttribute("myCustom", myCustom);

		return myCustom;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		logger.debug("*** supportsParameter ***");

		logger.debug("parameterName:[{}], parameterType:[{}]", parameter.getParameterName(), parameter.getParameterType());
		logger.debug("annotattedElement:{}", parameter.getAnnotatedElement());

		if (parameter.getParameterAnnotations().length > 0) {
			for (int i=0; i<parameter.getParameterAnnotations().length; i++) {
				logger.debug("parameterAnnotations:[{}]", parameter.getParameterAnnotations()[i].annotationType().getName());
			}
		}
		logger.debug("methodName:[{}], methodReturnType:[{}]", parameter.getMethod().getName(), parameter.getMethod().getReturnType());
		if (parameter.getMethodAnnotations().length > 0) {
			for (int i=0; i<parameter.getMethodAnnotations().length; i++) {
				logger.debug("methodAnnotations:[{}]", parameter.getMethodAnnotations()[i].annotationType().getName());
			}
		}

		logger.debug("hasMethodAnnotation:{}", parameter.hasMethodAnnotation(MyCustomAnnotation.class));
		logger.debug("hasParameterAnnotation:{}", parameter.hasParameterAnnotation(MyCustomAnnotation.class));
		logger.debug("getParameterName:{}", parameter.getParameterName().equals("myCustom"));
		logger.debug("getParameterType:{}", parameter.getParameterType().equals(MyCustomModel.class));

		return parameter.getParameterType().equals(MyCustomModel.class);
		//return false;
	}

}
