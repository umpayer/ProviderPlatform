package com.umpay.busi.step1;

import com.umpay.consts.BusiConsts;
import com.umpay.consts.BusiResEnum;
import com.umpay.consts.StepEnum;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.demo.step2_入网成功配置参数.API_8电子协议下载;
import com.umpay.tools.check.CheckUtil;
import com.umpay.tools.json.JsonResBuild;
import com.umpay.util.LogUtil;

public class Step09DownPdf {
	public static	JsonResBuild res=new JsonResBuild();
	public static	StepEnum step=StepEnum.DownPdf;
	
	/***
	 * 
	 * @param acqSpId
	 * @param ysfMerId
	 * @return
	 * 		acqMerId
	 */
	@SuppressWarnings({ "finally", "unchecked" })
	public static String  step(String acqSpId,String merId)  {
		 //1、准备工作检查
        try {
        	CheckUtil.CheckArgs(acqSpId,merId);
			new EnvConfig().initConfig();
	        EnvConfig.context.clear();
	        EnvConfig.context.put(BusiConsts.acqSpId, acqSpId);
	        EnvConfig.context.put(BusiConsts.merId, merId);
	        //
	        new API_8电子协议下载().down_电子协议下载();
            res.append(BusiResEnum.SUCCESS, "电子协议下载成功",step);
	        LogUtil.info(step.getFlowLoggerName()+"节点处理成功");
        } catch (Exception e) {
        	LogUtil.error(e.getMessage(), e);
			res.append(BusiResEnum.ERROR, e.getMessage(),step);
		}finally {
			return res.toJsonString();
		}
        
		
	}
}
