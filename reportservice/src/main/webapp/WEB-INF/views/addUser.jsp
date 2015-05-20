<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add User</title>
<c:import url="cssheaders.jsp" />
</head>
<body>
	<div class="container">
		<h3>Please add user data:</h3>
		
		<form:form commandName="user" action="/m/user/register">
			<form:hidden path="userId" value="${ user.userId }" />
			<div class="form-group">
				<label>Login:</label>
			
				<form:input cssClass="form-control" path="login" value="${ user.login }" readonly="${ user.userId == 0 ? false : true }" />
				<form:errors path="login" element="div" cssClass="alert alert-warning"></form:errors>
			</div>
			<div class="form-group">
				<label>Password:</label>
				<form:password cssClass="form-control" path="passwd" value="${ user.passwd }" />
				<form:errors path="passwd" element="div" cssClass="alert alert-warning"></form:errors>
			</div>
			<div class="form-group">
				<label>Name:</label>
				<form:input cssClass="form-control" path="name" value="${ user.name }" />
			
				<form:errors path="name" element="div" cssClass="alert alert-warning"></form:errors>
			</div>
			<div class="form-group">
			<label>Surname:</label>
				<form:input cssClass="form-control" path="surname" value="${ user.surname }" />
			
				<form:errors path="surname" element="div" cssClass="alert alert-warning"></form:errors>
			</div>
				<input type="submit" value="submit" class="btn btn-info" role="button">
				<c:if test="${ user.userId != 0 }">
					<c:url var="deleteLink" value="/user/delete" />
					<a href="${ deleteLink }" class="btn btn-warning" role="button">Delete user</a>
				</c:if>
				<c:url value="/" var="home" />
				<a href="${ home }" class="btn btn-default" role="button">Home</a>
		</form:form>
		
		
	</div>
</body>
</html>