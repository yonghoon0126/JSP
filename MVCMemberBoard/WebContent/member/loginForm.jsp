<%@ page language="java" contentType="text/html; charset=EUC-KR"%>

<html>
<head>
<title>로그인 페이지</title>
</head>
<body>
<form name="loginform" action="./MemberLoginAction.me" method="post">
<center>
<table border=1>
	<tr>
		<td colspan="2" align=center>
			<b><font size=5>로그인 페이지</font></b>
		</td>
	</tr>
	<tr><td>아이디 : </td><td><input type="text" name="MEMBER_ID"/></td></tr>
	<tr><td>비밀번호 : </td><td><input type="password" name="MEMBER_PW"/></td></tr>
	<tr>
		<td colspan="2" align=center>
			<a href="javascript:loginform.submit()">로그인</a>&nbsp;&nbsp;
			<a href="MemberJoin.me">회원가입</a>
		</td>
	</tr>
</table>
</center>
</form>
</body>
</html>