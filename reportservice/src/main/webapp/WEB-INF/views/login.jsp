<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<c:import url="cssheaders.jsp" />
</head>
<body>
<c:url value="j_spring_security_check" var="sec" />

<form action="login" name="loginForm" method="post">
	<label>Login: </label>
	<input type="text" class="form-control" name="username" />
	<label>Password: </label>
	<input type="text" class="form-control" name="password" />
	<input type="submit" name="submit" value="Login..." />
	<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	
</form>

<c:if test="${ not empty error }">
	<div class="alert alert-warning">${ error }</div>
</c:if>
<c:if test="${ not empty msg }">
	<div class="alert alert-warning">${ msg }</div>
</c:if>

</body>
</html>