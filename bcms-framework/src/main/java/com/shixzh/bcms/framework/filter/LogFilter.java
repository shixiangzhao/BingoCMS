package com.shixzh.bcms.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(LogFilter.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		logger.info("LogFilter -> doFilter start...");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		logger.info("logFilter doFilter servletPath:" + httpServletRequest.getRemoteHost());
		filterChain.doFilter(httpServletRequest, response);
		logger.info("LogFilter -> doFilter end.");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
