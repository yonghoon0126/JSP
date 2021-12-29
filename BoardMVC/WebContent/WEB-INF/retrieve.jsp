<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>글 자세히 보기</title>
</head>
<body>
<h1>글 자세히 보기</h1>
<form action="update.do" method="post">
	<input type="hidden" name="num"   value="${retrieve.num}">
	글번호${retrieve.num}&nbsp;&nbsp;&nbsp;&nbsp; 조회수${retrieve.readcnt}<br>
	
	제목<input type="text"  name="title"  value="${retrieve.title}"><br>
	작성자<input type="text" name="author" value="${retrieve.author}"><br>
	내용<textarea name="content" rows="10">${retrieve.content}</textarea><br>
	<input type="submit"   value="수정">
</form>

<a href="list.do">목록</a>
<a href="delete.do?num=${retrieve.num}">삭제</a>
<a href="replyui.do?num=${retrieve.num}">답변달기</a>

</body>
</html>