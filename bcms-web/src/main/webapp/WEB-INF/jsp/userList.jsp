<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user list page</title>
</head>

<c:set var="ctx"
	value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<script type="text/javascript" src="${ctx}/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	
</script>

<body>
	This is user list jsp!
	<br>
	<a href="${ctx}/system/index">index</a>
	<table>
		<thead>
			<tr>
				<td>编号</td>
				<td>姓名</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="userPOs" var="user">
				<tr>
					<td>${user.userId}</td>
					<td>${user.userName}</td>
					<td><a href="${ctx}/user/detail/${user.userId}">查看</a></td>
					<td><a href="${ctx}/WEB-INF/jsp/userAdd.jsp">新增</a></td>
					<td><a href="${ctx}/user/toUpdate">更新</a></td>
					<td><a href="${ctx}/user/delete">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>