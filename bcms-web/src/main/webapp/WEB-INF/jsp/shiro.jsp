<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>Hello
<shiro:user>
    <!-- shiro:principal prints out the Subject’s main
        principal - in this case, a username: -->
    <shiro:principal/>!
</shiro:user>
<shiro:guest>
    <!-- not logged in - considered a guest. Show
        the register link: -->
    ! <a href="register.jsp">Register today!</a>
</shiro:guest>
</p> 
</body>
</html>