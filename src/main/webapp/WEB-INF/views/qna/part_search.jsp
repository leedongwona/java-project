<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <%-- target blank : 새 탭 생성 --%>
<form target="_blank">
	<div class="input-group mb-3">
		<div class="input-group-append">
			<select class="custom-select" id="criteria">
				<option value="title">제목</option>
				<option value="id">작성자</option>
				<option value="content">내용</option>
			</select>
		</div>

		<input type="text" class="form-control" placeholder="검색어를 입력하세요." aria-label="검색어를 입력하세요." id="keyword">

		<div class="input-group-append">
			<button class="btn btn-info" id="search_submit">검색</button>

		</div>
	</div>
</form>

