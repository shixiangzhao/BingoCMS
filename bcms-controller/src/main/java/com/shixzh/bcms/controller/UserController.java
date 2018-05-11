package com.shixzh.bcms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shixzh.bcms.po.UserPO;
import com.shixzh.bcms.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userServce;

	@RequestMapping(value = "/detail/{userId}", method = RequestMethod.GET)
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

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@RequestParam("userName") String userName, Model model) {
		logger.info("UserController -> addUser start...");
		UserPO userPO = new UserPO(userName);
		Integer isAdd = userServce.addUser(userPO);
		logger.info("UserController -> addUser end.");
		model.addAttribute("isAdd", isAdd);
		model.addAttribute("msg", "success");
		return "userAdd";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateUser( @RequestParam("userId") Long userId, @RequestParam("userName") String userName,
			Model model) {
		logger.info("UserController -> updateUser start...");
		UserPO userPO = new UserPO(userId, userName);
		Integer isUpdate = userServce.updateUser(userPO);
		logger.info("UserController -> updateUser end.");
		model.addAttribute("isUpdate", isUpdate);
		model.addAttribute("msg", "success");
		return "userUpdate";
	}

	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("userId") Long userId, Model model) {
		logger.info("UserController -> deleteUser start...");
		Integer isDelete = userServce.deleteUser(userId);
		logger.info("UserController -> deleteUser end.");
		model.addAttribute("isDelete", isDelete);
		model.addAttribute("msg", "success");
		return "index";
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String listUser(@RequestParam("userName") String userName, Model model) {
		logger.info("UserController -> listUser start, userName={}", userName);
		UserPO userPO = new UserPO(userName);
		List<UserPO> userPOs = userServce.listUser(userPO);
		logger.info("UserController -> listUser end.");
		model.addAttribute("msg", "success");
		model.addAttribute("userPOs", userPOs);
		return "userList";
	}

	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAddUser(Model model) {
		logger.info("UserController -> toAddUser start...");
		model.addAttribute("msg", "success");
		logger.info("UserController -> toAddUser end.");
		return "userAdd";
	}

	@RequestMapping(value = "/toUpdate/{userId}", method = RequestMethod.GET)
	public String toUpdateUser(@PathVariable("userId") Long userId, Model model) {
		logger.info("UserController -> toUpdateUser start...");
		UserPO userPO = userServce.getByUserId(userId);
		model.addAttribute("userPO", userPO);
		model.addAttribute("msg", "success");
		logger.info("UserController -> toUpdateUser end.");
		return "userUpdate";
	}

}
