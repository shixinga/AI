package com.ai.vo;

import java.util.ArrayList;

public class RuleVO {
	                     //结论为字符串类型
	public int id;
	public ArrayList<String> conditions ;//= new ArrayList<String>();          //条件被放置在列表类中
	public String result;
	public int can_end;

	public String conditionstr;
	public RuleVO(){
		conditions = new ArrayList<String>();
		can_end=0;
	}
//	public Rule(ArrayList<String> cause, String result) {
//		this.result = result;
//		this.cause = cause;
//
//	}
}
