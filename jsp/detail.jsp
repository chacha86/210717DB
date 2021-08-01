<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="article.DBUtil" %>
<%@ page import="article.Article" %>
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
<%	
	String id = request.getParameter("id");
	DBUtil db = new DBUtil();
	Article article = db.getArticleById(id);
%>
<div>
	<span>번호 : <%= article.getId() %></span>
</div>
<hr>
<div>
	<span>제목 : <%= article.getTitle() %></span>
</div>
<hr>
<div>
	<span>작성자 : <%= article.getMemberId() %></span>
</div>
<hr>
<div>
	<span>내용 : </span>
	<div><%= article.getBody() %></div>
</div>
<hr>
<a href="/web-example2/updateForm.jsp?id=<%= article.getId() %>">수정</a>
<a href="/web-example2/deleteArticle.jsp?id=<%= article.getId() %>">삭제</a>

</body>
</html>