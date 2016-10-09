package com.example.web.aws.controller;

import java.net.URI;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.WebUtils;

import com.example.web.aws.MyCustomAnnotation;

@Controller
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String index() {
		logger.info("index start");
		return "index/index";
	}

	@MyCustomAnnotation
	@RequestMapping(value = "/custom", method = RequestMethod.GET)
	public String custom(Locale locale, TimeZone timezone, HttpServletRequest request, HttpServletResponse response) {
		logger.info("custom start");
		logger.info("locale:{}", locale);
		logger.info("timezone:{}", timezone);

		Cookie cookie = new Cookie("myCookie","abc");
		response.addCookie(cookie);

		HttpSession session = request.getSession(false);
		if (session == null) {
			logger.info("session is null");
			session = request.getSession(true);
		} else {
			logger.info("session is not null");
		}
		session.setAttribute("mySession", new Date());

		logger.info("Session Id:{}", session.getId());

		return "index/custom";
	}

	@RequestMapping(value = "/custom2/{id}/{name}", method = RequestMethod.GET)
	public String custom2(@PathVariable("id") Long id, @PathVariable("name") String name) {
		logger.info("custom2 start. id:{}, name:{}", id, name);
		return "index/custom";
	}

	@RequestMapping(value = "/hoge", method = RequestMethod.GET)
	public String hoge(HttpServletRequest request, UriComponentsBuilder builder) {
		logger.info("hoge start");

		//URI location = builder.path("/custom").build().toUri();
		//logger.info("URL:{}", location.toString());

		Enumeration<String> headers = request.getHeaderNames();
		while(headers.hasMoreElements()) {
			String name = headers.nextElement();
			String value = request.getHeader(name);
			logger.info("header: {} / {}", name, value);
		}

		//URI location = builder.path("/custom/{id}/{name}").buildAndExpand(123L, "test").toUri();
		URI location = builder.path("/fuga").build().toUri();
		logger.info("URL:{}", location.toString());

		//return "redirect:/custom2/456/TEST";
		return "redirect:" + location.toString();
	}

	@RequestMapping(value = "/poge", method = RequestMethod.GET)
	public String poge(HttpServletRequest request) {

		//HttpRequest req = new ServletServerHttpRequest(request);
		//UriComponentsBuilder builder = UriComponentsBuilder.fromHttpRequest(req);

		//UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("https://example.com");

		UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
		URI location = builder.scheme("https").host("example.com").path("/fuga").build().toUri();
		logger.info("URL:{}", location.toString());

		return "redirect:" + location.toString();
	}
}
