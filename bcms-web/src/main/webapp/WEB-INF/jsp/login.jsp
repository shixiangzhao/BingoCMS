<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>main.jsp</title>
</head>

<c:set var="ctx"
	value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<script type="text/javascript"
	src="${ctx}/plugins/jquery-3.0.0/jquery-3.0.0.js"></script>
<script type="text/javascript">
	
</script>

<body>
	This is has login jsp
	<a href="/WEB-INF/jsp/view">view</a>
</body>
</html>