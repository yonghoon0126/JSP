<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" errorPage="guestbook_error.jsp" import="java.util.*, guestbook.*"%>
<!DOCTYPE HTML>
<html>
<head>
<script >
	function check(gb_id) {
		pwd = prompt('수정/삭제 하려면 비밀번호를 넣으세요');
		document.location.href="guestbook_control.jsp?action=edit&gb_id="+gb_id+"&upasswd="+pwd;
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>방명록:목록화면</title>
</head>

<%
	GuestBean gb = new GuestBean();
	ArrayList<GuestBook> datas = gb.getDBList();
%>
<body>
<center>
<h2>방명록:목록화면 </h2>
<hr>
<form>
<a href="guestbook_form.jsp">방명록 쓰기</a><p>

		<table cellpadding=5 cellspacing=0 border="1">
			<tr  bgcolor="yellow" ><td>번호</td><td>작성자</td><td>전화번호</td><td>작성일</td><td>내용</td></tr>
			<%
				// ArrayList 객체를 통해 DB에서 가져온 목록을 출력함.
				for(GuestBook  guestbook : datas) {
			%>
			  <tr>
			   <td><%=guestbook.getGb_id() %></td>
				<td><%=guestbook.getGb_name() %></td>
				<td><%=guestbook.getGb_tel() %></td>
				<td><%=guestbook.getGb_date() %></td>
				<td><a href=javascript:check('<%=guestbook.getGb_id() %>')><%=guestbook.getGb_contents() %></a></td>
			  </tr>
			 <%
				}
			 %>
		</table>
</form>
<hr>
</center>
</body>
</html>