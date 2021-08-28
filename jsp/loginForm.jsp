<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>로그인</h1>

<form action="/article/login.do">
 <div>
   <div>
     <input type="text" name="loginId" placeholder="아이디를 입력해주세요.">
   </div>
   <div>
     <input type="password" name="loginPw" placeholder="비밀번호를 입력해주세요.">
   </div>
   <input type="submit" value="로그인" />
 </div>
</form>

</body>
</html>