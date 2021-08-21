<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="article.DBUtil" %>
<%@ page import="article.Article" %>
<%@ page import="article.Reply" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세</title>
<style>
	
</style>
</head>
<body>
<h1>게시물 상세</h1>
<div>
	<span>번호 : ${ article.id }</span>
</div>
<hr>
<div>
	<span>제목 : ${ article.title }</span>
</div>
<hr>
<div>
	<span>작성자 : ${ article.nickname }</span>
</div>
<hr>
<div>
	<span>내용 : </span>
	<div>${ article.body }</div>
</div>
<hr>
<a href="/web-example2/updateForm.jsp?id=${ article.id }">수정</a>
<a href="/web-example2/deleteArticle.jsp?id=${ article.id }">삭제</a>

<h3>댓글</h3>
<c:forEach items="${ replies }" var="reply">
<div>
	<div>작성자 : ${reply.nickname} </div>
	<div>내용 : ${ reply.body } </div>
	<div>작성일 : ${ reply.regDate }</div>
</div>
<hr>
</c:forEach>
<form action="/web-example2/addReply.jsp">
	<div>
		<div>홍길동</div>
		<div>
			<input type="text" name="rbody" placeholder="댓글을 남겨보세요"/>
			<input type="hidden" name="aid" value="${ article.id }"/>
		</div>
		<div><input type="submit" value="등록"></div>
	</div>
</form>

</body>
</html>