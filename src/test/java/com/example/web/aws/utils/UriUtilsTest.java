package com.example.web.aws.utils;

import java.nio.charset.StandardCharsets;

import org.junit.Test;
import org.springframework.web.util.UriUtils;

public class UriUtilsTest {

	@Test
	public void test0() throws Exception {
		System.out.println(StandardCharsets.UTF_8.name());
		System.out.println(StandardCharsets.UTF_8.displayName());
	}

	@Test
	public void test1() throws Exception {
		String uri = "http://user:password@www.example.com:9000/contents/detail.html?qid=1&kw=battery#section7";
		String enc = UriUtils.encode(uri, StandardCharsets.UTF_8.name());
		System.out.println(enc);
	}

	@Test
	public void test2() throws Exception {
		String uri = "http%3A%2F%2Fuser%3Apassword%40www.example.com%3A9000%2Fcontents%2Fdetail.html%3Fqid%3D1%26kw%3Dbattery%23section7";
		String dec = UriUtils.decode(uri, StandardCharsets.UTF_8.name());
		System.out.println(dec);
	}

	@Test
	public void test3() throws Exception {
		String uri = "http://user:password@www.example.com:9000/contents/detail.html?qid=1&kw=battery#section7";
		String extension = UriUtils.extractFileExtension(uri);
		System.out.println(extension);
	}

}
