package com.entity;

public class BoardDTO {
	private int num;       
	private String author ;   
	private String title ;    
	private String content ;  
	private String  writeday;  //µðºñ¿¡ DATE
	private int  readCnt ;  
	private int  repRoot   ;
	private int  repStep   ;
	private int  repIndent ;
	
	public BoardDTO() {	}

	public BoardDTO(int num, String author, String title, 
			String content, String writeday, int readCnt, 
			int repRoot,int repStep, int repIndent) {
		this.num = num;
		this.author = author;
		this.title = title;
		this.content = content;
		this.writeday = writeday;
		this.readCnt = readCnt;
		this.repRoot = repRoot;
		this.repStep = repStep;
		this.repIndent = repIndent;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriteday() {
		return writeday;
	}

	public void setWriteday(String writeday) {
		this.writeday = writeday;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	public int getRepRoot() {
		return repRoot;
	}

	public void setRepRoot(int repRoot) {
		this.repRoot = repRoot;
	}

	public int getRepStep() {
		return repStep;
	}

	public void setRepStep(int repStep) {
		this.repStep = repStep;
	}

	public int getRepIndent() {
		return repIndent;
	}

	public void setRepIndent(int repIndent) {
		this.repIndent = repIndent;
	}

	@Override
	public String toString() {
		return "BoardDTO [num=" + num + ", author=" + author + ", title=" + title + ", "
				+ "content=" + content	+ ", writeday=" + writeday + ", readCnt=" + readCnt 
				+ ", repRoot=" + repRoot + ", repStep=" + repStep	+ ", repIndent=" + repIndent + "]";
	}
	
	
}
