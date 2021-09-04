<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %> 
<%@ page import="article.Article" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
  request.setAttribute("a", 10);
  request.setAttribute("b", "test123");
  
  ArrayList<String> slist = new ArrayList<String>();
  slist.add("aaa");
  slist.add("bbb");
  slist.add("ccc");
  request.setAttribute("c", slist);
  
  Article article = new Article(1, "aaaa", "bbbb", 1, "20210815", 0, "ccc", 0);
  request.setAttribute("d", article);
%>

${ d.title }
</body>
</html>