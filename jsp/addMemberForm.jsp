<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<h1>회원가입</h1>
<form action="/article/addMember.do">
 <div>
  <input type="text" name="loginId" placeholder="아이디를 입력해주세요."/>
 </div>
 <div>
  <input type="password" name="loginPw" placeholder="비밀번호를 입력해주세요."/>
 </div>
 <div>
  <input type="text" name="nickname" placeholder="이름을 입력해주세요."/>
 </div>
 <div>
  <input type="submit"> 
 </div>
</form>
</body>
</html>