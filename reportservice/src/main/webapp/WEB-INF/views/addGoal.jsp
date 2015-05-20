<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Goal</title>
<c:import url="cssheaders.jsp"></c:import>
</head>
<body>
	<div class="container">
	<div class="col-md-8">
		<h2>${ pageHeader }</h2>

		<fmt:formatDate value="${ goal.startdate }" type="date"
			pattern="dd-MM-yyyy" var="start" />
		<fmt:formatDate value="${ goal.enddate }" type="date"
			pattern="dd-MM-yyyy" var="end" />
		<c:url value="/goals/register" var="actionLink" />
		<form:form commandName="goal" method="POST" action="${ actionLink }">
			<form:hidden path="goalId" value="${ goal.goalId }" />
			<div class="form-group">
				<label>Description: </label>
				<form:input cssClass="form-control" path="description"
					value="${ goal.description }" />

				<form:errors path="description" element="div" cssClass="alert alert-warning"></form:errors>
			</div>
			<div class="form-group">
				<label>Start date (dd-MM-yyyy): </label>

				<form:input cssClass="form-control" path="startdate"
					value="${ start }" />

				<form:errors path="startdate" element="div" cssClass="alert alert-warning"></form:errors>
			</div>
			<div class="form-group">
				<label>End date (dd-MM-yyyy, empty - unfinished): </label>

				<form:input cssClass="form-control" path="enddate" value="${ end }" />

				<form:errors path="enddate" element="div" cssClass="alert alert-warning"> </form:errors>
			</div>
			<input class="btn btn-success" type="submit" value="submit">
			<c:if test="${ goal.goalId != 0 }">
				<c:url var="link" value="/goals/remove" />
				<a href="${ link }" class="btn btn-warning">Remove goal</a>
				<c:url value="/" var="home" />
				<a href="${ home }" class="btn btn-default">Home</a>
			</c:if>
		</form:form>
		
		
		</div>
	</div>
</body>
</html>