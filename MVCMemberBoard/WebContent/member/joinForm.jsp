<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<html>
<head>
<title>회원가입 페이지</title>
</head>
<body>
<form name="joinform" action="./MemberJoinAction.me" method="post">
<center>
<table border=1>
	<tr>
		<td colspan="2" align=center>
			<b><font size=5>회원가입 페이지</font></b>
		</td>
	</tr>
	<tr>
		<td>아이디 : </td>
		<td><input type="text" name="MEMBER_ID"/></td>
	</tr>
	<tr>
		<td>비밀번호 : </td>
		<td><input type="password" name="MEMBER_PW"/></td>
	</tr>
	<tr>
		<td>이름 : </td>
		<td><input type="text" name="MEMBER_NAME"/></td>
	</tr>
	<tr>
		<td>나이 : </td>
		<td><input type="text" name="MEMBER_AGE" maxlength=2 size=5/></td>
	</tr>
	<tr>
		<td>성별 : </td>
		<td>
			<input type="radio" name="MEMBER_GENDER" value="남" checked/>남자
			<input type="radio" name="MEMBER_GENDER" value="여"/>여자
		</td>
	</tr>
	<tr>
		<td>이메일 주소 : </td>
		<td><input type="text" name="MEMBER_EMAIL" size=30/></td>
	</tr>
	<tr>
		<td colspan="2" align=center>
			<a href="javascript:joinform.submit()">회원가입</a>&nbsp;&nbsp;
			<a href="javascript:joinform.reset()">다시작성</a>
		</td>
	</tr>
</table>
</center>
</form>
</body>
</html>