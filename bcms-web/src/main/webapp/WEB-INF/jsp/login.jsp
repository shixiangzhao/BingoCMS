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
	function login() {
		console.log("login start...");
		var data = {
			"userMobile" : $("#userMobile").val(),
			"userPassword" : $("#userPassword").val()
		};

		$.ajax({
			type : "POST",
			url : "${ctx}/system/login",
			dataType : 'json',
			contentType : 'application/json;charset=UTF-8',
			data : JSON.stringify(data),
			success : function(e) {
				console.log("okok:" + e)
			}
		});
	}
</script>

<body>
	<div>
		<span>登陆页面</span> <br>
		<div id="loginForm">
			<table>
				<tr>
					<td>手机号：</td>
					<td><input id="userMobile" type="text" name="userMobile" /></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input id="userPassword" type="text" name="userPassword" /></td>
				</tr>
				<tr>
					<td><input id="loginBtn" type="button" onclick="login()" value="登陆" /></td>
					<td><input type="button" value="重置" /></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>