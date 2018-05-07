package com.shixzh.bcms.framework.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogInterceptor implements HandlerInterceptor {
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		/* 做一些清理工作 */
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView model)
			throws Exception {
		System.out.println("logInterceptor.postHandle()--view Name:" + model.getViewName());
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object obj) throws Exception {
		/* 取得调用的controller方法等 */
		if (obj instanceof HandlerMethod) {
			HandlerMethod hMethod = (HandlerMethod) obj;
			Method method = hMethod.getMethod();
			System.out.println("logInterceptor.preHandle()--method Name:" + method.getName());
		}
		return true;
	}
}
