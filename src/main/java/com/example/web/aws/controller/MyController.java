package com.example.web.aws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.web.aws.MyCustomAnnotation;
import com.example.web.aws.model.MyCustomModel;

@Controller
public class MyController {
	private static final Logger logger = LoggerFactory.getLogger(MyController.class);

	/*
	 * メソッドのアノテーションで判別する例
	 */
	@MyCustomAnnotation
	@RequestMapping(value = "/piyo", method = RequestMethod.GET)
	public String piyo(MyCustomModel myCustom) {
		logger.info("piyo start");
		logger.info("myCustom:{}", myCustom);
		return "my/piyo";
	}

	/*
	 * パラメータのアノテーションで判別する例
	 */
	@RequestMapping(value = "/poyo", method = RequestMethod.GET)
	public String poyo(@MyCustomAnnotation MyCustomModel myCustom) {
		logger.info("poyo start");
		logger.info("myCustom:{}", myCustom);
		return "my/poyo";
	}

	/*
	 * パラメータの型で判別する例
	 */
	@RequestMapping(value = "/puyo", method = RequestMethod.GET)
	public String puyo(MyCustomModel myCustom) {
		logger.info("puyo start");
		logger.info("myCustom:{}", myCustom);
		return "my/puyo";
	}

}
