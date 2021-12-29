package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.BoardCommand;
import com.service.BoardDeleteCommand;
import com.service.BoardListCommand;
import com.service.BoardPageCommand;
import com.service.BoardReplyCommand;
import com.service.BoardReplyUICommand;
import com.service.BoardRetrieveCommand;
import com.service.BoardSearchCommand;
import com.service.BoardUpdateCommand;
import com.service.BoardWriteCommand;

@WebServlet("*.do")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("����� doGet...");
		doPost(request, response);
	}

	//http://localhost:8081/BoardMVC/list.do
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println("����� doPost...");
	
		//  /BoardMVC/list.do
		String requestURI = request.getRequestURI();
		//  /BoardMVC-->9
		String contextPath = request.getContextPath();  
	    //  /list.do
		String com = requestURI.substring(contextPath.length()); 
		System.out.println("requestURI= " + requestURI);
		System.out.println("contextPath= "+contextPath+" , "+ 
		contextPath.length());
		
		System.out.println("com= " + com);
		BoardCommand command  = null;
		String nextPage = null;
		
		// ��Ϻ���
//		if(com.equals("/list.do")){
//			command = new BoardListCommand();
//			command.execute(request, response);
//			nextPage = "list.jsp";	//�۾��� Ŭ���� --> /writeui.do�� ����
//		}
		
		//�۾��� ��
		if(com.equals("/writeui.do")){
			nextPage="write.jsp";	//�����ư Ŭ����-->write.do�� ����
		}
		
		//�۾��� 
		if(com.equals("/write.do")){
			System.out.println("/write.do....");
			command=new BoardWriteCommand();
			command.execute(request, response);
			nextPage="list.do";
		}
		
		//�� �ڼ��� ����
		if(com.equals("/retrieve.do")){
			command=new BoardRetrieveCommand();
			command.execute(request, response);
			//���, ����/delete.do?num=1, �亯�ޱ�/replyui.do?num=1
			nextPage="retrieve.jsp";
		}
		
		//�� �����ϱ�
		if(com.equals("/update.do")) {
			command=new BoardUpdateCommand();
			command.execute(request, response);
			nextPage="list.do";
		}
		
		//�� �����ϱ�
		if(com.equals("/delete.do")) {
			command=new BoardDeleteCommand();
			command.execute(request, response);
			nextPage="list.do";
		}
		
		//�� �˻� �ϱ�
		if(com.equals("/search.do")) {//list���� ã�� �����
			command=new BoardSearchCommand();
			command.execute(request, response);
			nextPage="list.jsp";
		}
		
		//�亯�� �Է� �� ����
		if(com.equals("/replyui.do")) {
			command=new BoardReplyUICommand();
			command.execute(request, response);
			nextPage="reply.jsp";//�亯�ޱ� Ŭ����-->/reply.do
		}
		
		//�亯�� ����
		if(com.equals("/reply.do")) {
			command=new BoardReplyCommand();
			command.execute(request, response);
			nextPage="list.do";
		}
		
		//����¡ ó��
		if (com.equals("/list.do")) {
			command = new BoardPageCommand();
			command.execute(request, response);
			nextPage = "listPage.jsp";
		}
		System.out.println(nextPage+"....");
		RequestDispatcher dis = 
				request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
	}//doPost()
}//class
