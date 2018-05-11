<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login page</title>
</head>

<c:set var="ctx"
	value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<script type="text/javascript" src="${ctx}/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	
</script>

<body>
	This is login jsp!
	<br>
	<a href="${ctx}/user/list">返回</a>
	<form action="/user/update" method="post">
		<table>
			<tr>
				<td>姓名：</td>
				<td><input type="text" name="userName" value="${userPO.userName}"/></td>
			</tr>
			<tr>
				<td><input type="submit" name="提交"/></td>
				<td><input type="reset" name="重置"/></td>
			</tr>
		</table>
	</form>
</body>
</html>