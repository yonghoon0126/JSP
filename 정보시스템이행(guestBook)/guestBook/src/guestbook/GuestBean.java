package guestbook;

import java.sql.*;
import java.util.*;

// 클래스 선언
public class GuestBean {
	
	// 데이터베이스 연결관련 변수 선언
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	// 데이터베이스 연결관련정보를 문자열로 선언
	String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
	String jdbc_url = "jdbc:oracle:thin:@127.0.0.1:1521";
	
	// 데이터베이스 연결 메서드
	void connect() {
		// JDBC 드라이버 로드
		try {
			Class.forName(jdbc_driver);
			// 데이터베이스 연결정보를 이용해 Connection 인스턴스 확보
			conn = DriverManager.getConnection(jdbc_url,"hr","hr");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 데이터베이스 연결 종료 메서드
	void disconnect() {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 데이터 갱신을 위한 메서드
	public boolean updateDB(GuestBook guestbook) {
		connect();
		
		String sql ="update guestbook set gb_name=?, gb_email=?, gb_date=sysdate, gb_tel=?, gb_passwd=?, gb_contents=? where gb_id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			// 인자로 받은 GuestBook 객체를 이용해 사용자가 수정한 값을 가져와 SQL문 완성
			pstmt.setString(1,guestbook.getGb_name());
			pstmt.setString(2,guestbook.getGb_email());
			pstmt.setString(3,guestbook.getGb_tel());
			pstmt.setString(4,guestbook.getGb_passwd());
			pstmt.setString(5,guestbook.getGb_contents());
			pstmt.setInt(6,guestbook.getGb_id());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			disconnect();
		}
		return true;
	}
	
	// 게시물 삭제를 위한 메서드
	public boolean deleteDB(int gb_id) {
		connect();
		
		String sql ="delete from guestbook where gb_id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			// 인자로 받은 gb_id 프라이머리 키 값을 이용해 삭제
			pstmt.setInt(1,gb_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			disconnect();
		}
		return true;
	}
	
	// 게시물 등록 메서드
	public boolean insertDB(GuestBook guestbook) {
		connect();
		
		String sql ="insert into guestbook(gb_name,gb_email,gb_date,gb_tel,gb_passwd,gb_contents) values(?,?,sysdate,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			// 인자로 받은 GuestBook 객체를 통해 사용자 입력값을 받아 SQL 완성후 입력 처리
			pstmt.setString(1,guestbook.getGb_name());
			pstmt.setString(2,guestbook.getGb_email());
			pstmt.setString(3,guestbook.getGb_tel());
			pstmt.setString(4,guestbook.getGb_passwd());
			pstmt.setString(5,guestbook.getGb_contents());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			disconnect();
		}
		return true;
	}

	// 게시물 하나의 모든 정보를 가지고 오는 메서드
	public GuestBook getDB(int gb_id) {
		connect();
		
		String sql = "select * from guestbook where gb_id=?";
		GuestBook guestbook = new GuestBook();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,gb_id);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			// 가져와 데이터를 GuestBook 객체로 만들어 리턴함.
			guestbook.setGb_id(rs.getInt("gb_id"));
			guestbook.setGb_name(rs.getString("gb_name"));
			guestbook.setGb_email(rs.getString("gb_email"));
			guestbook.setGb_date(rs.getDate("gb_date"));
			guestbook.setGb_tel(rs.getString("gb_tel"));
			guestbook.setGb_contents(rs.getString("gb_contents"));
			guestbook.setGb_passwd(rs.getString("gb_passwd"));
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
		return guestbook;
	}
	
	// 게시물 목록 출력을 위해 전체 게시물을 가지고 오는 메서드
	public ArrayList getDBList() {
		connect();
		// 게시물 목록을 리턴하기 위한 ArrayList 객체 생성, 명시적으로 ArrayList 에 들어갈 데이터 타잎을 선언함.
		ArrayList<GuestBook> datas = new ArrayList<GuestBook>();
		
		String sql = "select * from guestbook";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			// 루프를 돌면 ResultSet 을 이동하면서 모든 데이터 row 를 하나씩 가지고 와서 GuestBook 객체에 넣고 이를 다시 ArrayList 에 넣는 작업을 반복.
			while(rs.next()) {
				GuestBook guestbook = new GuestBook();
				guestbook.setGb_id(rs.getInt("gb_id"));
				guestbook.setGb_name(rs.getString("gb_name"));
				guestbook.setGb_email(rs.getString("gb_email"));
				guestbook.setGb_date(rs.getDate("gb_date"));
				guestbook.setGb_tel(rs.getString("gb_tel"));
				guestbook.setGb_contents(rs.getString("gb_contents"));
				datas.add(guestbook);
			}
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
		// 처리가 끝나 ArrayList 를 리턴함.
		return datas;
	}
}