package com.entity;

import java.util.ArrayList;

public class PageTO {
	 ArrayList<BoardDTO> list;//��� ����Ʈ ����
	 int curPage;			  //���� ������ ��ȣ
	 int perPage=5;			  //�������� ������ ���ڵ� ����
	 int totalCount;		  //��ü ���ڵ� ���� 
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
