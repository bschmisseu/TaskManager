<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Task Manager</title>
	
	<spring:url value="/resources/css/home-page-style.css" var="homePageStyle" />
	<spring:url value="/resources/pictures/logo.png" var="logo" />
	<link href="${homePageStyle}" rel="stylesheet"></link>
			
</head>

<body>
	<!-- Body Page -->
	<div align="center">
		<tiles:insertAttribute name="body" />
	</div>
</body>

</html>