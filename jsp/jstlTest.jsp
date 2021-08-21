<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
  ArrayList<String> slist = new ArrayList<String>();
  slist.add("aaa");
  slist.add("bbb");
  slist.add("ccc");
  request.setAttribute("c", slist);
%>

<!-- el/jstl -->
<c:forEach items="${c}" var="aa">
 ${aa}
</c:forEach>

<!-- 게시물 목록을 제목, 내용만 출력 (JSTL 이용해서) -->
<c:forEach items="${articles}" var="article">
 제목: ${article.title}<br>
 내용: ${article.body}<hr>
</c:forEach>


</body>
</html>