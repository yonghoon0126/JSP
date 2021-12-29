package com.entity;

import java.util.ArrayList;

public class PageTO {
	 ArrayList<BoardDTO> list;//목록 리스트 저장
	 int curPage;			  //현재 페이지 번호
	 int perPage=5;			  //페이지당 보여줄 레코드 개수
	 int totalCount;		  //전체 레코드 개수 
	public ArrayList<BoardDTO> getList() {
		return list;
	}
	public void setList(ArrayList<BoardDTO> list) {
		this.list = list;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	@Override
	public String toString() {
		return "PageTO [list=" + list + ", curPage=" + curPage 
				+ ", perPage=" + perPage + ", totalCount=" 
				+ totalCount + "]";
	}
	 
}
