package com.shixzh.bcms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/system")
public class SystemController {

	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView login(HttpServletRequest request) {
		logger.info("SystemAction -> login start...");
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		// 假设用户输入的用户名密码正确，则放入sessionKey中，对应的value可以 是User对象，这里以字符串"test"代替
		session.setAttribute("sessionKey", "test");
		mv.setViewName("login");
		logger.info("SystemAction -> login end.");
		return mv;
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(HttpServletRequest request) {
		logger.info("SystemAction -> view start...");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("view");
		logger.info("SystemAction -> view end.");
		return mv;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		logger.info("SystemAction -> view start...");
		model.addAttribute("name", name);
		return "hello";
	}

}
