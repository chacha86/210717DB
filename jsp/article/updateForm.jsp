<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="article.DBUtil" %>
<%@ page import="article.Article" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정페이지</title>
</head>
<body>
<h1>수정페이지</h1>
<%
	
%>

<form action="/article/update.do">
	<div>
		<input type="hidden" name="id" value="${ article.id }" />
	</div>
	<div>
		<input type="text" name="title" value="${ article.title }"/>
	</div>
	<div>
		<textarea name="body" cols="25" rows="10">${ article.body }</textarea>
	</div>
	<div>
		<input type="submit"> 
	</div>
</form>
</body>
</html>