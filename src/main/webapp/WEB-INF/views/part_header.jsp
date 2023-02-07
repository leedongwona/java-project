<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
li{
list-style: none; 
}

</style>



<header>
  <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="/mainpage/mainhome">NotNull Chicken</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
        <c:choose>
         <c:when test="${empty login}">
         <li>
          <a class="nav-link" href="/member/login">login </a> 
          </li>
           <li>
          <a class="nav-link" href="/member/insert">join </a>
          </li>
        </c:when>
 		
      <c:otherwise>
        <span style="color:white">${login.id}님, 환영합니다.</span>
         <a href="/member/logout">logout</a> |
         <a href="/member/read/${login.id}">my page</a> |
      </c:otherwise>
        </c:choose>
        <li>
        <a class="nav-link" href="/member/insert">cart </a> 
        </li>
      </ul>
      
    </div>
  </nav>
</header>