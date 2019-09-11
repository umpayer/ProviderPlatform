package com.umpay.tools.json;

import com.alibaba.fastjson.JSONObject;
import com.umpay.consts.BusiConsts;
import com.umpay.consts.BusiResEnum;
import com.umpay.consts.StepEnum;

public class JsonResBuild {
	
	public JSONObject res;
	
	
	
	public JsonResBuild() {
		super();
		this.res = new JSONObject();
	}
	
	public JsonResBuild(JSONObject res) {
		super();
		this.res = res;
	}



	public  JsonResBuild append(String key,String value){
		this.res.put(key, value);
		return this;
	}
	
	public  JsonResBuild append(BusiResEnum bre,String exText){
		this.res.put(BusiConsts.RETCODE,bre.getRetCode());
		this.res.put(BusiConsts.RETMSG,bre.getRetMsg());
		this.res.put(BusiConsts.EXRETMSG,exText);
		return this;
	}
	public  JsonResBuild append(BusiResEnum bre,String exText,StepEnum step){
		this.res.put(BusiConsts.RETCODE,bre.getRetCode());
		this.res.put(BusiConsts.RETMSG,bre.getRetMsg());
		this.res.put(BusiConsts.EXRETMSG,exText);
		this.res.put(BusiConsts.STEPNAME,step.getFlowLoggerName());
		this.res.put(BusiConsts.STEPPATH,step.getFlowPath());
		return this;
	}
	
	public  String  toJsonString(){
		return this.res.toString();
	}

}
