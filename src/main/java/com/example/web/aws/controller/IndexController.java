package com.example.web.aws.controller;

import java.util.Locale;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.web.aws.model.MyCustom;

@Controller
public class IndexController {
  private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

  @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
  public String index() {
    logger.info("index start");
    return "index/index";
  }

  @RequestMapping(value = "/custom", method = RequestMethod.GET)
  public String custom(Locale locale, TimeZone timezone, MyCustom custom) {
	  logger.info("custom start. locale:{} timezone:{}", locale, timezone);
	  logger.info("MyCustom:{}", custom);
	  //model.addAttribute("custom", custom);
	  return "index/custom";
  }
}
