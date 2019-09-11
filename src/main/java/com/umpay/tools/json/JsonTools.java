package com.umpay.tools.json;

import com.umpay.tools.check.CheckUtil;

public class JsonTools {
	/**
	 * 在json对象中，递归获取key的对应值
	 * @param json
	 * @param checkId
	 * @return
	 */
	public static String gainValueForNesting(String jsonstr, String checkId){
		return JsonResolve.gainValueForNesting(JsonResolve.resolve(jsonstr), checkId);
	}
	/**
	 * 在josn对象中，获取第一层key的对应值
	 * @param json
	 * @param checkId
	 * @return
	 */
	public static String gainValueForSingle(String jsonstr, String checkId){
		return JsonResolve.gainValueForSingle(JsonResolve.resolve(jsonstr), checkId);
	}
	/**
	 * 判断返回码是否成功
	 * @param jsonstr
	 * @return
	 */
	public static String isSuccess(String jsonstr){
		return JsonResolve.gainValueForSingle(JsonResolve.resolve(jsonstr), "RetCode");
	}
	
	
	public static String wait(String time){
		CheckUtil.CheckArgs(time);
		try {
			Thread.sleep(Long.valueOf(time));
			return "0000";
		} catch (Exception e) {
			return "9999";
		}
	}
	
	
	
}
