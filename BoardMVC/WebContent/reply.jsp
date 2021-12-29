<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답변 글 쓰기 화면</title>
</head>



<body>

<h1>답변 글 쓰기 화면</h1>

<form action="reply.do" method="post">
	<input type="hidden" name="num" value="${replyui.num}">
	<input type="hidden" name="repRoot" value="${replyui.repRoot}">
	<input type="hidden" name="repStep" value="${replyui.repStep}">
	<input type="hidden" name="repIndent" value="${replyui.repIndent}">
	
	원래 글 번호 ${replyui.num} 조회수 ${replyui.readCnt}<br>
	제목<input type="text" name="title" value="${replyui.title}"><br>
	작성자<input type="text" name="author"><br>
	내용<textarea name="content" rows="10">${replyui.content}</textarea><br>

	<input type="submit" value="답변달기"><br>

</form>

	<a href="list.do">목록보기</a>

</body>
</html>