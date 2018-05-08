package com.shixzh.bcms.framework.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyHttpSessionListener implements HttpSessionListener {

	private static Logger logger = LoggerFactory.getLogger(MyHttpSessionListener.class);
	public static int peopleOnLine = 0;

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		logger.info("MyHttpSessionListener -> sessionCreated start, httpSessionEvent={}", httpSessionEvent);
		peopleOnLine++;
		httpSessionEvent.getSession().setAttribute("peopleOnLine", peopleOnLine);
		logger.info("MyHttpSessionListener -> sessionCreated end, peopleOnLine={}", peopleOnLine);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		logger.info("MyHttpSessionListener -> sessionDestroyed, httpSessionEvent={}", httpSessionEvent);
		peopleOnLine--;
		httpSessionEvent.getSession().setAttribute("peopleOnLine", peopleOnLine);
		logger.info("MyHttpSessionListener -> sessionDestroyed, peopleOnLine={}", peopleOnLine);
	}

}
