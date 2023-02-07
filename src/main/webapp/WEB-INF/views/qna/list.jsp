<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>${empty pt.criteria? 'QnA' : 'QnA 검색 목록'}</title>
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
				<h1 class="display-4">${empty pt.criteria? 'QnA' : 'QnA 검색 목록'}</h1>
		</div>
		<header>
			<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
				<a class="navbar-brand" href="/mainpage/mainhome">NotNull Chiken</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarCollapse" aria-controls="navbarCollapse"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarCollapse">
					<ul class="navbar-nav mr-auto">
						<jsp:include page="../part_header.jsp" />
					</ul>
					<form class="form-inline mt-2 mt-md-0"></form>
				</div>
			</nav>
		</header>

		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
			</ol>
		</div>

		<a href="/qna/insert" class="btn btn-primary my-3">글쓰기</a>

					<table class="table">
						<thead>
							<tr>
								<th scope="col" class="text-center">글번호</th>
								<th scope="col" class="text-center">제목</th>
								<th scope="col" class="text-center">작성자</th>
								<th scope="col" class="text-center">조회수</th>
								<th scope="col" class="text-center">작성일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="dto">
								<tr>
									<td class="text-center">${dto.bno}</td>
									<td><a
										href="/qna/read/${dto.bno}/${pt.curpage}/${pt.criteria}${empty pt.keyword? '' : '/'}${pt.keyword}">${dto.title}</a></td>
									<td class="text-center">${dto.id}</td>
									<td class="text-center">${dto.readcnt}</td>
									<td class="text-center">${dto.regdate}</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>

					<jsp:include page="part_paging.jsp" />
					<jsp:include page="part_search.jsp" />
	</div>


<script type="text/javascript">
	$(document).ready(function(){
		$("form").on("click", "#search_submit", function(event){
			event.preventDefault();
			let criteria = $("#criteria").val();
			let keyword = $("#keyword").val();
			let uri = "/qna/list/"+criteria+"/"+keyword;
				 
			if(keyword == ""){
				alert("검색어를 입력해주세요.")
				
				return false;
			}
			
			$("form").attr("action", uri);
			
			$("form").submit();
		});
	});


	</script>



</body>
</html>