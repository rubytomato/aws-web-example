package com.example.web.aws.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class MyCustom {

	private String hoge;

	private String fuga;

	public String getHoge() {
		return hoge;
	}

	public void setHoge(String hoge) {
		this.hoge = hoge;
	}

	public String getFuga() {
		return fuga;
	}

	public void setFuga(String fuga) {
		this.fuga = fuga;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
