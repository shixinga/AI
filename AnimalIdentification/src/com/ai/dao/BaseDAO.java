package com.ai.dao;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class BaseDAO {
	private Connection conn;
	public ResultSet rs;
	public Statement stm;
	public PreparedStatement pstm;
	public Statement getStatement() throws ClassNotFoundException, SQLException, IOException, URISyntaxException {
		if (stm == null) {
			if (conn == null) {
				creatConnection();
			}
			stm = conn.createStatement();
		}
		return stm;
	}

	public PreparedStatement getPreparedStatement(String sql)
			throws SQLException, ClassNotFoundException, IOException, URISyntaxException {
		if (pstm == null) {
			if (conn == null) {
				creatConnection();
			}
		}else{
			pstm.close();
		}
		pstm = conn.prepareStatement(sql);
		return pstm;

	}

	public void closeConnection()  {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (stm != null) {
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void creatConnection() throws ClassNotFoundException, SQLException, IOException, URISyntaxException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/animal",
				"root", "");
	}

}
