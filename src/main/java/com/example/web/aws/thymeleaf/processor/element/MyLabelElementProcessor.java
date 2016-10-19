package com.example.web.aws.thymeleaf.processor.element;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.ProcessorResult;
import org.thymeleaf.processor.element.AbstractElementProcessor;

public class MyLabelElementProcessor extends AbstractElementProcessor {
	private static final String ELEMENT_NAME = "label";
	private static final int PRECEDENCE = 100000;

	public MyLabelElementProcessor() {
		super(ELEMENT_NAME);
	}

	@Override
	protected ProcessorResult processElement(Arguments arguments, Element element) {
		System.out.println("*** processElement ***");
		System.out.println("getOriginalName:" + element.getOriginalName());
		System.out.println("getParentOriginalName:" + element.getParent().getFirstElementChild().getOriginalName());

		// 自分自身を削除
		element.getParent().removeChild(element);

		return ProcessorResult.OK;
	}

	@Override
	public int getPrecedence() {
		return PRECEDENCE;
	}

}
