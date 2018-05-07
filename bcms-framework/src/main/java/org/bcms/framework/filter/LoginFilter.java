package org.bcms.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.bcms.framework.constants.Constant;
import org.bcms.framework.util.StringUtils;

public class LoginFilter implements Filter {

	private String dispatchUrl = "";
	private String excludeUrl = "";
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		String servletPath = request.getServletPath();
		
		HttpSession session = request.getSession();
		String sessionKey = (String) session.getAttribute(Constant.SESSIONKEY);
		
		/*就是登陆界面不进行过滤*/
		if(servletPath.equals(dispatchUrl) || servletPath.equals(excludeUrl)){
			filterChain.doFilter(request, response);
		}else{
			if(!StringUtils.isEmpty(sessionKey)){
				filterChain.doFilter(request, response);
			}else{
				request.getRequestDispatcher(dispatchUrl).forward(request, response);
			}
		}


	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		dispatchUrl = filterConfig.getInitParameter("dispatchUrl");
		excludeUrl = filterConfig.getInitParameter("excludeUrl");
	}

}
