<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Filter results</title>
<c:import url="cssheaders.jsp"></c:import>
</head>
<body>
	<div class="container">
		<h3>Result set</h3>
		<table class="table">
			<c:forEach items="${ report.reportBody }" var="elem">
				<c:set var="key" value="${ elem.key }" />
				<tr>
					<td class="bg-primary" colspan="5">${ elem.key }</td>
				</tr>
				<c:forEach items="${ elem.value }" var="body">
					<tr>
						<td>${ body.description }</td>
						<td><fmt:formatDate value="${ body.startdate }" type="date" /></td>
						<fmt:formatDate value="${ body.enddate }" type="date" var="end" />
						<c:choose>
							<c:when test="${ !empty end }">
								<td>${ end }</td>
							</c:when>
							<c:otherwise> 
								<td class="alert alert-info">IN PROGRESS</td>
							</c:otherwise>
						</c:choose>
						<td>${ body.timeDifference }</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="3">${ report.descriptionFooter }</td>
					<td colspan="2">${ report.resultFooter[key] }</td>
				</tr>

			</c:forEach>
		</table>
		<c:url value="/report/download" var="download" />
		<a href="${ download }/txt" class="btn btn-success">Download as txt</a>&nbsp
		<a href="${ download }/pdf" class="btn btn-success">Download as PDF</a>&nbsp
		<c:url value="/" var="home" />
		<a href="${ home }" class="btn btn-default">Home</a>
	</div>
</body>
</html>