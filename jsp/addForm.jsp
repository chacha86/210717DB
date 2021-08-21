<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>게시물 등록</h1>
<% //공통코드 %>

<form action="/web-example2/addArticle.jsp">
	<div>
		<input type="text" name="title" placeholder="제목을 입력해주세요."/>
	</div>
	<div>
		<textarea name="body" placeholder="내용을 입력해주세요." cols="25" rows="10"></textarea>
	</div>
	<div>
		<input type="submit"> 
	</div>
</form>

</body>
</html>