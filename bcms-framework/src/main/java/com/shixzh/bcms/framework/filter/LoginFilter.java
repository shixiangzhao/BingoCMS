package com.shixzh.bcms.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shixzh.bcms.framework.constants.Constant;
import com.shixzh.bcms.framework.util.StringUtils;

public class LoginFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

	private String toLoginUrl = "";
	private String loginUrl = "";

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		logger.info("LoginFilter -> doFilter start, toLoginUrl ={}, loginUrl={}", toLoginUrl, loginUrl);
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String servletPath = request.getServletPath();
		logger.info("LoginFilter -> doFilter, servletPath={}", servletPath);

		HttpSession session = request.getSession();
		String sessionKey = (String) session.getAttribute(Constant.SESSIONKEY);
		logger.info("LoginFilter -> doFilter sessionKey=" + sessionKey);

		// 登陆界面不进行过滤
		if (servletPath.equals(toLoginUrl) || servletPath.equals(loginUrl)) {
			filterChain.doFilter(request, response);
			logger.info("This is login page, doFilter end.");
		} else {
			if (!StringUtils.isEmpty(sessionKey)) {//登陆过的用户
				filterChain.doFilter(request, response);
				logger.info("You have login, sessionKey={}", sessionKey);
			} else {//未登录则跳转到登陆页面
				request.getRequestDispatcher(toLoginUrl).forward(request, response);
				logger.info("Please go to login page, toLoginUrl={}", toLoginUrl);
			}
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("LoginFilter -> init, param={}", filterConfig);
		toLoginUrl = filterConfig.getInitParameter("toLoginUrl");
		loginUrl = filterConfig.getInitParameter("loginUrl");
		logger.info("LoginFilter -> init, toLoginUrl ={}, loginUrl={}", toLoginUrl, loginUrl);
	}

}
