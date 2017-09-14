package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource; // connection pooling
/**
 * dirver 로딩 
 * Connection 객체 반환
 * 자원 반환
 */
public class DBUtil {
	static DataSource ds;
	static {
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env"); // lookup : 시스템자원을 검색
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	// select
	public static void close(Connection con, Statement stmt, ResultSet rset) {
		try {
			if(rset != null) {
				rset.close();
				rset = null;
			}
			if(stmt != null) {
				stmt.close();
				stmt = null;
			}
			if(con != null) {
				con.close();
				con = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// insert/update/delete
	public static void close(Connection con, Statement stmt) {
		try {
			if(stmt != null) {
				stmt.close();
				stmt = null;
			}
			if(con != null) {
				con.close();
				con = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}