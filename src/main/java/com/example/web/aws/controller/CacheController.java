package com.example.web.aws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.web.aws.CacheAnnotation;
import com.example.web.aws.NoCacheAnnotation;

@Controller
@RequestMapping(value = "/cache")
public class CacheController {
	private static final Logger logger = LoggerFactory.getLogger(CacheController.class);

	@CacheAnnotation(maxAge = 600)
	@RequestMapping(value = "/cache", method = RequestMethod.GET)
	public String cache(Model model) {
		logger.info("cache/cache start");

		return "cache/cache";
	}

	@NoCacheAnnotation
	@RequestMapping(value = "/nocache", method = RequestMethod.GET)
	public String noCache(Model model) {
		logger.info("cache/noCache start");

		return "cache/nocache";
	}

	@RequestMapping(value = "/nothing", method = RequestMethod.GET)
	public String nothing() {
		return "cache/nothing";
	}

}
