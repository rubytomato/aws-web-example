package com.example.web.aws.utils;

import org.junit.Test;
import org.springframework.web.util.JavaScriptUtils;

public class JavaScriptUtilsTest {

	@Test
	public void test1() {
		String script = "<script type=\"text/javascript\">alert(document.cookie);</script>";
		String esc = JavaScriptUtils.javaScriptEscape(script);
		System.out.println(esc);
	}

}
