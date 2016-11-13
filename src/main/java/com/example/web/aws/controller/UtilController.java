package com.example.web.aws.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.CookieGenerator;
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping(value = "/util")
public class UtilController {
	private static final Logger logger = LoggerFactory.getLogger(UtilController.class);

	@Autowired
	ServletContext context;

	@RequestMapping(value = "/s1", method = RequestMethod.GET)
	public String s1(Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("util s1 start");

		File tempDir = WebUtils.getTempDir(context);
		logger.info("name:{}", tempDir.getName());
		logger.info("path:{}", tempDir.getPath());
		logger.info("absolutePath:{}", tempDir.getAbsolutePath());

		try {
			String realPath = WebUtils.getRealPath(context, "/js/main.js");
			logger.info("realPath:{}", realPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String sessionId = WebUtils.getSessionId(request);
		logger.info("sessionId:{}", sessionId);

		Date current = new Date();
		WebUtils.setSessionAttribute(request, "current", current);

		logger.info("sessionId:{}", WebUtils.getSessionId(request));

		String cookieValue = RandomStringUtils.randomAlphabetic(18);

		CookieGenerator cookieGenerator = new CookieGenerator();
		cookieGenerator.setCookieDomain("localhost");
		cookieGenerator.setCookieMaxAge(600);
		cookieGenerator.setCookieName("myCookie");
		cookieGenerator.addCookie(response, cookieValue);

		return "util/s1";
	}

	@RequestMapping(value = "/s2", method = RequestMethod.GET)
	public String s2(Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("util s2 start");

		String sessionId = WebUtils.getSessionId(request);
		logger.info("sessionId:{}", sessionId);

		Cookie cookieValue = WebUtils.getCookie(request, "myCookie");
		logger.info("cookie name:{}, value:{}", cookieValue.getName(), cookieValue.getValue());

		CookieGenerator cookieGenerator = new CookieGenerator();
		cookieGenerator.setCookieName("myCookie");
		cookieGenerator.removeCookie(response);

		Date current = (Date) WebUtils.getSessionAttribute(request, "current");
		logger.info("current:{}", current);
		model.addAttribute("c", current);

		return "util/s2";
	}

	@RequestMapping(value = "/s3", method = RequestMethod.GET)
	public String s3(Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("util s3 start");

		String sessionId = WebUtils.getSessionId(request);
		logger.info("sessionId:{}", sessionId);

		Date current = (Date) WebUtils.getSessionAttribute(request, "current");
		logger.info("current:{}", current);
		model.addAttribute("c", current);

		try {
			Date c2 = (Date) WebUtils.getRequiredSessionAttribute(request, "current");
			logger.info("c2:{}", c2);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}

		Map<String,Object> params = WebUtils.getParametersStartingWith(request, "chk_");
		if (!params.isEmpty()) {
			for (Entry<String, Object> e : params.entrySet()) {
				logger.info("key:{}, value:{}", e.getKey(), e.getValue());
			}
		}

		String q = WebUtils.findParameterValue(request, "q");
		logger.info("q:{}", q);

		MultiValueMap<String,String> mParams = WebUtils.parseMatrixVariables(q);
		if (!mParams.isEmpty()) {
			for (Entry<String, List<String>> e: mParams.entrySet()) {
				for (String value : e.getValue()) {
					logger.info("key:{}, value:{}", e.getKey(), value);
				}
			}
		}

		return "util/s3";
	}

	@RequestMapping(value = "/s4", method = RequestMethod.POST)
	public String s4(Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("util s4 start");

		Boolean hasSend = WebUtils.hasSubmitParameter(request, "send");
		logger.info("hasSend:{}", hasSend);
		Boolean hasRegist = WebUtils.hasSubmitParameter(request, "regist");
		logger.info("hasRegist:{}", hasRegist);

		String email = WebUtils.findParameterValue(request, "email");
		logger.info("email:{}", email);
		String telno = WebUtils.findParameterValue(request, "telno");
		logger.info("telno:{}", telno);

		Date current = (Date) WebUtils.getSessionAttribute(request, "current");
		logger.info("current:{}", current);
		model.addAttribute("c", current);

		return "util/s4";
	}

}
