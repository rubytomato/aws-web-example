package com.example.web.aws.thymeleaf.processor.attr;

import org.apache.commons.lang3.StringUtils;
import org.thymeleaf.Arguments;
import org.thymeleaf.Configuration;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.attr.AbstractSingleAttributeModifierAttrProcessor;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;

import com.example.web.aws.model.ItemModel;

public class MySrcAttrProcessor extends AbstractSingleAttributeModifierAttrProcessor {
	private static final String ATTR_NAME = "src";
	private static final int ATTR_PRECEDENCE = 1000;

	public MySrcAttrProcessor() {
		super(ATTR_NAME);
	}

	@Override
	protected String getTargetAttributeName(Arguments arguments, Element element, String attributeName) {
		System.out.println("MySrcAttrProcessor#getTargetAttributeName");
		return ATTR_NAME;
	}

	@Override
	protected String getTargetAttributeValue(Arguments arguments, Element element, String attributeName) {
		System.out.println("MySrcAttrProcessor#getTargetAttributeValue");

		final String attributeValue = element.getAttributeValue(attributeName);
		System.out.println("attribute:" + attributeValue);

		final String hoge = element.getAttributeValue("data-hoge");
		System.out.println("hoge:" + hoge);

		final Configuration configuration = arguments.getConfiguration();
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);

		final IStandardExpression expression = parser.parseExpression(configuration, arguments, attributeValue);
		final ItemModel item = (ItemModel) expression.execute(configuration, arguments);
		System.out.println("request item:" + item.toString());

		return StringUtils.isEmpty(item.getImageUrl()) ? "/path/to/image/noimage.png" : item.getImageUrl();
	}

	@Override
	protected ModificationType getModificationType(Arguments arguments, Element element, String attributeName, String newAttributeName) {
		System.out.println("MySrcAttrProcessor#getModificationType");
		return ModificationType.SUBSTITUTION;
	}

	@Override
	protected boolean removeAttributeIfEmpty(Arguments arguments, Element element, String attributeName, String newAttributeName) {
		System.out.println("MySrcAttrProcessor#removeAttributeIfEmpty");
		return true;
	}

	@Override
	protected boolean recomputeProcessorsAfterExecution(Arguments arguments, Element element, String attributeName) {
		System.out.println("MySrcAttrProcessor#recomputeProcessorsAfterExecution");
		return true;
	}

	@Override
	public int getPrecedence() {
		return ATTR_PRECEDENCE;
	}

}
