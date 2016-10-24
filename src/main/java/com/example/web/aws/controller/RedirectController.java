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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.web.aws.controller.form.RedirectForm;

@Controller
@RequestMapping(value = "/redirect")
public class RedirectController {
	private static final Logger logger = LoggerFactory.getLogger(RedirectController.class);

	@RequestMapping(value = "/entry", method = RequestMethod.GET)
	public String entry(Model model) {
		logger.info("redirect [B] entry start");

		Date current = new Date();
		model.addAttribute("current", current);

		return "redirect/entry";
	}

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String regist(RedirectForm form, RedirectAttributes attributes) {
		logger.info("redirect [C] regist start");
		logger.info("{}", form.toString());

		// formの内容をDBに登録する処理
		// 疑似的な登録ID
		String id = UUID.randomUUID().toString();
		logger.info("id:{}",id);

		attributes.addFlashAttribute("message", "登録完了");

		return "redirect:/redirect/result/" + id;
	}

	@RequestMapping(value = "/result/{id}", method = RequestMethod.GET)
	public String result(@PathVariable("id") String id, Model model) {
		logger.info("redirect [D] result start");
		logger.info("id:{}",id);

		// IDから登録内容を検索する
		model.addAttribute("name", "rubytomato");
		model.addAttribute("email", "rubytomato@example.com");

		Date current = new Date();
		model.addAttribute("current", current);

		return "redirect/result";
	}

	@RequestMapping(value = "/other", method = RequestMethod.GET)
	public String other(Model model) {
		logger.info("redirect [E] other start");

		Date current = new Date();
		model.addAttribute("current", current);

		return "redirect/other";
	}

}
