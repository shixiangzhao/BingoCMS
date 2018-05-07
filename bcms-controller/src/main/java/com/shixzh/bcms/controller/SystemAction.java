package com.shixzh.bcms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/system")
public class SystemAction {
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		// 假设用户输入的用户名密码正确，则放入sessionKey中，对应的value可以 是User对象，这里以字符串"test"代替
		session.setAttribute("sessionKey", "test");
		mv.setViewName("login");
		return mv;
	}

	@RequestMapping("/view")
	public ModelAndView view(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("view");
		return mv;
	}
}
