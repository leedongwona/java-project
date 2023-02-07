<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>에러 목록</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
	crossorigin="anonymous"></script>

</head>

<body>

	<div class="container-fluid">
		<jsp:include page="../part_header.jsp" />
	</div>

	<div class="container">
	
		<div class="jumbotron">
			<h1 class="display-4">에러 목록</h1>
		</div>
		
		<!-- nav bar 사용하여 에러 현재 상태에 따라 출력(FIXING) -->
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
  			<a class="navbar-brand">STATUS</a>
  			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    			<span class="navbar-toggler-icon"></span>
 			</button>
  			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    			<div class="navbar-nav">
    				<a class="nav-link" href="/error/list">ALL</a>
					<a class="nav-link" href="/error/list_error">ERROR</a>
					<a class="nav-link active" href="/error/list_fixing">FIXING<span class="sr-only">(current)</span></a>
					<a class="nav-link" href="/error/list_complete">COMPLETE</a>
    			</div>
  			</div>
		</nav>
		
		<!-- 에러목록 테이블  -->
		<table class="table">
			<thead>
				<tr>
					<th scope="col" class="text-center">에러 번호</th>
					<th scope="col" class="text-center">현재 상태</th>
					<th scope="col" class="text-center">에러 발생일</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${list}" var="dto">
					<tr>
						<td class="text-center"><a href="/error/read/${dto.eno}">${dto.eno}</a></td>
						<td class="text-center">${dto.status}</td>
						<td class="text-center">${dto.errordate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</div>

</body>
</html>