<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="boardone.*"%>
<%@ page import="java.sql.*"%>
<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="article" scope="page" class="boardone.BoardVO">
<jsp:setProperty name="article" property="*" />
</jsp:useBean>

<% 
	String pageNum = request.getParameter("pageNum");
	BoardDAO dbPro = BoardDAO.getInstance();
	int check = dbPro.updateArticle(article);
	if(check==1){
%>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Refresh" content="0; url=list.jsp?pageNum=<%=pageNum%>">
<%}else{ %>
<script type="text/javascript">
alert('비밀번호가 맞지 않습니다.');
history.back();
</script>
<%}%>

<title>Insert title here</title>
</head>



<body>

</body>
</html>