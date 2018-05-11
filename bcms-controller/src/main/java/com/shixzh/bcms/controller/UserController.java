package com.shixzh.bcms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shixzh.bcms.controller.req.ReqUser;
import com.shixzh.bcms.po.UserPO;
import com.shixzh.bcms.service.UserService;

@Controller
@RequestMapping(value = "/user")
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
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addUser(@RequestBody ReqUser reqUser, Model model) {
		logger.info("UserController -> addUser start...");
		UserPO userPO = new UserPO(reqUser.getUserName());
		Integer isAdd = userServce.addUser(userPO);
		logger.info("UserController -> addUser end.");
		model.addAttribute("isAdd", isAdd);
		model.addAttribute("msg", "success");
		return "userAdd";
	}

	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String updateUser(@RequestBody ReqUser reqUser, Model model) {
		logger.info("UserController -> updateUser start...");
		UserPO userPO = new UserPO(reqUser.getUserName());
		Integer isUpdate = userServce.updateUser(userPO);
		logger.info("UserController -> updateUser end.");
		model.addAttribute("isUpdate", isUpdate);
		model.addAttribute("msg", "success");
		return "userUpdate";
	}
	
	@RequestMapping(value="/delete/{userId}", method=RequestMethod.GET)
	public String deleteUser(@PathVariable("userId") Long userId, Model model) {
		logger.info("UserController -> deleteUser start...");
		Integer isDelete = userServce.deleteUser(userId);
		logger.info("UserController -> deleteUser end.");
		model.addAttribute("isDelete", isDelete);
		model.addAttribute("msg", "success");
		return "userList";
	}

	@RequestMapping(value="/list", method=RequestMethod.POST)
	public String listUser(@RequestBody ReqUser reqUser, Model model) {
		logger.info("UserController -> deleteUser start...");
		UserPO userPO = new UserPO(reqUser.getUserName());
		List<UserPO> userPOs = userServce.listUser(userPO);
		logger.info("UserController -> deleteUser end.");
		model.addAttribute("msg", "success");
		model.addAttribute("userPOs", userPOs);
		return "userList";
	}
	
	
}
