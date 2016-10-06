package com.example.web.aws.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.example.web.aws.model.MyCustom;

public class MyCustomHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	private static final Logger logger = LoggerFactory.getLogger(MyCustomHandlerMethodArgumentResolver.class);

	@Override
	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest,
			WebDataBinderFactory webDataBinderFactory) throws Exception {
		logger.debug("resolveArgument");
		String hoge = nativeWebRequest.getParameter("hoge");
		String fuga = nativeWebRequest.getParameter("fuga");
		MyCustom custom = new MyCustom();
		custom.setHoge(hoge);
		custom.setFuga(fuga);
		modelAndViewContainer.addAttribute("custom", custom);
		return custom;
	}

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		logger.debug("supportsParameter");
		logger.debug("parameterName:{}, parameterType:{}, annotattedElement:{}", methodParameter.getParameterName(), methodParameter.getParameterType(), methodParameter.getAnnotatedElement());
		return methodParameter.getParameterType().equals(MyCustom.class);
		//return false;
	}

}
