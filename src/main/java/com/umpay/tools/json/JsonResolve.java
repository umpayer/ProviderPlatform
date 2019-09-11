package com.umpay.tools.json;

import java.util.Iterator;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.umpay.util.StringUtil;

public class JsonResolve {

	public static JSONObject resolve(String str) {
		if(StringUtil.isEmpty(str))new JSONObject();
		return JSONObject.parseObject(str);
	}

	/***
	 * 在josn数组中递归获取key的对应值
	 * @param json
	 * @param checkId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String gainValueForNesting(JSONArray json, String checkId){
		String res="";
		Iterator jsonIterator=json.iterator();
		while(jsonIterator.hasNext()){
			Object object =jsonIterator.next();
			if(object instanceof JSONObject){
				res=gainValueForNesting((JSONObject)object,checkId);
				if(!"".equals(res))return res;
			}
			if(object instanceof JSONArray){
				res=gainValueForNesting((JSONArray)object,checkId);
				if(!"".equals(res))return res;
			}
			if(checkId.equals(object)){
				return (String)object;
			}
		}
		return "";
	}
	
	
	/**
	 * 在josn对象中，获取第一层key的对应值
	 * @param json
	 * @param checkId
	 * @return
	 */
	public static String gainValueForSingle(JSONObject json, String checkId){
		if(json.containsKey(checkId)){
			return (String)json.get(checkId);
		}else{
			return "";
		}
	}
	
	/**
	 * 在json对象中，递归获取key的对应值
	 * @param json
	 * @param checkId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String gainValueForNesting(JSONObject json, String checkId){
		Set keyset=json.keySet();
		String res="";
		for (Object object : keyset) {
			if(json.get(object) instanceof JSONObject){
				res=gainValueForNesting((JSONObject)json.get(object),checkId);
				if(!"".equals(res))return res;
			}
			if(json.get(object) instanceof JSONArray){
				res=gainValueForNesting((JSONArray)json.get(object),checkId);
				if(!"".equals(res))return res;
			}
			if(checkId.equals(object)){
				return (String)json.get(object);
			}
		}
		return "";
	}
	
	
}
