package com.example.web.aws.controller;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.web.aws.controller.form.RedirectForm;

@Controller
@RequestMapping(value = "/redirect3")
public class Redirect3Controller {
	private static final Logger logger = LoggerFactory.getLogger(Redirect3Controller.class);

	//Entry Form
	@RequestMapping(value = "/entry", method = RequestMethod.GET)
	public String entry(Model model) {
		logger.info("redirect3 [B] entry start");

		Date current = new Date();
		model.addAttribute("current", current);

		return "redirect3/entry";
	}

	//Registration
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String regist(RedirectForm form, Model model) {
		logger.info("redirect3 [C] regist start");
		logger.info("{}", form.toString());

		// formの内容をDBに登録する
		// 疑似的な登録ID
		String id = UUID.randomUUID().toString();
		logger.info("id:{}",id);

		model.addAttribute("id", id);

		// IDから登録情報を検索する
		model.addAttribute("name", "rubytomato");
		model.addAttribute("email", "rubytomato@example.com");

		model.addAttribute("message", "登録完了");

		Date current = new Date();
		model.addAttribute("current", current);

		return "redirect3/regist";
		//return result(id, model);
	}

	//Result
	@RequestMapping(value = "/result/{id}", method = RequestMethod.GET)
	public String result(@PathVariable("id") String id, Model model) {
		logger.info("redirect3 [D] result start");
		logger.info("id:{}",id);

		model.addAttribute("id", id);

		// IDから登録情報を検索する
		model.addAttribute("name", "rubytomato");
		model.addAttribute("email", "rubytomato@example.com");

		Date current = new Date();
		model.addAttribute("current", current);

		return "redirect3/regist";
	}

	//Other
	@RequestMapping(value = "/other", method = RequestMethod.GET)
	public String other(Model model) {
		logger.info("redirect3 [E] other start");

		Date current = new Date();
		model.addAttribute("current", current);

		return "redirect3/other";
	}

}
