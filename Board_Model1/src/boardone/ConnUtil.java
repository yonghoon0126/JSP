package boardone;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnUtil {

	private static DataSource ds;
	
	static {
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	// static
	
	public static Connection getConnection() throws SQLException{
		
		return ds.getConnection();
	}	// Connection
	
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		
		try {
			conn.close();
			stmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	// close 3°³

	public static void close(Connection conn, Statement stmt) {
		
		try {
			conn.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	// close 2°³
	
}
