package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;
import net.board.db.BoardBean;

 public class BoardDetailAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		request.setCharacterEncoding("euc-kr");
   		
		BoardDAO boarddao=new BoardDAO();
	   	BoardBean boarddata=new BoardBean();
	   	
		int num=Integer.parseInt(request.getParameter("num"));//글번호
		
		boarddao.setReadCountUpdate(num);//조회수 증가
	   	boarddata=boarddao.getDetail(num);//글의 내용 BoardDAO에 내용을  Bean저장
	   	
	   	if(boarddata==null){//글의 내용이 없으면
	   		System.out.println("상세보기 실패");
	   		return null;
	   	}
	   	System.out.println("상세보기 성공");
	   	
	   	request.setAttribute("boarddata", boarddata);//BoardBean에 저장
	   	ActionForward forward = new ActionForward();
	   	forward.setRedirect(false);//dispatcher 호출
   		forward.setPath("./board/qna_board_view.jsp");
   		return forward;

	 }
}