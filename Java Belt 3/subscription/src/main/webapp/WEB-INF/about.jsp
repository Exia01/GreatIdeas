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
	<div id="header">
		<h1> ${idea.name}</h1>
		<a href="/ideas" class="btn-sm btn-warning">Go Back</a>
	</div>

	<h2>Created by: ${idea.iCreator.fName} ${idea.iCreator.lName}</h2>
	<table class="table table-bordered" id="">
		<thead>
			<tr>
				<th>Name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${idea.diggers}" var="info">
			<tr>
					<td>${info.fName} ${info.lName}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/ideas/edit/${idea.id}" class="btn btn-success">Edit</a>



</body>

</html>