package boardone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

	private static BoardDAO instance=null;
	
	public BoardDAO() {	}

	public static BoardDAO getInstance() {
		
		if(instance==null) {
			synchronized (BoardDAO.class) {
				instance = new BoardDAO();
			}
		}
		return instance;
	};
	
	// 1. 삽입
	@SuppressWarnings("resource")
	public void insertArticle(BoardVO article) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = article.getNum();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		int number = 0;
		String sql = "";
		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select max(num) from board1");
			rs = pstmt.executeQuery();
			if(rs.next())
				number = rs.getInt(1) + 1;
			else
				number = 1;
			
			if(num != 0) {	// 답변글 일 경우
				sql = "update board1 set step=step+1 " + "where ref=? and step > ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				pstmt.executeUpdate();
				step = step + 1;
				depth = depth + 1;
			
			}else {		// 새 글일 경우
				ref = number;
				step = 0;
				depth = 0;
			}
				
			// 저장
			sql="insert into board1(num,writer,"+
					"email,subject,pass,regdate,ref,step,"+
					"depth,content,ip) values(board1_seq.nextval,"	+
					"?,?,?,?,?,?,?,?,?,?)";//10개
				
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, article.getWriter());
					pstmt.setString(2, article.getEmail());
					pstmt.setString(3, article.getSubject());
					pstmt.setString(4, article.getPass());
					pstmt.setTimestamp(5, article.getRegdate());
					pstmt.setInt(6, ref);
					pstmt.setInt(7, step);
					pstmt.setInt(8, depth);
					pstmt.setString(9, article.getContent());
					pstmt.setString(10, article.getIp());
					pstmt.executeUpdate();
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnUtil.close(conn, pstmt, rs);
		}
		
		
	}	// insertArticle 삽입
	
	// 2. 전체 글의 개수를 가져오는 메소드
	public int getArticleCount() {
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from board1");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			ConnUtil.close(conn, pstmt, rs);
		}
		
		return x;
	}	// getArticleCount() 전체글
	
	// 3. 게시글 시작번호, 끝 번호 가져오기, 페이징 처리
	public List<BoardVO> getArticles(int start, int end){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> articleList = null;
		System.out.println("start = " + start + ", end = " + end);
		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement
						("select * from (select rownum rnum,num,writer,email,"
						+ "subject,pass,regdate,readcount,ref,step,depth,content,"
						+ "ip from(select * from board1 order "+"by ref desc, step asc))"
						+ " where rnum>=? and rnum<=?");
					
			pstmt.setInt(1,  start);
			pstmt.setInt(2,  end);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				articleList = new ArrayList<BoardVO>(end-start+1);
				
				do {
					BoardVO article = new BoardVO();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					
					article.setSubject(rs.getString("subject"));
					article.setPass(rs.getString("pass"));
					article.setReadcount(rs.getInt("readcount"));
					
					article.setRef(rs.getInt("ref"));
					article.setStep(rs.getInt("step"));
					article.setDepth(rs.getInt("depth"));

					article.setRegdate(rs.getTimestamp("regdate"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
					articleList.add(article);
				}while(rs.next());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnUtil.close(conn, pstmt, rs);
		}
		return articleList;
	}	// getArticles()
	
	// 4. 글 하나의 정보를 가져오기 / 조회수 수정
	@SuppressWarnings("resource")
	public BoardVO getArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO article = null;
		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("update board1 set readcount = readcount+1 where num=?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("select * from board1 where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article = new BoardVO();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPass(rs.getNString("pass"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
			}	// end if
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnUtil.close(conn, pstmt, rs);
		}
		return article;
		
	}	// getAtricle() 글 하나의 정보
	
	// 5. 조회수를 증가시키는 부분을 제외하고 가져오는 메소드 / 글 수정 화면 만들기 / 글 수정시에는 조회수 증가 필요없다.
	public BoardVO updateGetArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO article = null;
		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from board1 where num =?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article = new BoardVO();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("eamil"));
				article.setSubject(rs.getString("subject"));
				article.setPass(rs.getString("pass"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
			}	// if
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnUtil.close(conn, pstmt, rs);
		}
		return article;
	}	// updateGetArticle()
	
	// 6. updateForm.jsp에서 비밀번호를 입력했을 때 / 글 수정 처리하기
	@SuppressWarnings("resource")
	public int updateArticle(BoardVO article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd = "";
		String sql = "";
		int result = -1;
		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select pass from board1 where num=?");
			pstmt.setInt(1, article.getNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbpasswd = rs.getString("pass");
				
				if(dbpasswd.equals(article.getPass())) {
					sql = "update board1 set writer=?,email=?,subject=?";
					sql += ",content=? where num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, article.getWriter());
					pstmt.setString(2, article.getWriter());
					pstmt.setString(3, article.getWriter());
					pstmt.setString(4, article.getWriter());
					pstmt.setInt(5, article.getNum());
					pstmt.executeUpdate();
					result = 1;		// 성공(비밀번호 일치)
				}else {
					result = 0;		// 실패(비밀번호 불일치)
				}
			}	// if end
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnUtil.close(conn, pstmt, rs);
		}	// try
		return result;
	}	// updateArticle()
	
	// 7. 글 삭제 처리하기
	@SuppressWarnings("resource")
	public int deleteAricle(int num, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd = "";
		int result = -1;
		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select pass from board1 where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbpasswd = rs.getString("pass");
				System.out.println("dbpasswd : " + dbpasswd);
				System.out.println("pass : " + pass);
				
				if(dbpasswd.equals(pass)) {
					pstmt = conn.prepareStatement("delete from board1 where num=?");
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					result = 1;		// 글 삭제 성공
				}else result = 0;	// 비밀번호 틀림
			}	// if end
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnUtil.close(conn, pstmt, rs);
		}	// try
		return result;
	}	// deleteArticle()
	
}	// class
