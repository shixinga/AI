package com.haha.test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.haha.dao.BaseDao;
import com.haha.dao.FeatureDao;
import com.haha.dao.RuleDao;
import com.haha.model.Rule;
import com.sun.org.apache.xalan.internal.utils.FeatureManager.Feature;

public class Test {

	@org.junit.Test
	public void testBaseDao() {
		BaseDao dao = new BaseDao();
		try {
			Statement statement = dao.getStatement();
			ResultSet rs = statement.executeQuery("select feature from features");
			while(rs.next()) {
				String feature = rs.getString("feature");
				System.out.println(feature);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@org.junit.Test
	public void testRuleDao() throws Exception {
		RuleDao ruleDao = new RuleDao();
		ruleDao.addRule("Äêºó", "ºÃ°¡", "1");
		List<Rule> list = ruleDao.getAllRules();
		for(Rule rule : list) {
			System.out.println(rule.toString());
		}
		
	}

}
