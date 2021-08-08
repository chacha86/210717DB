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
	String rbody = request.getParameter("rbody");
	String aid = request.getParameter("aid");
	
	db.addReply(rbody, aid);
	response.sendRedirect("/web-example2/detail.jsp?id=" + aid);
	
%>
</body>
</html>