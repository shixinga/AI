package com.haha.model;

import java.util.ArrayList;
import java.util.List;

public class Rule {

	private int mId;
	private List<String> mConditions;
	private String mResult;
	private int mCan_end;
	private String mConditionsStr;
	
	
	public Rule() {
		mCan_end = 0;
		mConditions = new ArrayList<>();
	}
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public List<String> getmConditions() {
		return mConditions;
	}
	public void setmConditions(List<String> mConditions) {
		this.mConditions = mConditions;
	}
	public String getmResult() {
		return mResult;
	}
	public void setmResult(String mResult) {
		this.mResult = mResult;
	}
	public int getmCan_end() {
		return mCan_end;
	}
	public void setmCan_end(int mCan_end) {
		this.mCan_end = mCan_end;
	}
	public String getmConditionsStr() {
		return mConditionsStr;
	}
	public void setmConditionsStr(String mConditionsStr) {
		this.mConditionsStr = mConditionsStr;
	}
	@Override
	public String toString() {
		String returnStr = "id=" + mId + ",mconditionstr=" + mConditionsStr + ",mResult=" + mResult + ",mCan_end=" + mCan_end + ",";
		for(String condition : mConditions) {
			returnStr += condition;
		}
		return returnStr;
	}
	
	
	
	
}
