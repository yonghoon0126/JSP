package com.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.entity.BoardDTO;

public class BoardSearchCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String searchName=request.getParameter("searchName");
		String searchValue=request.getParameter("searchValue");
		
		BoardDAO dao=new BoardDAO();
		//글 검색 하기
		ArrayList<BoardDTO> list=
				dao.search(searchName, searchValue);
		
		request.setAttribute("list", list);
	}
}
