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

	private static final Logger logger = LoggerFactory.getLogger(UrlEncodeFilter.class);
	Map<String, Object> paramMap = new HashMap<String, Object>();

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		logger.info("UrlEncodeFilter -> doFilter start, urlEncode={}", paramMap.get("urlEncode").toString());
		request.setCharacterEncoding(paramMap.get("urlEncode").toString());
		filterChain.doFilter(request, response);
		logger.info("UrlEncodeFilter -> doFilter end.");

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("UrlEncodeFilter -> init start, filterConfig={}", filterConfig);
		String urlEncode = filterConfig.getInitParameter("urlEncode");
		paramMap.put("urlEncode", urlEncode);
		logger.info("UrlEncodeFilter -> init end, paramMap.get(urlEncode)={}", paramMap.get("urlEncode"));
	}

}
