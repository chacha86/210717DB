<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>구구단</h1>

<%
	// 내장객체 - JSP에서 다양한 처리를 지원하기 위해 미리 제공하는 유용한 객체
	// out - 출력에 관한 기능을 제공.
	// request - 요청 처리에 대한 기능 제공. ex) queryString 파라미터를 가져오거나, 요청을 위임 등
    // response - 응답 처리에 대한 기능 제공. ex) 다른 페이지를 새로 요청.
    
	String sdan = request.getParameter("dan"); // 3
	String slimit = request.getParameter("limit"); // 10
	
	int dan = Integer.parseInt(sdan);
	int limit = Integer.parseInt(slimit);
	
	for(int i = 1; i < limit ; i++) {
		out.println(dan + " X " + i + " = " + dan * i);
%>
		<br>
<%
	}
%>

</body>
</html>