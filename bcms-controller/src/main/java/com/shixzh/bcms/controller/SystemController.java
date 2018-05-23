package com.shixzh.bcms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shixzh.bcms.controller.req.ReqUser;
import com.shixzh.bcms.service.SystemService;

@Controller
@RequestMapping(value = "/system")
public class SystemController {

	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

	@Autowired
	private SystemService systemService;

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(@RequestParam(name = "name", required = false, defaultValue = "world") String name,
			Model model) {
		logger.info("SystemAction -> view start...");
		systemService.toLogin();
		model.addAttribute("msg", name);
		logger.info("SystemAction -> view end, model={}", model);
		return "view";
	}

	@RequestMapping(value = "/toLogin", method = RequestMethod.GET)
	public String toLogin(HttpServletRequest request) {
		logger.info("SystemAction -> toLogin start...");
		HttpSession session = request.getSession();
		int peopleOnLine = (int) session.getAttribute("peopleOnLine");
		// 假设用户输入的用户名密码正确，则放入sessionKey中，对应的value可以 是User对象，这里以字符串"test"代替
		session.setAttribute("sessionKey", "test" + peopleOnLine);
		logger.info("SystemAction -> toLogin end.");
		return "login";
	}

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestBody ReqUser reqUser, Model model) {
		logger.info("SystemAction -> login start, reqUser={}", reqUser);
		model.addAttribute("msg", reqUser.getUserName());
		logger.info("SystemAction -> login end, model={}", model);
		return "index";
	}

}
