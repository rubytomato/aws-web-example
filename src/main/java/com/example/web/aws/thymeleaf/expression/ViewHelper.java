package com.example.web.aws.thymeleaf.expression;

import java.util.Date;

public interface ViewHelper {

	public Date currentDate();

	public String hello(String message);
}
