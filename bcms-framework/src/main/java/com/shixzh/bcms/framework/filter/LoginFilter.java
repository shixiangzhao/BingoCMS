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

	private String dispatchUrl = "";
	private String excludeUrl = "";

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		logger.info("LoginFilter -> doFilter start, dispatchUrl = " + dispatchUrl + ", excludeUrl = " + excludeUrl);
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String servletPath = request.getServletPath();

		HttpSession session = request.getSession();
		String sessionKey = (String) session.getAttribute(Constant.SESSIONKEY);
		logger.info("LoginFilter -> doFilter sessionKey=" + sessionKey);

		/* 登陆界面不进行过滤 */
		if (servletPath.equals(dispatchUrl) || servletPath.equals(excludeUrl)) {
			filterChain.doFilter(request, response);
			logger.info("Do filter end.");
		} else {
			if (!StringUtils.isEmpty(sessionKey)) {//登陆过的用户
				filterChain.doFilter(request, response);
			} else {//未登录则跳转到登陆页面
				request.getRequestDispatcher(dispatchUrl).forward(request, response);
			}
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("LoginFilter -> init, param={}", filterConfig);
		dispatchUrl = filterConfig.getInitParameter("dispatchUrl");
		excludeUrl = filterConfig.getInitParameter("excludeUrl");
		logger.info("LoginFilter -> init, dispatchUrl = " + dispatchUrl + ", excludeUrl = " + excludeUrl);
	}

}
