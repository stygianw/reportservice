<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User details</title>
<c:import url="cssheaders.jsp" />
</head>
<body>
<div class="container">
	<h2>Details of user: ${ user.name } ${ user.surname }</h2>
	<h3>Goals totally: ${fn:length(user.goals)}</h3>
	<c:if test="${ fn:length(user.goals) > 0 }">
		<c:set var="goalcount" value="0" />
		<c:forEach var="goal" items="${ user.goals }">
			<c:if test="${ goal.enddate != null }">
				<c:set var="goalcount" value="${ goalcount + 1 }" />
			</c:if>
		</c:forEach>
		<h3>Finished goals: ${ goalcount }</h3>
		<h4>Goals list:</h4>
	
	<table class="table">
		<tr>
		 	<th>Description</th>
		 	<th>Start date</th>
		 	<th>End date</th>
		 	<th>Days spent for goal</th>
		 	<th>Edit goal</th>
		 </tr>
		<c:forEach items="${ user.goals }" var="goal">
		 
		 <tr>
			<td>${ goal.description }</td>
			<td><fmt:formatDate value="${ goal.startdate }" type="date"/></td>
				<c:choose>
					<c:when test="${ !empty goal.enddate }">
						<td><fmt:formatDate value="${ goal.enddate }" type="date"/></td>
						<td>${ goal.timeDifference }</td>
					</c:when>
					<c:otherwise>
						<td colspan="2"><span>IN PROGRESS</span></td>
					</c:otherwise>
				</c:choose>
			
			<c:url var="url" value="/goals/edit/${ goal.goalId }" />
			<td><a href="${ url }" class="btn btn-info" role="button">Edit goal</a></td>
		</tr>
		</c:forEach>
	</table>
	</c:if>
	<c:if test="${ fn:length(user.goals) == 0 }">
		<div class="alert alert-info">
			<span>No goals for this user. Please add some!</span>
		</div>
		
	</c:if>
	<c:url value="/goals/add/${ user.userId }" var="addLink" />
	<a href="${ addLink }" class="btn btn-success" role="button">Add Goal</a>
	
<c:url value="/" var="home"/>
<a href="${ home }" class="btn btn-default" role="button">Home</a >
</div>
</body>
</html>