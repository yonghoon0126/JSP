<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"  errorPage="guestbook_error.jsp" import="guestbook.*"%>
<!DOCTYPE HTML >
<% request.setCharacterEncoding("utf-8"); %>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="gb" class="guestbook.GuestBean"/>
<jsp:useBean id="guestbook" class="guestbook.GuestBook"/>
<jsp:setProperty name="guestbook" property="*"/>
<% 
	// 사용자 요청을 구분하기 위한 파라미터를 저장할 변수
	String action = request.getParameter("action");
	
	// 게시물 목록 요청인 경우
	if(action.equals("list")) {
		response.sendRedirect("guestbook_list.jsp");
	}
	// 게시물 등록 요청인 경우
	else if(action.equals("insert")) {		
		if(gb.insertDB(guestbook)) {
			response.sendRedirect("guestbook_list.jsp");
		}
		else
			throw new Exception("DB 입력오류");
	}
	// 게시물 수정화면 요청인 경우
	else if(action.equals("edit")) {
		GuestBook gbook = gb.getDB(guestbook.getGb_id());
		if(!gbook.getGb_passwd().equals(request.getParameter("upasswd"))) {
			out.println("<script>alert('비밀번호가 틀렸습니다.!!');history.go(-1);</script>");
		}
		else {
			request.setAttribute("gbook",gbook);
			pageContext.forward("guestbook_edit_form.jsp");
		}
	}
	// 게시물 수정 처리인 경우
	else if(action.equals("update")) {
			if(gb.updateDB(guestbook)) {
				response.sendRedirect("guestbook_list.jsp");
			}
			else
				throw new Exception("DB 갱신오류");
	}
	// 게시물 삭체 요청인 경우
	else if(action.equals("delete")) {
		if(gb.deleteDB(guestbook.getGb_id())) {
			response.sendRedirect("guestbook_list.jsp");
		}
		else
			throw new Exception("DB 삭제 오류");
	}
	else {
		out.println("<script>alert('action 파라미터를 확인해 주세요!!!')</script>");
	}
%>

</body>
</html>