package com.umpay.tools.check;

import com.umpay.tools.except.ArgsNullException;
import com.umpay.util.LogUtil;

public class CheckUtil {

	
	public static void CheckArgs(Object... args){
		for (Object object : args) {
			if(null==object) {
				LogUtil.error("请求参数不全");				
				throw new ArgsNullException();
			}
		}
	}
}
