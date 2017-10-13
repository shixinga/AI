package com.haha.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FeatureDao extends BaseDao {

	public static final String SQL_FEATURE = "select feature from features";
	public List getFeatures() {
		List<String> features = new ArrayList<>();
		try {
			mStatement = getStatement();
			mResultSet = mStatement.executeQuery(SQL_FEATURE);
			while(mResultSet.next()) {
				String feature = mResultSet.getString("feature");
				features.add(feature);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return features;
	}
}
