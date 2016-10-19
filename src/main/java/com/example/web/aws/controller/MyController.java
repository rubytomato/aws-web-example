package com.example.web.aws.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.web.aws.model.ItemModel;
import com.example.web.aws.model.UserModel;

@Controller
public class MyController {
	private static final Logger logger = LoggerFactory.getLogger(MyController.class);

	private static final Map<Long, ItemModel> LIST;
	static {
		Map<Long, ItemModel> tmp = new HashMap<Long, ItemModel>();
		tmp.put(1L, new ItemModel(1L, "うまい棒", 10, 50, "/path/to/image/umaibou.png"));
		tmp.put(2L, new ItemModel(2L, "チョコバット", 20, 20, "/path/to/image/chocobat.png"));
		tmp.put(3L, new ItemModel(3L, "ベビースター", 30, 5, null));
		LIST = Collections.unmodifiableMap(tmp);
	}

	@RequestMapping(value = "/my/{id}", method = RequestMethod.GET)
	public String my(@PathVariable(name = "id", required = true) Long id, HttpServletRequest request, Model model) {
		logger.info("my start");

		UserModel user = new UserModel(100L, "rubytomato");

		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		ItemModel item = LIST.get(id);
		model.addAttribute("item", item);

		model.addAttribute("umaibou", LIST.get(1L));
		model.addAttribute("chocobat", LIST.get(2L));
		model.addAttribute("babystar", LIST.get(3L));

		model.addAttribute("valueName", "chocobat");

		return "my/my";
	}

}
