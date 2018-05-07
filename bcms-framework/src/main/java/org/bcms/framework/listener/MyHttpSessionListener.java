package org.bcms.framework.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener {

	public static int peopleOnLine = 0;

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		System.out.println("MyHttpSessionListener.sessionCreated(): " + httpSessionEvent);
		peopleOnLine++;
		httpSessionEvent.getSession().setAttribute("peopleOnLine", peopleOnLine);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		System.out.println("MyHttpSessionListener.sessionDestroyed(): " + httpSessionEvent);
		peopleOnLine--;
		httpSessionEvent.getSession().setAttribute("peopleOnLine", peopleOnLine);
	}

}
