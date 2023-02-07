<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>게시글 수정 화면</title>

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
<script type="text/javascript" src="/resources/js/templateform.js"></script>

<style type="text/css">
	#file_drop{
		width: 100%;
		height : 150px;
		border : 1px solid gray;
		background-color: #FFFFFF;
		margin : auto;
	}
	#uploaded_list li{
		list-style-type: none;
		dispaly:inline-blcok;
	}

</style>


</head>
<body>

<div class="container-fluid">
	<jsp:include page="../part_header.jsp"/>
</div>
	<div class="container">
		<div class="jumbotron">
			<h1 class="display-4">게시글 수정 화면</h1>
		</div>

		<form action="/board/update" method="post">
			<div class="form-group row">
				<label for="id">작성자</label>
				<input class="form-control" id="id" name="id" value="${dto.id}" required>
			</div>
			
			<div class="form-group row">
				<label for="pw">비밀번호</label>
				<input type="password" class="form-control" id="pw" name="password" required>
			</div>
			
			<div class="form-group row">
				<label for="title">제목</label>
				<input class="form-control" id="title" name="title" value="${dto.title}" required>
			</div>			

			<div class="form-group row">
				<label for="content">내용</label>
				<textarea rows="10" id="content" name="content" class="form-control" required>${dto.content}</textarea>
			</div>
			
			
		</form>
		
		
		<div class="form-group row">
			<label>업로드 할 파일을 드랍시키세요.</label>
			<div id="file_drop">	
			</div>
		</div>
			
		<div class="form-group">
			<ul id="uploaded_list" class="text-center row">	
			</ul>
		</div>
		
		<div class="form-group row">
			<button type="submit" class="btn btn-primary boardsubmit">등록</button>
		</div>
		
	</div>

	<script type="text/javascript">
	
		$(document).ready(function() {
			let formData = new FormData();
			let uploadedFileDeleteList = [];
    	  
		$("#uploaded_list").on("click", ".del", function(){
			let filename = $(this).prev().prev().attr("alt");
			uploadedFileDeleteList.push(filename);
    		 
			$(this).parent().parent().parent().remove();
		});
    	  
		$.getJSON("/board/getFilenames?bno="+${dto.bno}, function(result){
			for(let i=0; i<result.length; i++){
				let filename = result[i];
				let tag = ufshowform2(result, filename);
				$("#uploaded_list").append(tag);
					
			}
				
		});
    	  
		$("#uploaded_list").on("click", ".del", function(event){
			event.preventDefault();
			let filename = $(this).prev().text();
			formData.delete(filename);
			  
			$(this).parent().parent().parent().remove();
		});	  
    	  
		$("#file_drop").on("dragenter dragover", function(event){
			event.preventDefault();
			});
    	  
		$("#file_drop").on("drop", function(event){
			event.preventDefault();
			let reader = new FileReader();
			let fArr = event.originalEvent.dataTransfer.files;
			let file = fArr[0];
			
			reader.readAsDataURL(file);
			
			reader.onload = function(event){
				let tag = futest2(event.target.result, file["name"]);
				$("#uploaded_list").append(tag);
			}
			
			formData.append(file["name"], file);
		});
    	  
    	  
		$(".boardsubmit").on("click", function(event){
    		  
			event.preventDefault();
			let id = $("#id").val();
			let pw = $("#pw").val();
			let title = $("#title").val();
			let content = $("#content").val();
    		  
			if(id == ""){
				$("#id").focus();
				return false;
			}
    		  
			if(pw == ""){
				$("#pw").focus();
				return false;
			}
    		  
			if(title == ""){
				$("#title").focus();
				return false;
			}
    		  
			if(content == ""){
				$("#content").focus();
				return false;
			}
    		  
			formData.append("id", id);
			formData.append("pw", pw);
			formData.append("title", title);
			formData.append("content", content);
			formData.append("uploadedFileDeleteList", uploadedFileDeleteList);
  	  	  
    	  	  
			$.ajax({
				url : "/board/update/${dto.bno}",
				type : "post",
				processData : false,
				contentType : false,
				data : formData,
				dataType : "text",
				success : function(result){
					if(result >= 1){
						location.assign("/board/read/${dto.bno}/${curpage}/${criteria}${empty keyword? '' : '/'}${keyword}");
					}else{
						alert("수정을 실패했습니다.");
						$("#pw").select();
						formData.delete("pw");
						formData.delete("title");
						formData.delete("content");
						formData.delete("uploadedFileDeleteList");
					}
    	  			  
				}
    	  			 
			});
    	
		});
    	  
	});
	</script>


</body>
</html>