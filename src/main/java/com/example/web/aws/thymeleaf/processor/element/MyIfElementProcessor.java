package com.example.web.aws.thymeleaf.processor.element;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.thymeleaf.Arguments;
import org.thymeleaf.context.IContext;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Text;
import org.thymeleaf.processor.element.AbstractConditionalVisibilityElementProcessor;
import org.thymeleaf.spring4.context.SpringWebContext;

import com.example.web.aws.model.ItemModel;
import com.example.web.aws.model.UserModel;

public class MyIfElementProcessor extends AbstractConditionalVisibilityElementProcessor {
	private static final String ELEMENT_NAME = "if";
	private static final int PRECEDENCE = 1000;

	public MyIfElementProcessor() {
		super(ELEMENT_NAME);
	}

	@Override
	protected boolean isVisible(Arguments arguments, Element element) {

		//final Configuration configuration = arguments.getConfiguration();
		//final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		//final String attributeValue = element.getAttributeValue("item");

		//final IStandardExpression expression = parser.parseExpression(configuration, arguments, attributeValue);
		//final ItemModel attrItem = (ItemModel) expression.execute(configuration, arguments);
		//System.out.println("attribute item:" + attrItem.toString());

		final IContext context = arguments.getContext();

		final SpringWebContext springWebContext = (SpringWebContext) context;

		final HttpServletRequest request = springWebContext.getHttpServletRequest();
		//final HttpServletResponse response = springWebContext.getHttpServletResponse();
		//final ServletContext servletContext = springWebContext.getServletContext();

		final ItemModel item = (ItemModel) request.getAttribute("item");
		System.out.println("request item:" + item.toString());

		HttpSession session = request.getSession(false);
		if (session != null) {
			UserModel sessionUser = (UserModel) session.getAttribute("user");
			System.out.println("session user:" + sessionUser.toString());
		}

		boolean isVisible = item.getRemaining() > 10;

		if (isVisible) {
			Element pEle = new Element("p");
			pEle.addChild(new Text("trueなら表示される"));
			Element divEle = new Element("div");
			divEle.addChild(pEle);
				
		}

		// 自分自身を削除
		//element.getParent().removeChild(element);

		return isVisible;
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
