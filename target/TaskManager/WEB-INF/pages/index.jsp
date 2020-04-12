<%@taglib uri="http://www.springframework.org/tags/form" prefix= "form"%>
<link href="${commonStyle}" rel="stylesheet"></link>

<div class="form">
<table style="text-align: center" class="index">
	<tr>
		<td><h2>Task Manager</h2></td>
	</tr>
	<tr>
		<td>
			<form:form method="GET" action="user/loginForm">
				<input type="submit" value="Login" class="btn btn-primary">
			</form:form>
		</td>
	</tr>
	<tr>
		<td>
			<form:form method="GET" action="user/registrationForm">
				<input type="submit" value="Regisration" class="btn btn-primary">
			</form:form>
		</td>
	</tr>
</table>
</div>