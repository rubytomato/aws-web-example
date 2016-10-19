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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.web.aws.MyCustomAnnotation;
import com.example.web.aws.model.UserModel;

@Controller
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String index(Model model) {
		logger.info("index start");

		UserModel user = new UserModel();
		user.setId(1L);
		user.setName("rubytomato");
		model.addAttribute("user", user);

		model.addAttribute("point", 123L);

		return "index/index";
	}

	@MyCustomAnnotation
	@RequestMapping(value = "/custom", method = RequestMethod.GET)
	public String custom(Locale locale, TimeZone timezone, Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("custom start");
		logger.info("locale:{}", locale);
		logger.info("timezone:{}", timezone);

		UserModel user = new UserModel();
		user.setId(1L);
		user.setName("rubytomato");
		model.addAttribute("user", user);

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
		session.setAttribute("myUser", user);

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

	@RequestMapping(value = "/puge", method = RequestMethod.GET)
	public String puge(UriComponentsBuilder baseUrl) {
		//UriComponents uriComponents = MvcUriComponentsBuilder
		//		.fromMethodName(IndexController.class, "fugo", "12").build();
		//URI location = uriComponents.toUri();
		//logger.info("fromMethodName URL:{}", location.toString());
		//return "redirect:" + location.toString();

		//String location = MvcUriComponentsBuilder.fromMappingName(baseUrl, "IC#fugo").arg(0, "12").build();
		//logger.info("fromMappingName URL:{}", location);
		//return "redirect:" + location;

		//IndexController controller = MvcUriComponentsBuilder.on(IndexController.class);
		//controller.fuga("12");

		//UriComponents uriComponents = MvcUriComponentsBuilder
		//		.fromMethodCall(controller).build();
		//		//.fromMethodCall(
		//		//		MvcUriComponentsBuilder.on(IndexController.class).fugo(12L)).build();
		//URI location = uriComponents.toUri();
		//logger.info("URL:{}", location.toString());
		//return "redirect:" + location.toString();

		MvcUriComponentsBuilder builder = MvcUriComponentsBuilder.relativeTo(baseUrl);
		UriComponents uriComponents = builder.withMethodName(IndexController.class, "fugo", 12L).build();
		URI location = uriComponents.toUri();
		logger.info("URL:{}", location.toString());
		return "redirect:" + location.toString();

	}

	@RequestMapping(value = "/fuga", method = RequestMethod.GET)
	public String fuga() {
		logger.info("index fuga");
		return "index/custom";
	}

	@RequestMapping(value = "/fugo/{id}", method = RequestMethod.GET /*, name = "fuga"*/)
	public String fugo(@PathVariable("id") Long id) {
		logger.info("index fugo");
		return "index/custom";
	}

	@RequestMapping(value = "/fugu", method = RequestMethod.GET)
	public String fugu(HttpServletRequest request) {
		logger.info("getScheme:{}", request.getScheme());
		logger.info("getServerName:{}", request.getServerName());
		logger.info("getServerPort:{}", request.getServerPort());
		logger.info("isSecure:{}", request.isSecure());

		logger.info("getProtocol:{}", request.getProtocol());
		logger.info("getMethod:{}", request.getMethod());
		logger.info("getServletPath:{}", request.getServletPath());


		logger.info("getLocalAddr:{}", request.getLocalAddr());
		logger.info("getLocalName:{}", request.getLocalName());
		logger.info("getLocalPort:{}", request.getLocalPort());

		logger.info("getRemoteAddr:{}", request.getRemoteAddr());
		logger.info("getRemoteHost:{}", request.getRemoteHost());
		logger.info("getRemotePort:{}", request.getRemotePort());

		logger.info("getRequestURI:{}", request.getRequestURI());
		logger.info("getRequestURL:{}", request.getRequestURL().toString());

		return "redirect:" + "/";
	}

}
