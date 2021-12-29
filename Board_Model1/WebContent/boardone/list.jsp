<%@page import="java.text.SimpleDateFormat"%>
<%@page import="boardone.BoardVO"%>
<%@page import="boardone.BoardDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="view/color.jsp" %>

<%
	int pageSize=5;		// 한 페이지에 게시물 수
%>

<%!
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>

<% String pageNum=request.getParameter("pageNum");
	if(pageNum == null){
		pageNum="1";
	}
	int currentPage=Integer.parseInt(pageNum);
	int startRow=(currentPage-1) * pageSize + 1;	// 시작행
	int endRow=currentPage * pageSize;	// 끝행
	int count = 0;	// 전체 게시글 수
	int number = 0;	// 나머지 페이지 수
	List<BoardVO> articleList=null;
	BoardDAO dbpro = BoardDAO.getInstance();
	
	count=dbpro.getArticleCount();	// 전체 글 수
	
	if(count>0){
		articleList=dbpro.getArticles(startRow, endRow);
		// 35-(1-1)*10;
	}
	number = count - (currentPage -1) * pageSize;	// 문 번호
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="style.css" rel="stylesheet" type="text/css"/>
</head>



<body bgcolor="<%=bodyback_c%>">

<center>
	<b>글 목록(전체 글 : <%=count%>) number=<%=number%></b>

	<table width="700">
		<tr>
			<td align="right" bgcolor="<%=value_c%>">
				<a href="writeForm.jsp">글 쓰기</a>
			</td>
		</tr>
	</table>
<%
	if(count == 0){
	%>	<table width="700" border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center">게시판에 저장된 글이 없습니다</td>
			</tr>
		</table>
<%
}else{
%>
	<table width="700" border="1" cellpadding="0" cellspacing="0">
		<tr height="30" bgcolor="<%=value_c%>">
			<td align="center" width="50">번호</td>
			<td align="center" width="250">제 목</td>
			<td align="center" width="100">작성자</td>
			<td align="center" width="150">작성일</td>
			<td align="center" width="50">조 회</td>
			<td align="center" width="100">IP</td>
		</tr>
<%
	for(int i = 0; i < articleList.size(); i++){
		BoardVO article = (BoardVO) articleList.get(i);
%>
	<tr height="30">
		<td align="center" width="50"><%=number-- %></td>
		<td width="250">
<%
	int wid = 0;
		if(article.getDepth()>0){
			wid = 5 * (article.getDepth());
%>
	<img src="images/level.gif" width="<%=wid%>" height="16">
	<img src="images/re.gif">
<%}else{ %>
	<img src="images/level.gif" width="<%=wid%>" height="16">
<%} // if end %>

	<a href="content.jsp?num=<%=article.getNum()%>&pageNum=<%=currentPage%>">
<%=article.getSubject()%></a>

<%
	if(article.getReadcount() >= 5){	// 조회수 20이상일 때
%>
	<img src="images/hot.gif" border="0" height="16">
<%
}	// if end
%>
		</td>
		
		<td align="center" width="100">
			<a href="mailto:<%=article.getEmail()%>"> <%=article.getWriter()%></a></td>
		<td align="center" width="150"><%=sdf.format(article.getRegdate()) %></td>
		<td align="center" width="50"><%=article.getReadcount()%></td>
		<td align="center" width="100"><%=article.getIp()%></td>
	</tr>
<%
	}	// for end 문
%>
</table>
<%
}	// if(cont == 0){} if문
%>			
<%
	if(count > 0){
		int pageBlock = 2;	// 페이지 묶음
		
		int imsi = count % pageSize == 0 ? 0 : 1;
		// 1 = 15 % 10
				
		int pageCount = count / pageSize + imsi;
		// 2 = 15 / 10 + 1
				
		int startPage = (int) ((currentPage -1) / pageBlock) * pageBlock + 1;
		// 1 = ((1 - 1) / 5) * 5 + 1
		
		int endPage = startPage + pageBlock - 1;
		// 5 = 1 + 5 = 1

	if(endPage > pageCount)
		endPage = pageCount;
	
	if(startPage > pageBlock){
%>
	<a href="list.jsp?pageNum=<%=startPage - pageBlock%>">[이전]</a>
<%
}//if startPage

	for (int i = startPage; i <= endPage; i++) {
%>
	<a href="list.jsp?pageNum=<%=i %>">[<%=i%>]</a>
​<%
}//for 끝

	if (endPage < pageCount) {
%>
	<a href="list.jsp?pageNum=<%=startPage + pageBlock%>">[다음]</a>
<%
	}//if endPage end
}//if count문 end
%>
</center>

</body>
</html>