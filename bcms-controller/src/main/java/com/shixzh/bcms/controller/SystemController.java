package com.shixzh.bcms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shixzh.bcms.controller.req.ReqUser;
import com.shixzh.bcms.framework.common.CommonResult;
import com.shixzh.bcms.framework.common.CommonResultCode;
import com.shixzh.bcms.framework.constants.Constant;
import com.shixzh.bcms.framework.util.DecriptUtil;
import com.shixzh.bcms.framework.util.JSONUtils;
import com.shixzh.bcms.po.UserPO;
import com.shixzh.bcms.service.SystemService;
import com.shixzh.bcms.service.UserService;

@Controller
@RequestMapping(value = "/system")
public class SystemController {

	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

	@Autowired
	private SystemService systemService;

	@Autowired
	private UserService userService;

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
		return "login";
	}

	@RequestMapping(value = "/toIndex", method = RequestMethod.GET)
	public String toIndex(@RequestParam(name = "name", required = false, defaultValue = "world") String name,
			Model model) {
		logger.info("SystemAction -> view start...");
		systemService.toLogin();
		model.addAttribute("msg", name);
		logger.info("SystemAction -> view end, model={}", model);
		return "index";
	}

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public CommonResult<Object> login(@RequestBody ReqUser reqUser, HttpServletRequest request) {
		logger.info("SystemAction -> login start, reqUser={}", reqUser);
		CommonResult<Object> commonResult = new CommonResult<>();
		if (reqUser == null || StringUtils.isEmpty(reqUser.getUserMobile())
				|| StringUtils.isEmpty(reqUser.getUserPassword())) {
			commonResult.setCode(CommonResultCode.PARAM_ERROR.getCode());
			commonResult.setMsg(CommonResultCode.PARAM_ERROR.getMsg());
			logger.info("SystemAction -> login end, commonResult={}", commonResult);
			return commonResult;
		}

		String userMobile = reqUser.getUserMobile();
		String userPassword = reqUser.getUserPassword();
		UserPO userPO = userService.getUserByMobile(userMobile);
		logger.info("SystemAction -> login, userPO={}", userPO);
		if (userPassword.equals(userPO.getUserPassword())) {
			HttpSession session = request.getSession();
			// 用户输入的用户名密码正确，则放入sessionKey中，对应的value是User对象，
			session.setAttribute(Constant.SESSIONKEY, userPO);
			commonResult.setCode(CommonResultCode.SUCCESS.getCode());
			commonResult.setMsg(CommonResultCode.SUCCESS.getMsg());
			logger.info("SystemAction -> login end, commonResult={}", commonResult);
			return commonResult;
		}
		commonResult.setCode(CommonResultCode.PRIVILEGE_ERROR.getCode());
		commonResult.setMsg(CommonResultCode.PRIVILEGE_ERROR.getMsg());
		logger.info("SystemAction -> login end, commonResult={}", commonResult);
		return commonResult;
	}

	/**
	 * 验证用户名和密码
	 * 
	 * @param String
	 *            username,String password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	@ResponseBody
	public String checkLogin(@RequestBody ReqUser reqUser, HttpServletRequest request) throws Exception {
		logger.info("SystemAction -> checkLogin start, reqUser={}", reqUser);
		String userMobile = reqUser.getUserMobile();
		String userPassword = reqUser.getUserPassword();
		

		UserPO userPO = userService.getUserByMobile(userMobile);
		logger.info("SystemAction -> login, userPO={}", userPO);
		//userRealm.checkPermission(, );

		Map<String, Object> result = new HashMap<String, Object>();
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(userMobile, DecriptUtil.MD5(userPassword));
			Subject currentUser = SecurityUtils.getSubject();
			if (!currentUser.isAuthenticated()) { //未授权则开启验证
				// 使用shiro来验证
				token.setRememberMe(true);
				currentUser.login(token);// 验证角色和权限
			}
		} catch (Exception ex) {
			throw new Exception(CommonResultCode.PRIVILEGE_ERROR.getMsg());
		}
		result.put("success", true);
		return JSONUtils.toJSONString(result);
	} 
}
