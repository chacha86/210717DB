<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>form 테스트</h1>


<!-- form queryString 생성기. action속성값으로 목적지 정한다. 그리고 form 태그로 감싸여진
input 태그들의 값을 name을 키로 삼아서 목적지로 넘긴다. -->
<form action="/web-example2/test.jsp">
	<!-- 
		input태그
		  - text : 입력창
		  - submit : 전송 버튼
		  
		textarea 태그 : 대량의 텍스트
	 -->
	<input type="text" value="" name="dan"> <!-- val1=aaa&val2=bbb&val3=ccc -->
	<input type="text" value="" name="limit">
	<input type="submit">
</form>

</body>
</html>