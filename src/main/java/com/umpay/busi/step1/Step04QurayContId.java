package com.umpay.busi.step1;

import com.umpay.call.VerifyCode;
import com.umpay.consts.BusiConsts;
import com.umpay.consts.BusiResEnum;
import com.umpay.consts.StepEnum;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.tools.check.CheckUtil;
import com.umpay.tools.json.JsonResBuild;
import com.umpay.util.LogUtil;

public class Step04QurayContId {
	public static	JsonResBuild res=new JsonResBuild();
	public static	StepEnum step=StepEnum.QurayContId;
	
	@SuppressWarnings({ "finally", "unchecked" })
	public static String  step(String merId)  {
		 //1、准备工作检查
        try {
        	CheckUtil.CheckArgs(merId);
			new EnvConfig().initConfig();
	        EnvConfig.context.clear();
	        EnvConfig.context.put(BusiConsts.merId, merId);
	        
	        //查询合同编号
	        new VerifyCode().查询合同编号();
            res.append(BusiResEnum.SUCCESS, "查询合同编号成功",step);
            res.append(BusiConsts.contId, (String) EnvConfig.context.get(BusiConsts.contId));
	        LogUtil.info(step.getFlowLoggerName()+"节点处理成功");
        } catch (Exception e) {
        	LogUtil.error(e.getMessage(), e);
			res.append(BusiResEnum.ERROR, e.getMessage(),step);
		}finally {
			return res.toJsonString();
		}
        
		
	}
}
