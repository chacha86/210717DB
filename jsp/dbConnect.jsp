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
<h1>DB 데이터 가져오기</h1>

<%
Class.forName("com.mysql.cj.jdbc.Driver");
String url = "jdbc:mysql://localhost:3306/k1?serverTimezone=UTC";
String user = "sbsst";
String password = "sbs123414";

Connection conn = DriverManager.getConnection(url, user, password); 
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("select * from article");

while(rs.next()) {
	
	String title = rs.getString("title");
	String body = rs.getString("body");
	
	out.println(title);
	out.println(body);
	out.println("<hr>");
}

%>

</body>
</html>