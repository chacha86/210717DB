<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="article.DBUtil" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	DBUtil db = new DBUtil();
	String title = request.getParameter("title");
	String body = request.getParameter("body");
	String id = request.getParameter("id");
	
	db.updateArticle(title, body, id);
	response.sendRedirect("/web-example2/detail.jsp?id=" + id);
%>

</body>
</html>