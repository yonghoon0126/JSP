guestbook_form.jsp<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" errorPage="error.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta  charset="utf-8">
<title>방명록:작성화면</title>
</head>
<body>
<center>
<h2>방명록:작성화면 </h2>
<hr>
[<a href="guestbook_control.jsp?action=list">게시물목록으로</a>]
<form name="form1" method="post" action="guestbook_control.jsp">
	<input type=hidden name="action" value="insert">
	<table cellpadding=5 cellspacing=0 border="1">
	  <tr>
	    <td bgcolor="yellow">작 성 자</td>	
	    <td><input type="text" name="gb_name" size="20"></td>
	  </tr>
	  <tr>
	    <td bgcolor="yellow">email</td>
	    <td><input type="text" name="gb_email" size="20"></td>
	  </tr>
	    <tr>
	    <td bgcolor="yellow">전화번호</td>
	    <td><input type="text" name="gb_tel" size="20"></td>
	  </tr>
	  <tr>
	    <td bgcolor="yellow">비밀번호</td>
	    <td><input type="password" name="gb_passwd" size="20"></td>
	  </tr>
	  <tr>
	    <td colspan="2"><textarea rows="5" name="gb_contents" cols="40"></textarea></td>
	  </tr>
	  <tr>
	    <td colspan=2 align=center><input type=submit value="저장"><input type=reset value="취소"></td>
	</tr>
	</table>
</form>

</center>
</body>
</html>