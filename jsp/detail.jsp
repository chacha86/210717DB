<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="article.DBUtil" %>
<%@ page import="article.Article" %>
<%@ page import="article.Reply" %>
<%@ page import="java.util.ArrayList" %>
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
	//DBUtil 이용해서 댓글리스트 가져오기.
	
	ArrayList<Reply> replies = db.getArticleReplyByArticleId(article.getId());
	
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
	<span>작성자 : <%= article.getNickname() %></span>
</div>
<hr>
<div>
	<span>내용 : </span>
	<div><%= article.getBody() %></div>
</div>
<hr>
<a href="/web-example2/updateForm.jsp?id=<%= article.getId() %>">수정</a>
<a href="/web-example2/deleteArticle.jsp?id=<%= article.getId() %>">삭제</a>

<h3>댓글</h3>
<% for(int i = 0; i < replies.size(); i++) { %>
<div>
	<div>작성자 : <%=  replies.get(i).getNickname() %> </div>
	<div>내용 : <%=  replies.get(i).getBody() %> </div>
	<div>작성일 : <%=  replies.get(i).getRegDate() %></div>
</div>
<hr>
<%
}
%>
</body>
</html>