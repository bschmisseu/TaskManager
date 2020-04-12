<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/resources/css/side-bar-style.css" var="sideBarStyle" />
<spring:url value="/resources/pictures/logo.png" var="logo" />
<link href="${sideBarStyle}" rel="stylesheet"></link>

<div class="sidenav">
	<table style="width: 100%">
		<tr>
			<td align="left"><a style="padding-bottom:0px;" href="HomePage.xhtml"><img src="${logo}" style="width:290px;height:37px;padding-left:10px;padding-right:10px;padding-bottom:0px"></img></a></td>
			<td style="font-size: 40px; color: #ababab; vertical-align: middle; padding-right:10px;" align="right">+</td>
		</tr>
	
	</table>
</div>