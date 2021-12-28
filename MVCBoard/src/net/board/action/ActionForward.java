package net.board.action;

public class ActionForward {
	
	//action 인터페이스에서 명령을 수행하고 결과 값을 갖고 포워딩하려고
	private boolean isRedirect=false; //redirect여부
	private String path=null; // 포워딩할 위치
	
	public boolean isRedirect(){
		return isRedirect;
	}
	
	public String getPath(){
		return path;
	}
	
	public void setRedirect(boolean b){
		isRedirect=b;
	}
	
	public void setPath(String string){
		path=string;
	}
}