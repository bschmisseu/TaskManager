<%@taglib uri="http://www.springframework.org/tags/form" prefix= "form"%>

<form:form method= "POST" modelAttribute= "user" action= "loginUser" class="was-validated">
<table>
	<tr>
		<td style="text-align: center;">
			<h2>Login Page</h2>
			<p>${message}</p>
		</td>
	</tr>
	<tr>
		<td>
  			<div class="form-group">
				<label for="username">Username: </label>
				<form:input path="username" class="form-control" placeholder="Enter username" required="required"/>
				<div class="invalid-feedback"><form:errors path="username"/></div>
			</div>
		</td>
	</tr>
	<tr>
		<td>
 			<div class="form-group">
				<label for="password">Password: </label>
				<form:password path="password" class="form-control" placeholder="Enter password" required="required"/>
				<div class="invalid-feedback"><form:errors path="password"/></div>
			</div>
		</td>
	</tr>
	<tr>
		<td colspan= "2" style="text-align: center">
		<input type= "submit" value= "Login" class="btn btn-primary">
		</td>
	</tr>
</table>
</form:form>