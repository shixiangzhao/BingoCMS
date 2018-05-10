package com.shixzh.bcms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shixzh.bcms.po.UserPO;
import com.shixzh.bcms.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userServce;

	@RequestMapping(value="/detail/{userId}", method=RequestMethod.GET)
	public String getDetail(@PathVariable("userId") Long userId, Model model) {
		logger.info("UserController -> getDetail, userId={}", userId);
		if (!(userId instanceof Long)) {
			logger.info("UserController -> getDetail, param is not Long.");
			return "index";
		}
		UserPO userPO = userServce.getByUserId(userId);
		logger.info("UserController -> getDetail, userPO={}", userPO);
		model.addAttribute("userPO", userPO);
		return "userDetail";
	}
}
