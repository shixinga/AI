package com.ai.controller;

import java.util.*;

import com.ai.dao.RulesDAO;
import com.ai.vo.RuleVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class Inference {
	
	private ArrayList<String> InputList = new ArrayList<String>();

	private ArrayList<RuleVO> rulesList;//输入的判断条件（已作规范化）

	//private ArrayList<String> factbase;//事实库，随着推理进行添加
	private ArrayList<String> resultbase;//结果库，存放当前所有作为结论的动物集

	public Inference(ArrayList<String> InputList) {
		this.InputList = InputList;
		rulesList = new ArrayList<RuleVO>();
		rulesList = new RulesDAO().getRules();
		resultbase = new ArrayList<String>();
		resultbase = new RulesDAO().getResultBase();
	}
/*该方法通过比较用户输入和规则列表中条件部分,判定用户输入是否包含了某条规则的条件部分,
 * 若不是,就比较下条规则的条件,不停地循环,直至最后一条规则.
 * 若是,判定该条规则的结论是否为最终结论,若是,则返回结论!否则,判断该条规则的结论是否应放置在用户输入列表中.
 * */
	public String MatchAndFindResult() {
		/*判定用户输入是否为空,以及是否矛盾*/
		//if (InputList != null && isContradict(InputList)) {
		boolean getnewinput = true;
		while(getnewinput){
			if (InputList != null) {
				getnewinput = false;
				for (int i = 0; i< rulesList.size(); i++) {
					//规则库中的顺位规则
					RuleVO rule = rulesList.get(i);
					//如果综合数据库中可以匹配到该规则
					if (InputList.containsAll(rule.conditions)){
						
						String result = rule.result;
						//如果含最终解
						if (resultbase.contains(result)) {
							return result;
						}else if(!InputList.contains(result)){
								InputList.add(result);
								rulesList.remove(rule);
								getnewinput = true;
						}
					}
				}
			}
		}
		return null;
	}

	public String MatchAndFindResult1() {
		/*判定用户输入是否为空,以及是否矛盾*/
		//if (InputList != null && isContradict(InputList)) {
		boolean getnewinput = true;
		String returnstr="";
		List<Map> list = new ArrayList<Map>();
		int resultnum=0;
		while(getnewinput){
			if (InputList != null) {
				getnewinput = false;
				for (int i = 0; i< rulesList.size(); i++) {
					//规则库中的顺位规则
					RuleVO rule = rulesList.get(i);
					//如果综合数据库中可以匹配到该规则
					if (InputList.containsAll(rule.conditions)){
						
						 JSONObject jo = new JSONObject();

					        // 下面构造两个map、一个list和一个Employee对象
						 Map<String, String> map = new HashMap<String, String>();
					     map.put("conditions", rule.conditionstr);
					     map.put("result", rule.result);
					        
					        list.add(map);
						
						String result = rule.result;
						//如果含最终解
						if (resultbase.contains(result)) {
							JSONArray jay = JSONArray.fromObject(list);
							returnstr = jay.toString();
							rulesList.remove(rule);
							resultnum++;
							System.out.println(rulesList.size()+"dada");
							i--;
							//return jay.toString();
						}else if(!InputList.contains(result)){
								InputList.add(result);
								rulesList.remove(rule);
								System.out.println(rulesList.size()+"kaka");
								i--;
								getnewinput = true;
						}
						
					}
				}
			}
		}
		System.out.println(resultnum+"eeee");
		if(resultnum>1)
			returnstr="2";
		else if(resultnum==0)
			returnstr="0";
		
		return returnstr;
	}
	

}
