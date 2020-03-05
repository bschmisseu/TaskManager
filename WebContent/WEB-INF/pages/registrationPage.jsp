<%@taglib uri="http://www.springframework.org/tags/form" prefix= "form"%>

<form:form method="POST" modelAttribute= "user" action="registerUser" class="was-validated">
<table>
	<tr>
		<td style="text-align: center">
			<h2>Registration Page</h2>
			<p>${message}</p> 
		</td>
	</tr>
	<tr>
		<td>
			<div class="form-group">
				<label for="firstName">First Name: </label>
				<form:input path="firstName" class="form-control" placeholder="Enter First Name" min="2" max="25" required="required"/>
				<div class="invalid-feedback"><form:errors path="firstName"/></div>
			</div>		
		</td>
	</tr>
	<tr>
		<td>
			<div class="form-group">
				<label for="lastName">Last Name: </label>
				<form:input path="lastName" class="form-control" placeholder="Enter Last Name" min="2" max="25" required="required"/>
				<div class="invalid-feedback"><form:errors path="lastName"/></div>
			</div>
		</td>
	</tr>
	<tr>
		<td>
			<div class="form-group">
				<label for="email">Email: </label>
				<form:input type="email" path="email" class="form-control" placeholder="Enter Email" min="2" max="35" required="required"/>
				<div class="invalid-feedback"><form:errors path="email"/></div>
			</div>			
		</td>
	</tr>
	<tr>
		<td>
			<div class="form-group">
				<label for="phoneNumber">Phone Number: </label>
				<form:input path="phoneNumber" class="form-control" placeholder="Enter Phone Number" min="8" max="12" required="required"/>
				<div class="invalid-feedback"><form:errors path="phoneNumber"/></div>
			</div>			
		</td>
	</tr>
	<tr>
		<td>
			<div class="form-group">
				<label for="usercredentials.username">Username: </label>
				<form:input path="usercredentials.username" class="form-control" placeholder="Enter Username" min="2" max="25" required="required"/>
				<div class="invalid-feedback"><form:errors path="usercredentials.username"/></div>
			</div>		
		</td>
	</tr>
	<tr>
		<td>
			<div class="form-group">
				<label for="usercredentials.password">Password: </label>
				<form:password path="usercredentials.password" class="form-control" placeholder="Enter password" min="6" max="25" required="required"/>
				<div class="invalid-feedback"><form:errors path="usercredentials.password"/></div>
			</div>
		</td>
	</tr>	
	<tr>
		<td colspan= "2" style="text-align: center">
		<input type= "submit" value= "Register" class="btn btn-primary">
		</td>
	</tr>
</table>
</form:form>