package com.example.web.aws.thymeleaf.dialect;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.thymeleaf.context.IProcessingContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionEnhancingDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.spring4.context.SpringWebContext;

import com.example.web.aws.thymeleaf.expression.ViewHelper;
import com.example.web.aws.thymeleaf.processor.attr.MyAttrAttrProcessor;
import com.example.web.aws.thymeleaf.processor.attr.MyIfAttrProcessor;
import com.example.web.aws.thymeleaf.processor.attr.MySrcAttrProcessor;
import com.example.web.aws.thymeleaf.processor.element.MyBlockElementProcessor;
import com.example.web.aws.thymeleaf.processor.element.MyIfElementProcessor;
import com.example.web.aws.thymeleaf.processor.element.MyLabelElementProcessor;

public class MyDialect extends AbstractDialect implements IExpressionEnhancingDialect/*, ApplicationContextAware*/ {
	private static final String PREFIX = "my";
	private static final Set<IProcessor> PROCESSORS;
	//private static final Map<String, Object> EXPRESSION_OBJECTS;

	static {
		Set<IProcessor> tmp = new HashSet<>();
		tmp.add(new MySrcAttrProcessor());
		tmp.add(new MyAttrAttrProcessor());
		tmp.add(new MyIfAttrProcessor());

		tmp.add(new MyIfElementProcessor());
		tmp.add(new MyLabelElementProcessor());
		tmp.add(new MyBlockElementProcessor());

		PROCESSORS = Collections.unmodifiableSet(tmp);

		//Map<String, Object> expressionObjects = new HashMap<>();
		//expressionObjects.put("vh", new ViewHelperImpl());
		//EXPRESSION_OBJECTS = Collections.unmodifiableMap(expressionObjects);
	}

	@Override
	public String getPrefix() {
		return PREFIX;
	}

	@Override
	public boolean isLenient() {
		return false;
	}

	@Override
	public Set<IProcessor> getProcessors() {
		return PROCESSORS;
	}

	@Override
	public Map<String, Object> getAdditionalExpressionObjects(IProcessingContext processingContext) {
		//final IWebContext webContext = (IWebContext) processingContext.getContext();
		//final HttpServletRequest request = webContext.getHttpServletRequest();
		//final HttpServletResponse response = webContext.getHttpServletResponse();

		final SpringWebContext springWebContext = (SpringWebContext) processingContext.getContext();
		final HttpServletRequest request = springWebContext.getHttpServletRequest();
		final HttpServletResponse response = springWebContext.getHttpServletResponse();
		final ApplicationContext applicationContext = springWebContext.getApplicationContext();

		ViewHelper viewHelper = applicationContext.getBean(ViewHelper.class);

		Map<String, Object> tmp = new HashMap<>();
		tmp.put("vh", viewHelper);

		return Collections.unmodifiableMap(tmp);
	}

/*
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

*/
}
