package com.haha.dao;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BaseDao {
	
	protected Connection mConnection;
	protected Statement mStatement;
	protected PreparedStatement mPreparedStatement;
	protected ResultSet mResultSet;
	
	
	private void creatConnection() throws ClassNotFoundException, SQLException, IOException, URISyntaxException {
		Class.forName("com.mysql.jdbc.Driver");
		mConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/animal?useUnicode=true&amp;characterEncoding=utf-8",
				"root", "");
	}
	
	public Statement getStatement() throws Exception {
		if (mConnection == null) {
			creatConnection();
		}
		
		if (mStatement == null) {
			mStatement = mConnection.createStatement();
		}
		return mStatement;
	}
	
	public PreparedStatement getPreparedStatement(String sql) throws Exception {
		if (mConnection == null) {
			creatConnection();
		}
		
		if (mPreparedStatement == null) {
			mPreparedStatement = mConnection.prepareStatement(sql);
		}
		return mPreparedStatement;
	}
	
	public void closeConnection()  {
		if (mResultSet != null) {
			try {
				mResultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				mResultSet = null;
				
			}
		}
		if (mStatement != null) {
			try {
				mStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				mStatement = null;
			}
		}
		if (mPreparedStatement != null) {
			try {
				mPreparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				mPreparedStatement = null;
			}
		}
		if (mConnection != null) {
			try {
				mConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				mConnection = null;
			}
		}
	}

}
