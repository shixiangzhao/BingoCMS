package com.shixzh.bcms.framework.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlEncodeFilter implements Filter {

	Logger logger = LoggerFactory.getLogger(UrlEncodeFilter.class);
	Map<String, Object> paramMap = new HashMap<String, Object>();

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("urlEncodeFilter doFilter..." + paramMap.get("urlEncode").toString());
		request.setCharacterEncoding(paramMap.get("urlEncode").toString());
		filterChain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String urlEncode = filterConfig.getInitParameter("urlEncode");
		paramMap.put("urlEncode", urlEncode);
	}

}
