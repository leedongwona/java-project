<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>${dto.grade=="A"? '회원정보' : '관리자 게시판'}</title>

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


<style type="text/css">
.addBorder {
	
}
</style>


</head>

<body>



	<div class="container-fluid">
		<jsp:include page="../part_header.jsp" />
	</div>

	<div class="container">

		<div class="jumbotron">
			<!-- grade = A 일 시 회원목록 출력, 아닐경우 관리자 게시판 출력 -->
			<h1 class="display-4 text-center">${dto.grade=="A"? '회원정보' : '관리자 게시판'}</h1>
		</div>

		<!-- 일반회원 출력화면  -->
		<c:if test='${dto.grade=="A"}'>
		
		
			
			<!-- 회원상세정보 테이블  -->
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<th scope="col" class="col-sm-3 text-right">아이디</th>
						<td scope="col">${dto.id}</td>
					</tr>

					<tr>
						<th scope="col" class="col-sm-3 text-right">이름</th>
						<td scope="col">${dto.name}</td>
					</tr>

					<tr>
						<th scope="col" class="col-sm-3 text-right">회원 등급</th>
						<td scope="col">${dto.grade}</td>
					</tr>

					<tr>
						<th scope="col" class="col-sm-3 text-right">생년월일(나이)</th>
						<td scope="col">${dto.birth}(만${dto.age}세)</td>
					</tr>

					<tr>
						<th scope="col" class="col-sm-3 text-right">이메일</th>
						<td scope="col">${dto.email}</td>
					</tr>

					<tr>
						<th scope="col" class="col-sm-3 text-right">주소</th>
						<td scope="col">${dto.address}</td>
					</tr>

				</tbody>
			</table>
			
			<div class="menus text-center mt-5">
				<button type="button" class="btn btn-warning" id="mupdate">회원
					정보 수정</button>
				<button type="button" class="btn btn-warning" id="pupdate">비밀번호
					변경</button>
				<button type="button" class="btn btn-warning" id="mdelete">회원
					탈퇴</button>
			</div>
	</div>

	<!-- 비밀변호 변경 클릭 시 나오는 modal -->
	<div class="modal" tabindex="-1" id="modal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">비밀번호 변경</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				
				<!-- 필요정보 입력 -->
				<div class="modal-body">
					<div class="form-group row text-center">
						<span id="failmsg" class="col-sm-12"></span>
					</div>

					<div class="form-group row">
						<label for="pw1" class="col-sm-2 col-form-label text-right">기존
							비밀번호</label>
						<div class="col-sm-10">
							<input type="pw" class="form-control" id="pw"
								name="pw">
						</div>
					</div>

					<div class="form-group row">
						<label for="pw1" class="col-sm-2 col-form-label text-right">새
							비밀번호</label>
						<div class="col-sm-10">
							<input type="pw" class="form-control" id="npw"
								name="npw">
						</div>
					</div>

					<div class="form-group row">
						<label for="pw1" class="col-sm-2 col-form-label text-right">새
							비밀번호(확인)</label>
						<div class="col-sm-10">
							<input type="pw" class="form-control" id="npw2">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-warning" id="changepw">비밀번호
						변경</button>
				</div>
			</div>
		</div>
	</div>
	</c:if>
	
	<!-- 관리자 출력화면  -->
	<c:if test='${dto.grade=="B"}'>
	
		<table class="table table-bordered ">
				<tbody>
					<tr>
						<th scope="col" class="col-sm-3 text-right">아이디</th>
						<td scope="col">${dto.id}</td>
					</tr>

					<tr>
						<th scope="col" class="col-sm-3 text-right">이름</th>
						<td scope="col">${dto.name}</td>
					</tr>

					<tr>
						<th scope="col" class="col-sm-3 text-right">회원 등급</th>
						<td scope="col">${dto.grade}</td>
					</tr>

					<tr>
						<th scope="col" class="col-sm-3 text-right">생년월일(나이)</th>
						<td scope="col">${dto.birth}(만${dto.age}세)</td>
					</tr>

					<tr>
						<th scope="col" class="col-sm-3 text-right">이메일</th>
						<td scope="col">${dto.email}</td>
					</tr>

					<tr>
						<th scope="col" class="col-sm-3 text-right">주소</th>
						<td scope="col">${dto.address}</td>
					</tr>

				</tbody>
			</table>
	
		<!-- 관리자 게시판에만 추가된 버튼  -->
		<div class="menus text-center mt-5">
			<button type="button" class="btn btn-warning" id="member_control">회원 관리</button>
			<button type="button" class="btn btn-warning" id="error_control">에러 관리</button>
			<button type="button" class="btn btn-warning" id="delete_member_view">탈퇴 회원목록</button>
		</div>
		
	</c:if>


	<script type="text/javascript">
		let id = "${dto.id}";
		$(document).ready(function() {
			
			// 회원탈퇴 버튼 클릭시 /member/delete 로 값 전송
			$("#mdelete").click(function() {
				let pw = prompt("비밀번호를 입력하세요.");

				$.ajax({
					url : "/member/delete/" + id,
					type : "post",
					data : {
						"pw" : pw
					},
					dataType : "text",
					success : function(result) {
						if (result > 0) {
							alert("회원 탈퇴되었습니다, 이용해주셔서 감사합니다.");
							location.assign("/board/list");
						} else {
							alert("비밀번호를 확인해주세요.");
						}
					}
				})
			});
			
			// 비밀번호변경 클릭 시 작동
			$("#modal").on("click", "button[data-dismiss='modal']", function() {
				defaultPwInput();
			});
			
			// modal 의 비밀번호변경 클릭 시 작동
			$("#changepw").click(function() {
				let pw = $("#pw").val();
				let npw = $("#npw").val();
				let npw2 = $("#npw2").val();
				
				// 유효성 검사
				if (pw == "") {
					$("#pw").focus();
					return false;
				}

				if (npw == "") {
					$("#npw").focus();
					return false;
				}

				if (npw2 == "") {
					$("#npw2").focus();
					return false;
				}

				if (npw != npw2) {
					alert("새 비밀번호와 새 비밀번호(확인)이 일치하지 않습니다.")
					$("#npw2").val("");
					$("#npw").select();
					$("#npw").focus();
					return false;
				}
				
				// /member/chagepw 로 값 전송
				$.ajax({
					url : '/member/changepw',
					type : "post",
					data : {
						"pw" : pw,
						"npw" : npw,
						"id" : "${dto.id}"
					},
					dataType : "text",
					success : function(result) {
						if (result > 0) {
							defaultPwInput();

							$("#modal").modal("toggle");
						} else {
							// 비밀번호 변경 실패 시 경고 문구 출력
							$("#failmsg").text(" 현재 비밀번호를 확인해주세요. ");
							$("#failmsg").css("color", "red");
							$("#failmsg").addClass("addBorder");
						}

					}
				});

			});
			
			// 회원목록 클릭 시 작동
			$("#member_control").click(function() {
				location.assign("/admin/list");
			});
			
			// 삭제회원목록 클릭 시 작동
			$("#delete_member_view").click(function() {
				location.assign("/admin/delete_list");
			});
			
			// 에러목록 클릭 시 작동
			$("#error_control").click(function() {
				location.assign("/error/list");
			});
			
			// 비밀번호 클릭 시 작동
			$("#pupdate").click(function() {
				$("#modal").modal("toggle");
			});
			
			// 회원 정보 수정 클릭 시 작동
			$("#mupdate").click(function() {
				location.assign("/member/update/${dto.id}");
			});
			
			// 입력된 값들을 없애고 출력된 경고문구 제거
			function defaultPwInput() {
				$("#pw").val("");
				$("#npw").val("");
				$("#npw2").val("");
				$("#failmsg").text("");
				$("#failmsg").removeClass("addBorder")
			}

		});
	</script>

</body>
</html>