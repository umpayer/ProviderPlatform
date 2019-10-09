package com.umpay.busi.other;

import com.umpay.call.UpdateYsfMerInfoForDB;
import com.umpay.consts.BusiConsts;
import com.umpay.consts.BusiResEnum;
import com.umpay.consts.StepEnum;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.tools.check.CheckUtil;
import com.umpay.tools.json.JsonResBuild;
import com.umpay.util.LogUtil;

public class UpdateYsfMerInfo {
	public static	JsonResBuild res=new JsonResBuild();
	public static	StepEnum step=StepEnum.UpdateYsfMerInfo;
	
	@SuppressWarnings({ "finally", "unchecked" })
	public static String  step(String merId,String auditstatus)  {
		 //1、准备工作检查
        try {
        	CheckUtil.CheckArgs(merId);
			new EnvConfig().initConfig();
	        EnvConfig.context.clear();
	        EnvConfig.context.put(BusiConsts.merId, merId);
	        EnvConfig.context.put(BusiConsts.auditStatus, auditstatus);
	        
	        //查询合同编号
	        new UpdateYsfMerInfoForDB().UpdateAuditStatusYsfMerInfo();
            res.append(BusiResEnum.SUCCESS, "更新商户状态信息成功",step);
            res.append(BusiConsts.merId, (String) EnvConfig.context.get(BusiConsts.merId));
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
