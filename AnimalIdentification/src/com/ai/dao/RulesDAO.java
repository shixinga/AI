package com.ai.dao;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ai.vo.RuleVO;

public class RulesDAO extends BaseDAO {

	public ArrayList<RuleVO> getRules(){
		ArrayList<RuleVO> rules = new ArrayList<RuleVO>();
		String sql = "select * from rules;";
		try {
			stm = getStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()){
				RuleVO rule = new RuleVO();
				String conditionstr = rs.getString("conditions");
				
//				System.out.println(conditionstr);
//				if(conditionstr.contains("+")){
//					
//					String[] conditionlist = conditionstr.split("+");
//					for( String condition:conditionlist){
//						rule.conditions.add(condition);
//					}
//				}else{
//					rule.conditions.add(conditionstr);
//				}
				String[] conditionlist = conditionstr.split("\\+");
				for( String condition:conditionlist){
					rule.conditions.add(condition);
				}
				rule.conditionstr=conditionstr;
				rule.result = rs.getString("result");
				rule.can_end = rs.getInt("can_end");
				rule.id = rs.getInt("id");
				rules.add(rule);
			}
		} catch (SQLException | ClassNotFoundException | IOException | URISyntaxException e) {
			e.printStackTrace();
		}finally{
			this.closeConnection();
		}
		return rules;
	}
	
	public ArrayList<String> getResultBase(){
		ArrayList<String> resultbase = new ArrayList<String>();
		String sql = "select result from rules where can_end = 1;";
		try {
			stm = getStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()){
				String resultstr = rs.getString("result");
				resultbase.add(resultstr);
			}
		} catch (SQLException | ClassNotFoundException | IOException | URISyntaxException e) {
			e.printStackTrace();
		}finally{
			this.closeConnection();
		}
		return resultbase;
	}
	
	public void addRule(ArrayList<String> rulelist,String result,String can_end){
		String rules="";
		int rules_size = rulelist.size();
		for(int i=0;i<rules_size;i++){
			if(rulelist.get(i)!=null){
				if(i!=rules_size-1)
					rules=rules + rulelist.get(i) + "+";
				else
					rules+=rulelist.get(i);
			}
		}
		String sql = "insert into rules (conditions,result,can_end) values ( '"+ rules+"','"+ result+"','"+can_end+"');";
		try {
			stm = getStatement();
			stm.execute(sql);
			
		} catch (SQLException | ClassNotFoundException | IOException | URISyntaxException e) {
			e.printStackTrace();
		}finally{
			this.closeConnection();
		}
	}
	
	public void addRule(String rulelist,String result,String can_end){
		
		String sql = "insert into rules (conditions,result,can_end) values ( '"+rulelist+"','"+ result+"','"+can_end+"');";
		try {
			stm = getStatement();
			stm.execute(sql);
			
		} catch (SQLException | ClassNotFoundException | IOException | URISyntaxException e) {
			e.printStackTrace();
		}finally{
			this.closeConnection();
		}
	}
	
	public boolean deleteById(int id){
		boolean result =  false;
		try {
			String sql = "delete from rules where id = ?";
			pstm = getPreparedStatement(sql);
			pstm.setInt(1,id);
			pstm.executeUpdate();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return result;
	}
	
}
