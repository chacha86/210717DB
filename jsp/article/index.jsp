<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="article.DBUtil" %>
<%@ page import="article.Article" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.col {
	display: inline-block;
	width: 50px;
	font-size: 10px;
}

.title {
	width: 150px;
}

.regDate {
	width: 100px;
}
</style>

<!-- 로그인 했을 때 나와야 되는 것 -->
<c:choose>
  <c:when test="${ sessionScope.loginUser != null}">
     <div>
       ${ sessionScope.loginUser }님 안녕하세요!!
       <a href="/article/logout.do">로그아웃</a>
     </div>
  </c:when>
  <c:otherwise>
    <div>
      <a href="/article/showMember.do">회원가입</a> 
      <a href="/article/showLogin.do">로그인</a>
    </div>
  </c:otherwise>
</c:choose>

<h1>게시물 목록</h1>

<body>
    <div>
     	<div class="col">번호</div>
     	<div class="col title">제목</div>
     	<div class="col">작성자</div>
     	<div class="col regDate">작성일</div>
     	<div class="col">조회수</div>
     	<div class="col">좋아요</div>
     	<hr>
        <c:forEach items="${articles}" var="article">
     	<div>
     		<div class="col">${article.id}</div>
     		<div class="col title"><a href="/article/detail.do?id=${article.id}">${article.title}[${article.rcnt}]</a></div>
     		<div class="col">${article.nickname}</div>
     		<div class="col regDate">${article.regDate}</div>
     		<div class="col">${article.hit}</div>
     		<div class="col">0</div>
     	</div>
     	<hr>
         </c:forEach>
     	<a href="/article/showAdd.do">글쓰기</a>
    </div>
    <div>
		<c:if test="${ page.currentPageBlock != 1 }">
    		<a href="/article/list.do?pageNum=${ page.startNoInBlock - 1 }">이전</a>
		</c:if>    
		<c:forEach var="pageNum" begin="${ page.startNoInBlock }" end="${ page.endNoInBlock }">
		  <c:choose>
		    <c:when test="${ pageNum == page.currentPageNo}">
		      <a href="/article/list.do?pageNum=${ pageNum }">[${ pageNum }]</a>    
		    </c:when>
		    <c:otherwise>
		      <a href="/article/list.do?pageNum=${ pageNum }">${ pageNum }</a>   
		    </c:otherwise>
		  </c:choose>
       </c:forEach>
       <c:if test="${ page.currentPageBlock < page.lastBlockNo }">
       		<a href="/article/list.do?pageNum=${ page.endNoInBlock + 1 }">다음</a>
       </c:if>
    </div>
</body>
</html>