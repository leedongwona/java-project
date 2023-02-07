<%@page import="org.springframework.http.MediaType"%>
<%@page import="kr.co.common.utils.UploadFileUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>에러 상세정보</title>

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
	
<script src="/resources/js/templateform.js"></script>

<style type="text/css">
	#uploaded_list li {
		list-style-type : none;
		display : inline-block;
	}
</style>
</head>
<body>

	<div class="container-fluid">
		<jsp:include page="../part_header.jsp"/>
	</div>
		
		<!-- 에러 정보 상세출력  -->
		<div class="container">
			<div class="jumbotron">
				<h1 class="display-4">에러 상세정보</h1>
			</div>

			<div class="form-group row">
				<label for="eno">에러 번호</label> <input class="form-control" value="${dto.eno}" readonly>
			</div>
			
			<div class="form-group row">
				<label for="msg">내용</label>
				<textarea rows="5" id="msg" name="msg" class="form-control" readonly>${dto.msg}</textarea>
			</div>

			<div class="form-group row">
				<label for="staff">관리자</label> <input class="form-control" value="${dto.staff}" readonly>
			</div>

			<div class="form-group row">
				<label for="status">현재 상태</label> <input class="form-control" value="${dto.status}" readonly>
			</div>

			<div class="form-group row">
				<label for="errordate">발생일</label> <input class="form-control" value="${dto.errordate}" readonly>
			</div>
			
			<div class="form-group row">
				<label for="updatedate">최종 수정일</label> <input class="form-control" value="${dto.updatedate}" readonly>
			</div>
			
			<a href="/error/update/${dto.eno}" class="btn btn-primary my-3">에러 수정</a>
			<a href="/error/list" class="btn btn-primary my-3">에러 목록</a>
		
	</div>



</body>
</html>