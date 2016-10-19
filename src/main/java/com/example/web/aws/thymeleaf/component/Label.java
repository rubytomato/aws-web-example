package com.example.web.aws.thymeleaf.component;

import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Text;

public class Label implements Component {

	private String id;
	private String text;
	private String classComponent;

	public Label(){
	}

	public Label(String id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public Label(String id, String text, String classComponent) {
		super();
		this.id = id;
		this.text = text;
		this.classComponent = classComponent;
	}

	@Override
	public Element toNode() {
		Element label = new Element("span");
		label.setProcessable(true);
		label.setAttribute("id", this.id);
		label.setAttribute("class", this.getClassComponent());
		label.addChild(new Text(this.text));
		return label;
	}

	public String getClassComponent() {
		return classComponent;
	}

	public void setClassComponent(String classComponent) {
		this.classComponent = classComponent;
	}

}
