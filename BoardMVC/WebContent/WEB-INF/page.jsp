<%@page import="com.entity.PageTO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<%
	PageTO to= (PageTO)request.getAttribute("page");
	int curPage=to.getCurPage(); //현재 페이지 번호   
	int perPage=to.getPerPage(); //페이지당 보여줄 레코드 개수
	
	int totalCount=to.getTotalCount();//총레코드 수
	int totalPage=totalCount/perPage; // 총 페이지수
	
	System.out.println("curPage="+curPage);
	System.out.println("perPage="+perPage);
	System.out.println("totalCount="+totalCount);
	System.out.println("totalPage="+totalPage);
	
	if(totalCount % perPage != 0) totalPage++;//페이지 하나씩 증가
	for(int i=1 ; i <= totalPage;i++){
		if( curPage == i){
			out.print("<font size=10 color='red'>" + i +"</font>");
		}else{
			out.print("<a href='list.do?curPage=" + i + "'>" + i +"</a>&nbsp;");
		}
	}
%>
</body>
</html>