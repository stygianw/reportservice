<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report filter</title>
<c:import url="cssheaders.jsp" />
</head>
<body>
	<div class="container">
		<h3>Filter parameters</h3>
		<c:url value="/report/process" var="actionLink"></c:url>
		<form:form commandName="filter" action="${ actionLink }">
		<div class="col-md-5">
			
			<label>Select user: </label><br/>
			<c:forEach items="${ users }" var="user">
				<form:checkbox path="namesToFilter" value="${ user.userId }"
					checked="checked" />
				<label>${ user.surname }&nbsp${ user.name }</label>
				<br />
				
			</c:forEach>
			
			<c:url value="/" var="home" />
			<a href="${ home }" class="btn btn-default">Home</a>
		</div>
		<div class="col-md-4">
		<label>Select report type:</label>
			<select class="form-control" name="selectedReport">
				<c:forEach items="${ reports }" var="report">
					<option value="${ report.key.id }">${ report.key.description }</option>
				</c:forEach>
			</select>
			<label>Lower date boundary (dd-mm-yyyy, empty - unlimited):</label>
			<br />
			<form:input class="form-control" path="notEarlierThan" />
			<br />
			<label>Upper date boundary (dd-mm-yyyy, empty - unlimited):</label>
			<br />
			<form:input class="form-control" path="notLaterThan" />
			<br />
			
			<form:errors path="*" element="div" cssClass="alert alert-warning"></form:errors>
			<input type="submit" value="sumbit" class="btn btn-success">
		</div>
		</form:form>
		
	</div>
</body>
</html>