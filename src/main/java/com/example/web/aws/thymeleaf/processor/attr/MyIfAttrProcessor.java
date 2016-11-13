package com.example.web.aws.thymeleaf.processor.attr;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.thymeleaf.Arguments;
import org.thymeleaf.Configuration;
import org.thymeleaf.context.IContext;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.attr.AbstractConditionalVisibilityAttrProcessor;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;

import com.example.web.aws.model.UserModel;
import com.example.web.aws.service.MyService;

public class MyIfAttrProcessor extends AbstractConditionalVisibilityAttrProcessor {
	private static final String ATTR_NAME = "if";
	private static final int ATTR_PRECEDENCE = 1000;

	public MyIfAttrProcessor() {
		super(ATTR_NAME);
	}

	@Override
	protected boolean isVisible(Arguments arguments, Element element, String attributeName) {

		final String attributeValue = element.getAttributeValue(attributeName);
		System.out.println("attribute:" + attributeValue);

		final String hoge = element.getAttributeValue("data-hoge");
		System.out.println("hoge:" + hoge);

		final Configuration configuration = arguments.getConfiguration();
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		final IStandardExpression expression = parser.parseExpression(configuration, arguments, attributeValue);

		final Boolean result = (Boolean) expression.execute(configuration, arguments);
		System.out.println("argument result:" + result);

		//final ItemModel item = (ItemModel) expression.execute(configuration, arguments);
		//System.out.println("argument item:" + item.toString());

		final IContext context = arguments.getContext();

		//org.thymeleaf.processor.attr.
		//final IWebContext webContext = (IWebContext) context;
		//final HttpServletRequest request = webContext.getHttpServletRequest();
		//final HttpServletResponse response = webContext.getHttpServletResponse();
		//final ServletContext servletContext = webContext.getServletContext();

		final SpringWebContext springWebContext = (SpringWebContext) context;
		final HttpServletRequest request = springWebContext.getHttpServletRequest();
		final HttpServletResponse response = springWebContext.getHttpServletResponse();
		final HttpSession session = springWebContext.getHttpSession();

		UserModel user = (UserModel) session.getAttribute("user");
		System.out.println("session user:" + user.toString());

		final ApplicationContext applicationContext = springWebContext.getApplicationContext();
		MyService myService = applicationContext.getBean(MyService.class);
		Date currentDate = myService.getCurrentDate();
		System.out.println("currentDate:" + currentDate.toString());

		return result;
		//return item.getRemaining() > 10;
	}

	@Override
	public int getPrecedence() {
		return ATTR_PRECEDENCE;
	}

}
