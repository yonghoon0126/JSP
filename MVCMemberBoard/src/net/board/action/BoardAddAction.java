package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.db.BoardDAO;
import net.board.db.BoardBean;

public class BoardAddAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 throws Exception{
		BoardDAO boarddao=new BoardDAO();
	   	BoardBean boarddata=new BoardBean();
	   	ActionForward forward=new ActionForward();
	   	
		String realFolder="";//파일 업로드
   		String saveFolder="boardupload";//폴더명
   		
   		int fileSize=5*1024*1024;
   		
   		//workspace\.metadata\.plugins\
   		//org.eclipse.wst.server.core\tmp0\wtpwebapps\Ex0909
   		realFolder=request.getRealPath(saveFolder);
   		
   		boolean result=false;//DAO에 Insert한 결과저장변수
   		
   		try{
   			MultipartRequest multi=null;//여러유형에 자료처리
   			
   			multi=new MultipartRequest(request,
   					realFolder,//실제 저장될 폴더
   					fileSize,//파일사이즈
   					"euc-kr",//파일명 인코딩타입
   					new DefaultFileRenamePolicy());//중복파일 있으면 새이름으로 변경
   			//폼에서 입력한 자료를 실제 Bean 객체에 설정
   			boarddata.setBOARD_ID(multi.getParameter("BOARD_ID"));
	   		boarddata.setBOARD_SUBJECT(multi.getParameter("BOARD_SUBJECT"));
	   		boarddata.setBOARD_CONTENT(multi.getParameter("BOARD_CONTENT"));
	   		boarddata.setBOARD_FILE(
	   				multi.getFilesystemName(
	   						(String)multi.getFileNames().nextElement()));
	   		System.out.println("파일들= "+(String)multi.getFileNames().nextElement());
	   		System.out.println("파일= "+multi.getFilesystemName(
						(String)multi.getFileNames().nextElement()));
	   		//DAO에 Insert한 결과가 성공이면 true 실패면 false return 받아 result에 저장 
	   		result=boarddao.boardInsert(boarddata);
	   		
	   		if(result==false){
	   			System.out.println("게시판 등록 실패");
	   			return null;
	   		}
	   		//DAO에서 Insert한 결과가 성공이면 
	   		//1. redirect 방식으로 처리 하려면 setRedirect(true)로 설정
	   		//setRedirect(false): Dispacher방식으로 설정
	   		//2. 등록이 완료되면 게시판 목록 보여주기 패스 설정
	   		//1,2를 설정해서 
	   		System.out.println("게시판 등록 완료");
	   		
	   		forward.setRedirect(true);
	   		forward.setPath("./BoardList.bo");
	   		return forward;
	   		
  		}catch(Exception ex){
   			ex.printStackTrace();
   		}
   		return null;//ActionForward 타입으로 Controll에 돌아감
	}//ActionForward
}