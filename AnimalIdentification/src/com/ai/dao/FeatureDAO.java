package com.ai.dao;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeatureDAO extends BaseDAO{
	public ArrayList<String> getFeatureList(){
		
		ArrayList<String> features =  new ArrayList<String>();
		String sql = "select feature from features";
		try {
			stm = getStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()){
				String feature = rs.getString("feature");
				features.add(feature);
			}
		} catch (SQLException | ClassNotFoundException | IOException | URISyntaxException e) {
			e.printStackTrace();
		}finally{
			this.closeConnection();
		}
		return features;
	}

}
