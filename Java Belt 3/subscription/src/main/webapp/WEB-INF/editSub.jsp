<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B"
crossorigin="anonymous">
<style>
#editform {
	width: 25%;
}

h1,
h3,
#editform {
	margin-left: 30px;
}

.error {
	color: red;
	font-weight: bold;
}
</style>
<title>Edit Show: ${editMediaShow.id}</title>
</head>

<body>
<div id="edit">
<h3>Edit ${editMediaShow.name}:</h3>
<c:if test="${errors != null}">
	<c:forEach items="${errors}" var="err">
		<p class="error">${errors}</p>
	</c:forEach>
</c:if>
<form:form method="POST" action="/ideas/update/${editMediaShow.id}" modelAttribute="editMediaShow">
	<div class="form-group">
		<form:label path="name">Title:</form:label>
		<br>
		<form:errors path="name" cssClass="error" />
		<form:input path="name" cssClass="form-control" />
	</div>

	<input type="submit" value="Update" class="btn btn-primary">
</form:form>
<br>
<a href="/ideas" class="btn btn-warning">cancel</a> || <a href="/delete/${editMediaShow.id}" class="btn btn-danger">Delete</a>
</div>

</body>

</html>