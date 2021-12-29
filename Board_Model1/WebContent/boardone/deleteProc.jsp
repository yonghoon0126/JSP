<%@page import="boardone.BoardDAO"%>
<%@ page import="java.sql.Timestamp" %>
<% request.setCharacterEncoding("utf-8"); %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	String pass = request.getParameter("pass");
	System.out.println("proc pass : " + pass);
	System.out.println("proc num : " + num);
	
	BoardDAO dbPro = BoardDAO.getInstance();
	int check = dbPro.deleteAricle(num, pass);
	System.out.println("check : " + check);
	
	if(check==1){
%>
<meta http-equiv="Refresh" content="0; url=list.jsp?pageNum=<%=pageNum%>">
<%}else{%>
	<script type="text/javascript">
	alert("비밀번호가 맞지 않습니다.");
	history.back();
	</script>
<%} %>

<title>Insert title here</title>
</head>




<body>

</body>
</html>