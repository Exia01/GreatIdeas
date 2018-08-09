<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B"
	crossorigin="anonymous">
<link rel="stylesheet" href="static/css/loginregister.css">
<title>Login and Registration</title>
</head>

<body>

<h1>Login and Registration</h1>
<c:if test="${registerError != null}">
	<p class="red">${registerError}</p>
</c:if>

<c:if test="${errors != null}">
	<c:forEach items="${errors}" var="error">
		<p class="red">${error.defaultMessage}</p>
	</c:forEach>
</c:if>

<div id='container'>
	<div id='register'>
		<h3>Register here!</h3>
		<form:form method="post" action="/register" modelAttribute="user">
			<div class="form-group">
				<form:label path="fName">First Name:</form:label>
				<br>
				<form:errors path="fName" cssClass="error" />
				<form:input path="fName" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="lName">Last Name:</form:label>
				<br>
				<form:errors path="lName" cssClass="error" />
				<form:input path="lName" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="email">Email:</form:label>
				<br>
				<form:errors path="email" cssClass="error" />
				<form:input path="email" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="password">Password:</form:label>
				<br>
				<form:errors path="password" cssClass="error" />
				<form:password path="password" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="passwordConfirm">Confirm Password:</form:label>
				<br>
				<form:errors path="passwordConfirm" cssClass="error" />
				<form:password path="passwordConfirm" cssClass="form-control" />
			</div>
			<button type="submit" value='Register' class='btn btn-success'>Register!</button>
		</form:form>
	</div>


	
	<div id='login'>
		<h3>Login here!</h3>
		<p class="red">
			<c:out value="${loginerror}" />
		</p>
		<form action="/login" method='post'>
			<div class="form-group">
				<label for="email">Email:</label>
				<input type="text" class="form-control" id="email" name="email" placeholder="Email">
			</div>
			<div class="form-group">
				<label for="password">Password:</label>
				<input type="password" class="form-control" id="password" name="password" placeholder="Password">
			</div>
			<button type="submit" value='Login' class='btn btn-info'>Login!</button>
		</form>
	</div>
</div>

</body>

</html>