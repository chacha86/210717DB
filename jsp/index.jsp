<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="article.DBUtil" %>
<%@ page import="article.Article" %>

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

<h1>게시물 목록</h1>

<body>
	<div class="col">번호</div>
	<div class="col title">제목</div>
	<div class="col">작성자</div>
	<div class="col regDate">작성일</div>
	<div class="col">조회수</div>
	<div class="col">좋아요</div>
	<hr>
	<%
		DBUtil dbutil = new DBUtil();
		ArrayList<Article> articles = dbutil.getArticleList();
		
		for(int i = 0; i < articles.size(); i++) {
			
	%>
	
	<div>
		<div class="col"><%= articles.get(i).getId() %></div>
		<div class="col title"><a href="/web-example2/detail.jsp?id=<%= articles.get(i).getId() %>"><%= articles.get(i).getTitle() %>[<%= articles.get(i).getRcnt() %>]</a></div>
		<div class="col"><%= articles.get(i).getNickname() %></div>
		<div class="col regDate"><%= articles.get(i).getRegDate() %></div>
		<div class="col"><%= articles.get(i).getHit() %></div>
		<div class="col">0</div>
	</div>
	
	<% } %>
	<hr>
	<a href="/web-example2/addForm.jsp">글쓰기</a>
</body>
</html>