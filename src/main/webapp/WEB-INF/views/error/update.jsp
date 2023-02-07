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

<title>에러 정보 수정</title>

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

</head>
<body>

	<div class="container-fluid">
		<jsp:include page="../part_header.jsp"/>
	</div>
	
	
	<!-- 관리자, 현재상태 수정 -->
	<div class="container">
		<div class="jumbotron">
			<h1 class="display-4 text-center">에러 정보 수정</h1>
		</div>

		<form action="/error/update" method="post">
			
			<div class="form-group row">
				<label for="eno" class="col-sm-2 col-form-label text-right">에러 번호</label>
				<div class="col-sm-10">
					<input class="form-control" id="eno" name="eno" value="${dto.eno}" readonly>
				</div>
			</div>

			<div class="form-group row">
				<label for="msg" class="col-sm-2 col-form-label text-right">에러 메시지</label>
				<div class="col-sm-10">
					<textarea class="form-control" id="msg" name="msg" readonly>${dto.msg}</textarea>
				</div>
			</div>

			<div class="form-group row">
				<label for="staff" class="col-sm-2 col-form-label text-right">관리자</label>
				<div class="col-sm-10">
					<input class="form-control" id="staff" name="staff" value="${dto.staff}" required>
				</div>
			</div>

			<div class="form-group row">
				<label for="status" class="col-sm-2 col-form-label text-right">현재 상태</label>
				<div class="col-sm-10">
					<select name="status">
						<optgroup label="status">
    						<option value="ERROR">ERROR</option>
    						<option value="FIXING">FIXING</option>
    						<option value="COMPLETE">COMPLETE</option>
 						 </optgroup>
					</select>
				</div>
			</div>

			<div class="form-group row">
				<label for="errordate" class="col-sm-2 col-form-label text-right">에러 발생일</label>
				<div class="col-sm-10">
					<input class="form-control" id="errordate" name="errordate" value="${dto.errordate}" readonly>
				</div>
			</div>

			<div class="form-group row"  style="display: none">
				<label for="updatedate" class="col-sm-2 col-form-label text-right">최종 수정일</label>
				<div class="col-sm-10">
					<input class="form-control" id="updatedate" name="updatedate" value="${dto.updatedate}" required>
				</div>
			</div>
			
			<div class="col-sm-10">
				<button type="submit" class="btn btn-warning">수정</button>
			</div>

		</form>
	</div>


</body>
</html>