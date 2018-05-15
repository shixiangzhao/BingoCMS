<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index page</title>
</head>

<c:set var="ctx"
	value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<title>user detail page</title>
<body>
	<h2>Hello, ${msg}</h2>
	<br>
	<form action="${ctx}/user/list" method="post">
		<table>
			<tr>
				<td>姓名模糊查询：</td>
				<td><input name="userName" value=""/></td>
				<td>年龄上限：</td>
				<td><input name="userAgeStart" value="15"/></td>
				<td>年龄下限：</td>
				<td><input name="userAgeEnd" value="16"/></td>
				<td><input type="submit" name="查询"/></td>
			</tr>
		</table>
	</form>
</body>
</html>