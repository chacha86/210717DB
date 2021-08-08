<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>게시물 등록</h1>

<%
Class.forName("com.mysql.cj.jdbc.Driver");
String url = "jdbc:mysql://localhost:3306/k1?serverTimezone=UTC";
String user = "sbsst";
String password = "sbs123414";

Connection conn = DriverManager.getConnection(url, user, password); 
Statement stmt = conn.createStatement();

String title = request.getParameter("title");
String body = request.getParameter("body");

String sql = "INSERT INTO article " +
				"SET title = '" + title + "', " +
				"`body` = '" + body + "', " +
				"memberId = 1, " +
				"regDate = NOW(), " +
				"hit = 0 ";

stmt.executeUpdate(sql);

// index.jsp로 페이지 이동. - forward, redirect
// redirect
response.sendRedirect("/web-example2/index.jsp");

%>

</body>
</html>