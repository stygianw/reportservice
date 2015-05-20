<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<c:import url="cssheaders.jsp" />
</head>
<body>
	<div class="container">
		<h2>Welcome to our time accounting service</h2>
		
		<table class="table">
			<tr>
				<th>Name</th>
				<th>Surname</th>
				<th>Quantity of goals</th>
				<th>Show goals</th>
				<th>Edit user</th>
			</tr>
			<c:forEach var="user" items="${ users }">
				<tr>
					<td>${ user.name }</td>
					<td>${ user.surname }</td>
					<td>${ fn:length(user.goals) }</td>
					<td><c:url var="goals"
							value="user/details/${ user.userId }/goals" /> <a
						href="${ goals }">Show goals</a></td>
					<td><c:url var="edit"
							value="user/details/${ user.userId }/edit" /> <a
						href="${ edit }">Edit user</a></td>
				</tr>
			</c:forEach>
		</table>
		<c:url var="addLink" value="/user/add" />
		<a href="${ addLink }" class="btn btn-info" role="button">Add user</a>
		<c:url value="/report/filter" var="filter" />
		<a href=${ filter } class="btn btn-success" role="button">Report
			builder</a>
	</div>
</body>
</html>