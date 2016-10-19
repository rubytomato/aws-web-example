package com.example.web.aws.thymeleaf.processor.element;

import org.thymeleaf.Arguments;
import org.thymeleaf.Configuration;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.IElementNameProcessorMatcher;
import org.thymeleaf.processor.element.AbstractConditionalVisibilityElementProcessor;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.util.Validate;

import com.example.web.aws.thymeleaf.component.Component;
import com.example.web.aws.thymeleaf.component.Label;
import com.example.web.aws.thymeleaf.dom.GenerateDom;

public class PersonaIfElementProcessor extends AbstractConditionalVisibilityElementProcessor {
	private static final String ELEMENT_NAME = "if";
	private static final int PRECEDENCE = 100000;

	public static PersonaIfElementProcessor create() {
		System.out.println("PersonaIfElementProcessor:create");
		return new PersonaIfElementProcessor(ELEMENT_NAME);
	}

	public PersonaIfElementProcessor() {
		this(ELEMENT_NAME);
	}

	public PersonaIfElementProcessor(String elementName) {
		super(elementName);
	}

	protected PersonaIfElementProcessor(IElementNameProcessorMatcher matcher) {
		super(matcher);
	}

	@Override
	protected boolean isVisible(Arguments arguments, Element element) {
		System.out.println("template Name:" + arguments.getTemplateName());
		System.out.println("document Name:" + element.getDocumentName());
		System.out.println("original Name:" + element.getOriginalName());

		String id = element.getAttributeValue("id");
		String value = element.getAttributeValue("value");
		String classElement = element.getAttributeValue("class");

		Validate.notNull(value, "You must define a value");

		final Configuration configuration = arguments.getConfiguration();
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		final String display = element.getAttributeValue("display");
		System.out.println("display:" + display);
		final IStandardExpression expression = parser.parseExpression(configuration, arguments, display);
		final Boolean planet = (Boolean) expression.execute(configuration, arguments);
		System.out.println("planet:" + planet);

		Label label = new Label(id != null ? id : Component.generateId(), value, classElement != null ? classElement : "label label-default");
		GenerateDom.generate(element, label.toNode());

		return true;
	}

	@Override
	protected boolean removeHostElementIfVisible(Arguments arguments, Element element) {
		return true;
	}

	@Override
	public int getPrecedence() {
		return PRECEDENCE;
	}

}
