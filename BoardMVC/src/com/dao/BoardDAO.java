package com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*-Model 설계:
 * 클라이언트의 요청에 대한 실제 작업을 처리하는 
 * Business Logic설계이다.
 * Command패턴을 이용하여 요청을 모듈화하고,
 * 데이터베이스 연동을 위한 DAO패턴, DTO패턴을 적용하여 설계.
 * 
 * -Command : 만능 리모콘의 역할(행동을 하나로 만들기)//다형성
 *   BoardCommand   :Command패턴, 인터페이스
 * 
 * - DAO 패턴, DTO 패턴 클래스 목록
 *   BoardDTO == VO : board 테이블의 레코드를 저장하기 위한 도메인 클래스
 *   BoardDAO        : 데이터베이스 관리 클래스
 *   PageTO          : 게시판 페이지 처리 관련 데이터 관리 클래스
 *   
 * - View 설계
 * 클라이언트 요청에 대한 응답 처리인 presentation logic을 처리하여 jsp로 구현
 * 웹 브라우저에서 화면을 담당하는 JSp
 * */
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.entity.BoardDTO;
import com.entity.PageTO;

public class BoardDAO {
	DataSource dataFactory;//DriverManager를 보완한 클래스
	public BoardDAO() {
		try {
			Context ctx=new InitialContext();
			dataFactory=(DataSource)ctx.lookup
					("java:comp/env/jdbc/myoracle");
			
		}catch (Exception e) {
			e.getSuppressed();
		}
	}	// BoardDAO()
	
	//목록 보기
	public ArrayList<BoardDTO> list() {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataFactory.getConnection();              //자바빈에서 String 타입
			String query="select num,author,title,content,"
					+ "to_char(writeday,'YYYY/MM/DD') writeday,"
					+ "readCnt, repRoot,repStep,repIndent FROM board2"
					+ " order by repRoot desc,repStep asc";
			pstmt=con.prepareStatement(query);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int num        = rs.getInt("num");        
				String author  = rs.getString("author");  
				String title   = rs.getString("title");  
				String content = rs.getString("content"); 
				String writeday = rs.getString("writeday");//날짜형을 문자형으로
				int readCnt     = rs.getInt("readCnt");  
				int repRoot     = rs.getInt("repRoot");  
				int repStep     = rs.getInt("repStep");  
				int repIndent   = rs.getInt("repIndent");  
				
				//자바빈에 담기
				BoardDTO data= new BoardDTO();//DTO에 저장
				data.setNum(num);
				data.setAuthor(author);
				data.setTitle(title);;
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadCnt(readCnt);
				data.setRepRoot(repRoot);
				data.setRepStep(repStep);
				data.setRepIndent(repIndent);
				
				//리스트 컬렉션에 담기
				list.add(data);//레코드별로 묶어서 보내기 위해 리스트에 저장
			}	//while
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}	//try end
		return list;
	}	//ArrayList<BoardDTO> list
	
	//글쓰기 
	public void write( String _title, String _author,
				String _content){
			Connection con = null;
			PreparedStatement pstmt = null;
			
		try{
		  con = dataFactory.getConnection();
		  String query ="INSERT INTO board2(num , title, author, "
		  	+ "content, repRoot , repStep , repIndent )"
		  	+ " values( board2_seq.nextval , ? , ? , ? , "
		  	+ "board2_seq.currval , 0 , 0 )";
		  pstmt = con.prepareStatement( query );

		  pstmt.setString( 1, _title );
		  pstmt.setString( 2, _author );
		  pstmt.setString( 3, _content );

		  int n = pstmt.executeUpdate();
		  System.out.println("write()...._title= "+_title);
		}catch(Exception e){ 
			e.printStackTrace();
		}finally{	
			try {
				if( pstmt!= null) pstmt.close();
				if( con!= null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	//end finally
    }	//end insert
	
	//조회수 1증가	
	public  void readCount( String  _num ){
		  Connection con = null;
		  PreparedStatement pstmt = null;
		  try {
			  con = dataFactory.getConnection();
			  String query = "UPDATE board2 SET readCnt = "
			  		+ "readCnt + 1 WHERE num="+ _num;
			  pstmt = con.prepareStatement( query );
			  int n = pstmt.executeUpdate( );
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if( pstmt!= null) pstmt.close();
					if( con!= null) con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	//end finally
	}	//readCount()
	
	//글 자세히 보기
	public  BoardDTO retrieve( String  _num ){
		readCount(_num);//조회수 증가
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		BoardDTO data=new BoardDTO();
	
		try {
			con=dataFactory.getConnection();
			String query="select * from board2 where num=?";
			
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(_num));
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int num        = rs.getInt("num");        
				String author  = rs.getString("author");  
				String title   = rs.getString("title");  
				String content = rs.getString("content"); 
				String writeday = rs.getString("writeday");//날짜형을 문자형으로
				int readCnt     = rs.getInt("readCnt");  
				
				data.setNum(num);
				data.setAuthor(author);
				data.setTitle(title);;
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadCnt(readCnt);
			}	//end if
		}catch( Exception e){ 
			e.printStackTrace();
		}finally{
			try {
				if( rs != null) rs.close();
				if( pstmt!= null) pstmt.close();
				if( con!= null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	//end try
		return data;
	}	//retrieve end	
	
	//글 수정하기
	public void update( String  _num ,      String  _title , 
			            String  _author ,	String  _content ){
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 
		try{
			con = dataFactory.getConnection();
			String query = "UPDATE board2 SET  title = ?, "
			+ " author = ? , content = ?  WHERE num = ?";

			pstmt = con.prepareStatement( query );
			pstmt.setString ( 1 , _title );
			pstmt.setString( 2,  _author );
			pstmt.setString( 3 ,  _content );
			pstmt.setInt(  4 ,   Integer.parseInt( _num ) );
			int n = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if( pstmt!= null) pstmt.close();
				if( con!= null) con.close();
				
			} catch (SQLException e) {	e.printStackTrace();	}
		}	//try 
	}	//end update
	
	//글 삭제하기	
	public void delete(String _num ){
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 try {
			con = dataFactory.getConnection();
				
			String query = "DELETE FROM board2 WHERE num = ?";
			pstmt = con.prepareStatement( query );
			pstmt.setInt(1 , Integer.parseInt( _num) );
				
		    int n =	pstmt.executeUpdate( );
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if( pstmt!= null) pstmt.close();
				if( con!= null) con.close();
			} catch (SQLException e) {	e.printStackTrace();	
			}
		}	//try
	}	//end delete
	
	// 검색 하기	
	public ArrayList<BoardDTO>  search( String _searchName,  
			                            String _searchValue ){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			 con = dataFactory.getConnection();
			 String query = "SELECT  num , author, title,  content ,  "
			 + "to_char( writeday , 'YYYY/MM/DD') writeday, "
			 + "readCnt FROM board2";
			 
			 if(  _searchName.equals( "title" )){
					query += " WHERE  title  LIKE ?";
			 }else{
					query += " WHERE  author LIKE ?";
			 }	//if
			 pstmt = con.prepareStatement( query );
			 pstmt.setString(  1,  "%"+_searchValue+"%" );
			 rs = pstmt.executeQuery( );
			 while( rs.next()){
					int num = rs.getInt( "num" );
					String author = rs.getString( "author" );
					String title = rs.getString( "title" );
					String content = rs.getString( "content" );
					String writeday = rs.getString( "writeday" );
					int readCnt = rs.getInt( "readCnt" );
					
					BoardDTO data = new BoardDTO();
					data.setNum( num );
					data.setAuthor( author );
					data.setTitle( title );
					data.setContent( content );
					data.setWriteday( writeday );
					data.setReadCnt( readCnt );
					list.add( data );
				 }	//end while
			 
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if( rs!= null) rs.close();
				if( pstmt!= null) pstmt.close();
				if( con!= null) con.close();
			} catch (SQLException e) {	e.printStackTrace();	}
		}	//try
		 return list;
	}	//search()
	
// 답변글 입력 폼 보기	
	public BoardDTO replyui(String  _num ){
		BoardDTO data = new BoardDTO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataFactory.getConnection();
			
			 String query = "SELECT * FROM board2 WHERE  num = ?";
			 pstmt = con.prepareStatement( query );
			 pstmt.setInt(1 , Integer.parseInt( _num) );
	         
			 rs  =  pstmt.executeQuery();
			 if( rs.next()){
					data.setNum(  rs.getInt( "num" ));
					data.setTitle( rs.getString( "title" ));
					data.setAuthor( rs.getString( "author" ));
					data.setContent( rs.getString( "content" ));
					data.setWriteday( rs.getString( "writeday" ));
					data.setReadCnt( rs.getInt( "readCnt" ));
					data.setRepRoot( rs.getInt( "repRoot" ));
					data.setRepStep( rs.getInt( "repStep" ));
					data.setRepIndent( rs.getInt( "repIndent" ));
			}	//end if
		} catch (Exception e) {
			 e.printStackTrace();
	 	 }finally{
			try {
				if( rs!= null) rs.close();
				if( pstmt!= null) pstmt.close();
				if( con!= null) con.close();
			} catch (SQLException e) {	e.printStackTrace(); }
		}	//try
		return data;
	}	//replyui()
	
	//답변 달기	
	public void reply(String _num,    String _title ,   String  _author, 
	                  String _content,String _repRoot, String _repStep , 
                      String _repIndent ){
		
		makeReply( _repRoot,  _repStep );
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataFactory.getConnection();
			String query = "INSERT INTO board2 ( num , title, author, content,"
			+ "repRoot, repStep, repIndent ) "
			+ " values ( board_seq.nextVal , ? , ? , ? , ?, ?, ?) ";
			pstmt = con.prepareStatement( query );
			
			pstmt.setString ( 1,  _title );
			pstmt.setString ( 2, _author );
			pstmt.setString ( 3, _content );
			pstmt.setInt ( 4, Integer.parseInt( _repRoot) );
			pstmt.setInt ( 5,  Integer.parseInt( _repStep) + 1 );
			pstmt.setInt ( 6,  Integer.parseInt( _repIndent) + 1 );
			int n = pstmt.executeUpdate();
		} catch (Exception e) {	e.printStackTrace();}	
		finally {
			try {
				if( pstmt!= null) pstmt.close();
				if( con!= null) con.close();
			} catch (SQLException e) {	e.printStackTrace(); }
		}	//try
	}	//end reply
	
	//답변글의 기존 repStep 1 증가
	public void makeReply( String _root , String _step ){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			 con = dataFactory.getConnection();
				
			 String query="UPDATE board2 SET repStep=repStep + 1 "
					    + "WHERE  repRoot = ? AND  repStep > ? ";
		     pstmt = con.prepareStatement( query );
		     pstmt.setInt(  1 ,   Integer.parseInt( _root ) );
		     pstmt.setInt(  2 ,   Integer.parseInt( _step ) );
			 int n= pstmt.executeUpdate( );
		}catch( Exception e){ 
				e.printStackTrace();
		}finally{
			try {
				if( pstmt!= null) pstmt.close();
				if( con!= null) con.close();		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	//end try
	}	//end makeReply
	
// 페이징 처리: 전체 레코드 갯수 구하기
	public int totalCount(){
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = dataFactory.getConnection();
			String query = "SELECT  count(*) FROM board2";
			pstmt = con.prepareStatement( query );
			rs =	pstmt.executeQuery( );
			if( rs.next()){	count = rs.getInt( 1 );		}
		}catch( Exception e){ 
			e.printStackTrace();
		}finally{
			try {
				if( rs!= null) rs.close();
				if( pstmt!= null) pstmt.close();
				if( con!= null) con.close();
			} catch (SQLException e) {	e.printStackTrace(); }
		}
		return count;
	}	//end totalCount		
	
// 페이지 구현
	public PageTO  page( int curPage ){
		PageTO   to = new PageTO();
		int totalCount = totalCount();

		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
/*ResultSet옵션
 * TYPE_FORWARD_ONLY : 커서이동을 다음 레코드로만 이동되도록 한다.
 * TYPE_SCROLL_SENSITIVE : 커시이동을 자유롭게 하고 업데이트 내용을 반영한다.
 * TYPE_SCROLL_INSENSITIVE : 커서 이동을 자유롭게 하고 업데이트 내용을 반영하지 않는다.
 * CONCUR_UPDATABLE : 데이터 변경이 가능하도록 한다.
 * CONCUR_READ_ONLY : 데이터 변경이 불가능하도록 한다.
 * */
			con = dataFactory.getConnection();
			String query = "SELECT  num, author, title,  content ,  "
				+ "to_char( writeday , 'YYYY/MM/DD') writeday , "
				+ " readCnt , repRoot, repStep, repIndent FROM board2"
				+ " order by repRoot desc , repStep asc";

			pstmt = con.prepareStatement(query, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY );//양방향 접근
			rs = pstmt.executeQuery(  );
			//PageTO에서 perPage=5(페이지당 보여줄 레코드 개수)
			int perPage = to.getPerPage(); 
			
			////(현재 페이지 번호  -1)*5
			int skip = ( curPage - 1 ) * perPage;
			//전체레코드에서 스킵해야 할 개수를 이용해 랜덤 접근함.
			if( skip > 0 ){
				rs.absolute( skip );
			}
			
			for( int i = 0 ; i < perPage &&  rs.next() ; i++){
				int num = rs.getInt( "num" );
				String author = rs.getString( "author" );
				String title = rs.getString( "title" );
				String content = rs.getString( "content" );
				String writeday = rs.getString( "writeday" );
				int readCnt = rs.getInt( "readCnt" );
				int repRoot = rs.getInt( "repRoot");
				int repStep = rs.getInt( "repStep" );
				int repIndent = rs.getInt( "repIndent" );
				BoardDTO data = new BoardDTO();
				data.setNum( num );
				data.setAuthor( author );
				data.setTitle( title );
				data.setContent( content );
				data.setWriteday( writeday );
				data.setReadCnt( readCnt );
				data.setRepRoot( repRoot);
				data.setRepStep( repStep );
				data.setRepIndent( repIndent );
				list.add( data );
			}	//end for
			to.setList( list ); // ArrayList 저장
			to.setTotalCount( totalCount ); // 전체 레코드 갯수
			to.setCurPage( curPage ); // 현재 페이지
		}catch( Exception e){ 
			e.printStackTrace();
		}finally{
			try {
				if( rs!= null) rs.close();
				if( pstmt!= null) pstmt.close();
				if( con!= null) con.close();
			} catch (SQLException e) {	e.printStackTrace();}
		}
		return to;		
	}	//end page
}
