<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Task Manager</title>
	
	<spring:url value="/resources/css/common-style.css" var="commonStyle" />
	<spring:url value="/resources/css/menu-bar-style.css" var="menubarStyle" />
	<spring:url value="/resources/css/side-bar-style.css" var="sideBarStyle" />
	<spring:url value="/resources/css/table-style.css" var="tableStyle" />
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"></link>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
		
	<link href="${commonStyle}" rel="stylesheet"></link>
	<%--<link href="${menubarStyle}" rel="stylesheet"></link>
	<link href="${sideBarStyle}" rel="stylesheet"></link>
	<link href="${tableStyle}" rel="stylesheet"></link> --%>
		
	<style>
		body {
		background-color: #f2f2f2; 
		}
	</style>		
</head>

<body>
	<!-- Header -->
	<tiles:insertAttribute name="header" />

	<!-- Body Page -->
	<div align="center">
		<tiles:insertAttribute name="body" />
	</div>

	<!-- Footer Page -->
	<tiles:insertAttribute name="footer" />
</body>

</html>