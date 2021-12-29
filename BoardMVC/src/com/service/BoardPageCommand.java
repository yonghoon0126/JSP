package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.entity.PageTO;

public class BoardPageCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest request, 
			HttpServletResponse response) {
		int curPage=1;//���� ������
		if(request.getParameter("curPage") != null) {
			curPage=Integer.parseInt(request.getParameter("curPage"));
		}
		BoardDAO dao=new BoardDAO();
		PageTO list=dao.page(curPage);

		System.out.println("����� BoardPageCommand...."+curPage+"\n"+
		list.getList());
		
		request.setAttribute("list", list.getList());
		request.setAttribute("page", list);
	}
}
