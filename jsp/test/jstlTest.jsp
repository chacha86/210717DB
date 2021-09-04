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


<%
 
  request.setAttribute("num", 3);

%>

<c:choose>
  <c:when test="${ num == 1 }">
    참!!
  </c:when>
  <c:otherwise>
    거짓!!
  </c:otherwise>
</c:choose>

<c:choose>
  <c:when test="${ num < 2 }">
    2보다 작습니다.
  </c:when>
  
  <c:when test="${ num < 5 }">
    5보다 작습니다.
  </c:when>
  
  <c:when test="${ num < 10 }">
    10보다 작습니다.
  </c:when>
  
  <c:otherwise>
    10보다 크거나 같습니다.
  </c:otherwise>
</c:choose>


</body>
</html>