package com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*-Model ����:
 * Ŭ���̾�Ʈ�� ��û�� ���� ���� �۾��� ó���ϴ� 
 * Business Logic�����̴�.
 * Command������ �̿��Ͽ� ��û�� ���ȭ�ϰ�,
 * �����ͺ��̽� ������ ���� DAO����, DTO������ �����Ͽ� ����.
 * 
 * -Command : ���� �������� ����(�ൿ�� �ϳ��� �����)//������
 *   BoardCommand   :Command����, �������̽�
 * 
 * - DAO ����, DTO ���� Ŭ���� ���
 *   BoardDTO == VO : board ���̺��� ���ڵ带 �����ϱ� ���� ������ Ŭ����
 *   BoardDAO        : �����ͺ��̽� ���� Ŭ����
 *   PageTO          : �Խ��� ������ ó�� ���� ������ ���� Ŭ����
 *   
 * - View ����
 * Ŭ���̾�Ʈ ��û�� ���� ���� ó���� presentation logic�� ó���Ͽ� jsp�� ����
 * �� ���������� ȭ���� ����ϴ� JSp
 * */
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.entity.BoardDTO;
import com.entity.PageTO;

public class BoardDAO {
	DataSource dataFactory;//DriverManager�� ������ Ŭ����
	public BoardDAO() {
		try {
			Context ctx=new InitialContext();
			dataFactory=(DataSource)ctx.lookup
					("java:comp/env/jdbc/myoracle");
			
		}catch (Exception e) {
			e.getSuppressed();
		}
	}	// BoardDAO()
	
	//��� ����
	public ArrayList<BoardDTO> list() {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataFactory.getConnection();              //�ڹٺ󿡼� String Ÿ��
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
				String writeday = rs.getString("writeday");//��¥���� ����������
				int readCnt     = rs.getInt("readCnt");  
				int repRoot     = rs.getInt("repRoot");  
				int repStep     = rs.getInt("repStep");  
				int repIndent   = rs.getInt("repIndent");  
				
				//�ڹٺ� ���
				BoardDTO data= new BoardDTO();//DTO�� ����
				data.setNum(num);
				data.setAuthor(author);
				data.setTitle(title);;
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadCnt(readCnt);
				data.setRepRoot(repRoot);
				data.setRepStep(repStep);
				data.setRepIndent(repIndent);
				
				//����Ʈ �÷��ǿ� ���
				list.add(data);//���ڵ庰�� ��� ������ ���� ����Ʈ�� ����
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
	
	//�۾��� 
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
	
	//��ȸ�� 1����	
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
	
	//�� �ڼ��� ����
	public  BoardDTO retrieve( String  _num ){
		readCount(_num);//��ȸ�� ����
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
				String writeday = rs.getString("writeday");//��¥���� ����������
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
	
	//�� �����ϱ�
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
	
	//�� �����ϱ�	
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
	
	// �˻� �ϱ�	
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
	
// �亯�� �Է� �� ����	
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
	
	//�亯 �ޱ�	
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
	
	//�亯���� ���� repStep 1 ����
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
	
// ����¡ ó��: ��ü ���ڵ� ���� ���ϱ�
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
	
// ������ ����
	public PageTO  page( int curPage ){
		PageTO   to = new PageTO();
		int totalCount = totalCount();

		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
/*ResultSet�ɼ�
 * TYPE_FORWARD_ONLY : Ŀ���̵��� ���� ���ڵ�θ� �̵��ǵ��� �Ѵ�.
 * TYPE_SCROLL_SENSITIVE : Ŀ���̵��� �����Ӱ� �ϰ� ������Ʈ ������ �ݿ��Ѵ�.
 * TYPE_SCROLL_INSENSITIVE : Ŀ�� �̵��� �����Ӱ� �ϰ� ������Ʈ ������ �ݿ����� �ʴ´�.
 * CONCUR_UPDATABLE : ������ ������ �����ϵ��� �Ѵ�.
 * CONCUR_READ_ONLY : ������ ������ �Ұ����ϵ��� �Ѵ�.
 * */
			con = dataFactory.getConnection();
			String query = "SELECT  num, author, title,  content ,  "
				+ "to_char( writeday , 'YYYY/MM/DD') writeday , "
				+ " readCnt , repRoot, repStep, repIndent FROM board2"
				+ " order by repRoot desc , repStep asc";

			pstmt = con.prepareStatement(query, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY );//����� ����
			rs = pstmt.executeQuery(  );
			//PageTO���� perPage=5(�������� ������ ���ڵ� ����)
			int perPage = to.getPerPage(); 
			
			////(���� ������ ��ȣ  -1)*5
			int skip = ( curPage - 1 ) * perPage;
			//��ü���ڵ忡�� ��ŵ�ؾ� �� ������ �̿��� ���� ������.
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
			to.setList( list ); // ArrayList ����
			to.setTotalCount( totalCount ); // ��ü ���ڵ� ����
			to.setCurPage( curPage ); // ���� ������
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
