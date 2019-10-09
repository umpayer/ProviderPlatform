package com.umpay.busi.other;

import com.umpay.call.QueryYsfMerInfoForDB;
import com.umpay.consts.BusiConsts;
import com.umpay.consts.BusiResEnum;
import com.umpay.consts.StepEnum;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.tools.check.CheckUtil;
import com.umpay.tools.json.JsonResBuild;
import com.umpay.util.LogUtil;

public class QueryYsfMerInfo {
	public static	JsonResBuild res=new JsonResBuild();
	public static	StepEnum step=StepEnum.QurayYsfMerInfo;
	
	@SuppressWarnings({ "finally", "unchecked" })
	public static String  step(String merId)  {
		 //1、准备工作检查
        try {
        	CheckUtil.CheckArgs(merId);
			new EnvConfig().initConfig();
	        EnvConfig.context.clear();
	        EnvConfig.context.put(BusiConsts.merId, merId);
	        
	        //查询合同编号
	        new QueryYsfMerInfoForDB().QueryYsfMerInfo();
            res.append(BusiResEnum.SUCCESS, "查询商户信息成功",step);
            res.append(BusiConsts.acqMerId, (String) EnvConfig.context.get(BusiConsts.acqMerId));
            res.append(BusiConsts.auditStatus, (String) EnvConfig.context.get(BusiConsts.auditStatus));
	        LogUtil.info(step.getFlowLoggerName()+"节点处理成功");
        } catch (Exception e) {
        	LogUtil.error(e.getMessage(), e);
			res.append(BusiResEnum.ERROR, e.getMessage(),step);
		}finally {
			return res.toJsonString();
		}
        
		
	}
}
