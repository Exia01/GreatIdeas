<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B"
			crossorigin="anonymous">
		<title>Subscriptions</title>
		<style>
			#header {
				display: flex;
				width: 70%;
				justify-content: space-around;
			}

			#mystate,
			#otherstate {
				width: 75%;
				margin: auto;
				margin-bottom: 50px;
			}

			.none {
				margin-bottom: 100px;
			}

			#create {
				margin-left: 30px;
				width: 35%;
			}

			.error {
				color: red;
			}

			.btn-danger {
				padding-top: 20 px;
			}
		</style>
	</head>

	<body>
		<div id="create">
			<h3>Create a new Idea:</h3>
			<c:if test="${errors != null}">
				<c:forEach items="${errors}" var="err">
					<p class="error">${err.defaultMessage}</p>
				</c:forEach>
			</c:if>
			<form:form method="post" action="/create" modelAttribute="newIdea">
				<div class="form-group">
					<form:label path="name">Content:</form:label>
					<br>
					<form:errors path="name" cssClass="error" />
					<form:input path="name" cssClass="form-control" />
				</div>
				<input type="submit" value="Create" class="btn btn-primary">
			</form:form>
			<br>
			<a href="/ideas" class="btn btn-warning">cancel</a>
		</div>

	</body>

	</html>