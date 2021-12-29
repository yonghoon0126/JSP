package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.db.BoardDAO;
import net.board.db.BoardBean;

public class BoardAddAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		BoardDAO boarddao=new BoardDAO();
	   	BoardBean boarddata=new BoardBean();
	   	ActionForward forward=new ActionForward();
	   	
		String realFolder="";//파일 업로드
   		String saveFolder="boardupload";
   		
   		int fileSize=5*1024*1024;//5megabyte
   		
     	//실제 물리적 하드에 저장 폴더
   		realFolder=request.getRealPath(saveFolder);
   		
   		boolean result=false;//BoardBean자료를 db(DAO)에 삽입 여부
   		
   		try{
   			//파일업로드 처리
   			MultipartRequest multi=null;
   			
   			multi=new MultipartRequest(request,
   					realFolder,
   					fileSize,
   					"euc-kr",
   					new DefaultFileRenamePolicy());
   			
   			boarddata.setBOARD_NAME(multi.getParameter("BOARD_NAME"));
   			boarddata.setBOARD_PASS(multi.getParameter("BOARD_PASS"));
	   		boarddata.setBOARD_SUBJECT(multi.getParameter("BOARD_SUBJECT"));
	   		boarddata.setBOARD_CONTENT(multi.getParameter("BOARD_CONTENT"));
	   		boarddata.setBOARD_FILE(
	   				multi.getFilesystemName((String)multi.getFileNames().nextElement()));
	   		
	   		result=boarddao.boardInsert(boarddata);//BoardBean에 저장된 데이타를 디비에 저장
	   		
	   		if(result==false){ //db에 저장 실패
	   			System.out.println("게시판 등록 실패");
	   			return null;
	   		}
	   		
	   		System.out.println("게시판 등록 완료");
	   		
	   		forward.setRedirect(true);//redirect방식
	   		forward.setPath("./BoardList.bo");
	   		return forward;
	   		
  		}catch(Exception ex){
   			ex.printStackTrace();
   		}
  		return null;
	}  	
}