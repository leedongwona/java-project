<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>탈퇴 회원 목록</title>
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
			<h1 class="display-4">탈퇴 회원 목록</h1>
		</div>
		
		<!-- 탈퇴회원 목록테이블 -->
		
		<table class="table">
			<thead>
				<tr>
					<th scope="col" class="text-center">아이디</th>
					<th scope="col" class="text-center">이름</th>
					<th scope="col" class="text-center">탈퇴 날짜</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${list}" var="dto">
					<tr>
						<td class="text-center">${dto.id}</a></td>
						<td class="text-center">${dto.name}</td>
						<td class="text-center">${dto.deletedate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</div>

</body>
</html>