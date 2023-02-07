<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>상품 주문하기</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/	npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>





</head>


<body>
<main role="main">
<div class="container-fluid">
   <jsp:include page="../part_header.jsp"/>
</div>
  
<!-- !PAGE CONTENT! -->
<div class="w3-main w3-content w3-padding" style="max-width:1200px;margin-top:100px">

  <!-- First Photo Grid-->
  <div class="w3-row-padding w3-padding-16 w3-center" id="food">
    <div class="w3-quarter">
      <a href="/shop/shopdetail"><img src="../resources/img/크리스피.png" alt="크리스피 바삭바삭" style="width:100%"></a>
      <h3>크리스피 바삭바삭</h3>
      <p>바삭바삭 쫄깃쫄깃 오동통통</p>
    </div>
    <div class="w3-quarter">
      <img src="../resources/img/치즈.png" alt="치즈솔솔 바삭바삭" style="width:100%">
      <h3>치즈솔솔 바삭바삭</h3>
      <p>치즈가루를 솔솔솔솔솔솔솔솔솔솔솔솔솔솔솔솔솔솔솔솔</p>
    </div>
    <div class="w3-quarter">	
      <img src="../resources/img/간장.png" alt="굽굽 바삭바삭" style="width:100%">
      <h3>굽굽바삭바삭</h3>
      <p>오븐에 구운 담백한 치킨</p>
    </div>
    <div class="w3-quarter">
      <img src="../resources/img/양념.png" alt="양념무쵸 바삭바삭" style="width:100%">
      <h3>양념무쵸바삭바삭</h3>
      <p>맛깔나는 양념을 버물버물</p>
    </div>
  </div>
  


  <!-- Pagination -->
  <div class="w3-center w3-padding-32">
    <div class="w3-bar">

    </div>
  </div>
  
  <hr id="about">

  <!-- About Section -->
  	<div class="container-fluid">
   <jsp:include page="../part_footer.jsp"/>
</div>

<!-- End page content -->
</div>

</main>
</body>
</html>