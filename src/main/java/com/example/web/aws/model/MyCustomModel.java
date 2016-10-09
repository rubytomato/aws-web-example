package com.example.web.aws.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class MyCustomModel {

	private String hoge;

	private String fuga;

	private String myHeader;

	private String myCookie;

	private Date mySession;

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

	public String getMyHeader() {
		return myHeader;
	}

	public void setMyHeader(String myHeader) {
		this.myHeader = myHeader;
	}

	public String getMyCookie() {
		return myCookie;
	}

	public void setMyCookie(String myCookie) {
		this.myCookie = myCookie;
	}

	public Date getMySession() {
		return mySession;
	}

	public void setMySession(Date mySession) {
		this.mySession = mySession;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
