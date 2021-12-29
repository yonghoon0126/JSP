package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {

	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			
			// jdbc/myoracle�̶� �̸��� ��ü�� ã�Ƽ� DataSource�� �޴´�.
			DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
			
			// ds�� �����Ǿ����Ƿ� Connection�� ���մϴ�.
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// select�� ������ �� ���ҽ� ������ ���� �޼ҵ�
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
	
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// DML(insert, update, delete)�� ������ �� ���ҽ� ������ ���� �޼ҵ�
	public static void close(Connection conn, Statement stmt) {
		
		try {
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}