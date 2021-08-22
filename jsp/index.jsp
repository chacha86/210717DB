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

<a href="/article/showMember.do">회원가입</a>

<h1>게시물 목록</h1>

<body>
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
</body>
</html>