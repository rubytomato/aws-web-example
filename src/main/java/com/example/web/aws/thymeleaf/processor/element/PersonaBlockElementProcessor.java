package com.example.web.aws.thymeleaf.processor.element;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.Arguments;
import org.thymeleaf.Configuration;
import org.thymeleaf.context.IContext;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.IElementNameProcessorMatcher;
import org.thymeleaf.processor.element.AbstractNoOpElementProcessor;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;

import com.example.web.aws.model.UserModel;

public class PersonaBlockElementProcessor extends AbstractNoOpElementProcessor {
	private static final String ELEMENT_NAME = "block";
	private static final int PRECEDENCE = 100000;

	public static PersonaBlockElementProcessor create() {
		System.out.println("PersonaBlockElementProcessor:create");
		return new PersonaBlockElementProcessor(ELEMENT_NAME);
	}

	public PersonaBlockElementProcessor() {
		this(ELEMENT_NAME);
	}

	public PersonaBlockElementProcessor(String elementName) {
		super(elementName);
	}

	protected PersonaBlockElementProcessor(IElementNameProcessorMatcher matcher) {
		super(matcher);
	}

	/**
	 * true
	 * <p>blah blah...</p>
	 * 
	 * false
	 * <persona:block>
	 *   <p>blah blah...</p>
	 * </persona:block>
	 */
	@Override
	protected boolean removeHostElement(Arguments arguments, Element element) {

		final Configuration configuration = arguments.getConfiguration();
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		final String userAttr = element.getAttributeValue("user");
		System.out.println("attribute:" + userAttr);

		final IStandardExpression expression = parser.parseExpression(configuration, arguments, userAttr);
		final UserModel user = (UserModel) expression.execute(configuration, arguments);
		System.out.println("argument user:" + user.toString());

		final IContext context = arguments.getContext();
		//org.thymeleaf.processor.attr.
		final IWebContext webContext = (IWebContext) context;

		final HttpServletRequest request = webContext.getHttpServletRequest();
		final HttpServletResponse response = webContext.getHttpServletResponse();
		final ServletContext servletContext = webContext.getServletContext();

		HttpSession session = request.getSession(false);
		if (session != null) {
			UserModel u = (UserModel) session.getAttribute("user");
			System.out.println("session user:" + u.toString());
		}

		return true;
	}

	@Override
	public int getPrecedence() {
		return PRECEDENCE;
	}

}
