<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>회원목록</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>

</head>

<body>

<div class="container-fluid">
	<jsp:include page="../part_header.jsp"/>
</div>

	<div class="container">
		<div class="jumbotron">
				<h1 class="display-4">회원목록</h1>
		</div>
		
		
		<form action="/admin/delete" method="post">
		
		<!-- 회원목록 테이블 -->
		<table class="table">
			<thead>
				<tr>
					<th scope="col" class="text-center">아이디</th>
					<th scope="col" class="text-center">이름</th>
				</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${list}" var="dto">
				<tr>
					<!-- 체크박스 사용 -> 다중삭제 기능 -->
					<td class="text-center"><input name="id" type="checkbox" value="${dto.id}"/>${dto.id}</td>
					<td class="text-center">${dto.name}</td>
				</tr>
			</c:forEach>
		
			</tbody>
		</table>
		<a class="btn btn-danger" onclick="checkdelete()">선택 삭제</a>

		
		</form>
	
	</div>
	
<script type="text/javascript">

	// 체크된 값들을 admin/delete 로 전송하는 함수
	function checkdelete(){
		let checkboxArr = [];
		$("input:checkbox[name='id']:checked").each(function(){
			checkboxArr.push($(this).val());
		});
		
		$.ajax({
			url : "/admin/delete",
			type : "POST",
			data : {
				"checkboxArr" : checkboxArr
			},
			success : function(result){
				if(result > 0) {
					alert("삭제 되었습니다.");
					location.assign("/admin/list");
				} else{
					alert("오류가 발생했습니다.");
				}
			}
			
		});
			
	}
		

</script>	

</body>
</html>