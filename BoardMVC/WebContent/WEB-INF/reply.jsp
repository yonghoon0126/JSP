<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>답변 글 쓰기 화면</title>
</head>
<body>
<h1>답변 글 쓰기 화면</h1>
<form action="reply.do" method="post">
	<input type="hidden" name="num"       value="${replyui.num}">
	<input type="hidden" name="repRoot"   value="${replyui.repRoot}">
	<input type="hidden" name="repStep"   value="${replyui.repStep}">
	<input type="hidden" name="repIndent" value="${replyui.repIndent}">
	
	원래글번호${replyui.num}&nbsp; 조회수${replyui.readcnt}<br>
	제목<input type="text"  name="title" value="${replyui.title}"><br>
	작성자<input type="text" name="author"><br>
	내용<textarea name="content" rows="10">${replyui.content}</textarea><br>
	<input type="submit"   value="답변달기"><br>
</form>
<a href="list.do">목록보기</a>
</body>
</html>