<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="view/color.jsp"%>
<%
int num = Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css" type="text/css">
<script type="text/javascript" src="script.js"></script>
</head>



<body bgcolor="<%=bodyback_c%>">

<center><b>글 삭제</b>
<br><br>

<form method="post" name="delForm" action="deleteProc.jsp?pageNum=<%=pageNum%>"
onsubmit="return deleteSave()">

	<table border="1" align="center" cellspacing="0" cellpadding="0" width="360">
		<tr height="30">
			<td align="center" bgcolor="<%=value_c%>">
			<b>비밀번호를 입력해 주세요.</b>
			</td>
		</tr>
		
		<tr height="30">
			<td align="center">비밀번호 : 
			<input type="password" name="pass">
			<input type="hidden" name="num" value="<%=num%>">
			</td>
		</tr>
		
		<tr height="30">	
			<td align="center" bgcolor="<%=value_c%>">
			<input type="submit" value="글 삭제">
			<input type="button" value="글 목록 
				onclick="document.location.href='list.jsp?pageNum=<%=pageNum%>'">
			</td>
		</tr>
	</table>
</form>

</center>

</body>
</html>