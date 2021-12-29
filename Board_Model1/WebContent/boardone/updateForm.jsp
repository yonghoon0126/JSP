<%@page import="boardone.BoardVO"%>
<%@page import="boardone.BoardDAO"%>
<%@ page import="boardone.*"%>
<%@ include file="view/color.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="script.js"></script>
</head>

<%
int num = Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");

try{
	BoardDAO dbPro = BoardDAO.getInstance();
	BoardVO article = dbPro.updateGetArticle(num);
%>

<body bgcolor="<%=bodyback_c%>">

<center>
<b>글 수정</b><%=num%>,<%=pageNum%><br>

<form method="post" name="writeform" action="updateProc.jsp?pageNum=<%=pageNum%>"
		onsubmit="return writeSave()">

<table width="400" border="1" cellspacion="0" cellpadding="0"
		bgcolor="<%=bodyback_c%>" align="center">
	<tr>
		<td width="70" bgcolor="<%=value_c%>" align="center">이름</td>
		<td align="left" width="330"><input type="text" size="10" maxlength="10" 
		name="writer" value="<%=article.getWriter()%>">
		<input type="hidden" name="num" value="<%=article.getNum()%>"></td>
	</tr>
	
	<tr>
		<td width="70" bgcolor="<%=value_c%>" align="center">제 목</td>
		<td align="left" width="330"><input type="text size="40" maxlength="50"
			name="subject" value="<%=article.getSubject()%>"></td>
	</tr>

	<tr>
		<td width="70" bgcolor="<%=value_c%>" align="center">Email</td>
		<td align="left" width="330">
		<input type="text" size="40" maxlength="30" name="email" value="<%=article.getEmail()%>"></td>
	</tr>
	
	<tr>
		<td width="70" bgcolor="<%=value_c%>" align="center">내 용></td>
		<td align="left" width="330">
		<textarea rows="13" cols="40" name="content"><%=article.getContent()%></textarea></td>
	</tr>
	
	<tr>
		<td width="70" bgcolor="<%=value_c%>" align="center">비밀번호</td>
		<td align="left" width="330">
		<input type="password" size="8" maxlength="12" name="pass"></td>
	</tr>
	
	<tr>
		<td colspan="2" bgcolor="<%=value_c%>" align="center">
			<input type="submit" value="글 수정">
			<input type="reset" value="다시 작성">
			<input type="button" value="목록 보기"
				onclick="document.location.href='list.jsp?pageNum=<%=pageNum%>'"></td>
		</tr>

</table>

</form>

</center>

<%}catch(Exception e){} %>

</body>
</html>