package com.haha.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.haha.model.Rule;

public class RuleDao extends BaseDao {

	public static final String SQL_RULES_ALL = "select * from rules;";
	public static final String SQL_RULES_BASE = "select result from rules where can_end = 1;";
	
	public List<Rule> getAllRules() {
		List<Rule> rules = new ArrayList<>();
		try {
			mStatement = getStatement();
			mResultSet = mStatement.executeQuery(SQL_RULES_ALL);
			while(mResultSet.next()) {
				Rule rule = new Rule();
				rule.setmId(mResultSet.getInt("id"));
				rule.setmCan_end(mResultSet.getInt("can_end"));
				rule.setmResult(mResultSet.getString("result"));
				String conditionStr = mResultSet.getString("conditions");
				rule.setmConditionsStr(conditionStr);
				if (conditionStr != null) {
					String [] conditions = conditionStr.split("\\+");
					for(String condition : conditions) {
						rule.getmConditions().add(condition);
					}
				}
				rules.add(rule);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return rules;
	}

	public ArrayList<String> getResultBase() {
		
		ArrayList<String> resultBase = new ArrayList<>();
		try {
			mStatement = getStatement();
			mResultSet = mStatement.executeQuery(SQL_RULES_BASE);
			while(mResultSet.next()) {
				resultBase.add(mResultSet.getString("result"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
			
		}
		
		
		return resultBase;
	}

	public void addRule(String rules, String result, String can_end) {

		String sql_insert = "insert into rules(conditions,result,can_end)"
				+ " values('"+rules+"','"+result+"',"+can_end+")";
		try {
			mStatement = getStatement();
			mStatement.execute(sql_insert);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public void deleteById(int parseInt) {

		String sql_delete = "delete from rules where id = ?";
		try {
			mPreparedStatement = getPreparedStatement(sql_delete);
			mPreparedStatement.setInt(1, parseInt);
			int row = mPreparedStatement.executeUpdate();
			System.out.println(row);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
