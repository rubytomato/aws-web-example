package com.example.web.aws.thymeleaf.expression;

import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class ViewHelperImpl implements ViewHelper {

	@Autowired
	MessageSource messageSource;

	@Override
	public Date currentDate() {
		return new Date();
	}

	@Override
	public String hello(String message) {
		return messageSource.getMessage("message.text1", new Object[]{message}, Locale.getDefault());
	}

}
