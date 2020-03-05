<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/resources/css/menu-bar-style.css" var="menubarStyle" />
<spring:url value="/resources/pictures/logo.png" var="logo" />
<link href="${menubarStyle}" rel="stylesheet"></link>

<ul>
      <li><a href="/TaskManager"><img src="${logo}" style="width:290px;height:37px;padding-left:10px;padding-right:10px"></img></a></li>
</ul>