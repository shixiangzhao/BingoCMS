<%@ page language="java" import="com.shixzh.bcms.framework.listener.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index.jsp</title>
</head>
<body>
	this is index jsp
	<!-- 这里应该填入用户名和密码 -->
	<a href="/WEB-INF/jsp/login">login</a>
	<br></br> 测试servletcontext：
	<%
		application.setAttribute("app", "app");
		application.getAttribute("app");
		application.removeAttribute("app");
	%>
	<br></br> 测试httpsession:
	<%
		session.setAttribute("app3", "app3");
		session.getAttribute("app3");
		session.removeAttribute("app3");
	%>
	<br></br> 测试servletrequest:
	<%
		request.setAttribute("app3", "app3");
		request.getAttribute("app3");
		request.removeAttribute("app3");
	%>
	<br></br> 当前在线人数：
	<%=session.getAttribute("peopleOnLine")%>
	<br></br> HttpSessionBindingListener测试：
	<%
		session.setAttribute("bean", new MyHttpSessionListener());
		session.removeAttribute("bean");
	%>
</body>
</html>