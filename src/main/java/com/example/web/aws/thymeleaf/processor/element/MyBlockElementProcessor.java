package com.example.web.aws.thymeleaf.processor.element;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Text;
import org.thymeleaf.processor.element.AbstractNoOpElementProcessor;

public class MyBlockElementProcessor extends AbstractNoOpElementProcessor {
	private static final String ELEMENT_NAME = "block";
	private static final int PRECEDENCE = 100000;

	public MyBlockElementProcessor() {
		super(ELEMENT_NAME);
		System.out.println("MyBlockElementProcessor:create");
	}

	@Override
	protected boolean removeHostElement(Arguments arguments, Element element) {
		System.out.println("*** removeHostElement ***");
		Element component = toNode("span", "add test node");

		element.getParent().insertBefore(element, component);

		return true;
	}

	@Override
	public int getPrecedence() {
		return PRECEDENCE;
	}

	private Element toNode(String element, String text) {
		Element label = new Element(element);
		label.setProcessable(true);
		label.addChild(new Text(text));
		return label;
	}

}
