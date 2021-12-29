package net.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/BoardFrontController.bo")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
		 	throws ServletException, IOException {
			 String RequestURI=request.getRequestURI();//*.bo 모든 요청을 받는다.
			 String contextPath=request.getContextPath();//프로젝트 주소
			 String command=RequestURI.substring(contextPath.length());//프로젝트명  뒤부터 길이 구함.(*.bo)
			 ActionForward forward=null; //포워딩될 뷰 url, 포워딩 방식  설정
			 Action action=null; //인터페이스 Ex0905/BoardList.bo
			 System.out.println("처음"+command);
			if(command.equals("/BoardWrite.bo")){
				forward=new ActionForward();
				
				//false이면 dispatch방식,true이면 redirect방식으로 뷰페이지url로 포워딩 된다.
				forward.setRedirect(false);
				forward.setPath("./board/qna_board_write.jsp");
			}else if(command.equals("/BoardReplyAction.bo")){
				action = new BoardReplyAction();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			}else if(command.equals("/BoardDelete.bo")){
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setPath("./board/qna_board_delete.jsp");
			}else if(command.equals("/BoardModify.bo")){
				action = new BoardModifyView();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
		 	}else if(command.equals("/BoardAddAction.bo")){
				action  = new BoardAddAction();
				try {
					forward=action.execute(request, response );
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/BoardReplyView.bo")){
				action = new BoardReplyView();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			}else if(command.equals("/BoardModifyAction.bo")){
				action = new BoardModifyAction();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			}else if(command.equals("/BoardDeleteAction.bo")){
				action = new BoardDeleteAction();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			}else if(command.equals("/BoardList.bo")){
				action = new BoardListAction();
				System.out.println(action+"BoardList.bo호출");
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			}else if(command.equals("/BoardDetailAction.bo")){
				action = new BoardDetailAction();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			System.out.println("위에 결과"+action);
			if(forward != null){
				if(forward.isRedirect()){
					System.out.println("위에 결과 redirct"+forward);
					response.sendRedirect(forward.getPath());
				}else{
					System.out.println("위에 결과dis "+forward);
					RequestDispatcher dispatcher=
						request.getRequestDispatcher(forward.getPath());
					dispatcher.forward(request, response);
				}
			}
		 }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

}
