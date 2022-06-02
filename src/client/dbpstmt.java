package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dbpstmt {

	

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	static final String DB_URL = "jdbc:mysql://seongminwoo.cllmkmibqzo5.ap-northeast-2.rds.amazonaws.com:3306/SeongMinWoo";



	static final String USERNAME = "minwoo";

	static final String PASSWORD = "minwoo9740";



	public static void main(String[] args) throws SQLException {

		Connection conn = null;

		PreparedStatement pstmt = null;

		

		

		try {

			String sql = "INSERT INTO test VALUES (?,?)";

			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1,3);

			pstmt.setString(2, "LEE");

			

			pstmt.executeUpdate();

		}catch(SQLException e) {

			e.printStackTrace();

		}catch(Exception e) {

			e.printStackTrace();

		}finally {

			pstmt.close();

			conn.close();

		}

	}



}