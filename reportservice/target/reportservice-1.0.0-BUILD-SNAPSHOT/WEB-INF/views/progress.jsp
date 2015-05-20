<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Building...</title>
<!-- <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="/m/resources/js/progressbar.min.js"></script> -->
<c:import url="jsheaders.jsp" />
<c:import url="cssheaders.jsp" />
<style type="text/css">
#example-percent-container {
	width: 250px;
}
</style>
</head>
<body>
<div class="container">
	<div class="col-md-5">
	<div class="alert alert-info">
		<span>Your report is being built, please wait...</span>
	</div>
	<div style="margin: auto" id="example-percent-container"></div>
	<div id="success" style="display: none" class="alert alert-success">
		<span>Building successful, redirecting...</span>
	</div>
	</div>
</div>
</body>
<c:url var="script" value="/resources/js/progress.js" />
<script src="${ script }"></script>
</html>