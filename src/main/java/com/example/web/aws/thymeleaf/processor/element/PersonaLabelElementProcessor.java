package com.example.web.aws.thymeleaf.processor.element;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.IElementNameProcessorMatcher;
import org.thymeleaf.processor.ProcessorResult;
import org.thymeleaf.processor.element.AbstractElementProcessor;
import org.thymeleaf.util.Validate;

import com.example.web.aws.thymeleaf.component.Component;
import com.example.web.aws.thymeleaf.component.Label;
import com.example.web.aws.thymeleaf.dom.GenerateDom;

public class PersonaLabelElementProcessor extends AbstractElementProcessor {
	private static final String ELEMENT_NAME = "label";
	private static final int PRECEDENCE = 100000;

	public static PersonaLabelElementProcessor create() {
		System.out.println("PersonaLabelElementProcessor:create");
		return new PersonaLabelElementProcessor(ELEMENT_NAME);
	}

	public PersonaLabelElementProcessor() {
		this(ELEMENT_NAME);
	}

	public PersonaLabelElementProcessor(IElementNameProcessorMatcher matcher) {
		super(matcher);
	}

	protected PersonaLabelElementProcessor(String elementName) {
		super(elementName);
	}

	@Override
	protected ProcessorResult processElement(Arguments arguments, Element element) {
		System.out.println("template Name:" + arguments.getTemplateName());
		System.out.println("document Name:" + element.getDocumentName());
		System.out.println("original Name:" + element.getOriginalName());

		String id = element.getAttributeValue("id");
		String value = element.getAttributeValue("value");
		String classElement = element.getAttributeValue("class");
		Validate.notNull(value, "You must define a value");
		Label label = new Label(id != null ? id : Component.generateId(), value, classElement != null ? classElement : "label label-default");
		GenerateDom.generate(element, label.toNode());
		element.getParent().removeChild(element);

		return ProcessorResult.OK;
	}

	@Override
	public int getPrecedence() {
		return PRECEDENCE;
	}

}
