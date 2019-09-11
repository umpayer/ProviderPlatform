package com.umpay.busi.step1;

import com.umpay.call.AuditMer;
import com.umpay.consts.BusiConsts;
import com.umpay.consts.BusiResEnum;
import com.umpay.consts.StepEnum;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.tools.check.CheckUtil;
import com.umpay.tools.json.JsonResBuild;
import com.umpay.util.LogUtil;

public class Step08AuditMer {
	public static	JsonResBuild res=new JsonResBuild();
	public static	StepEnum step=StepEnum.AuditMer;
	
	/***
	 * 
	 * @param acqSpId
	 * @param ysfMerId
	 * @return
	 * 		acqMerId
	 */
	@SuppressWarnings({ "finally", "unchecked" })
	public static String  step(String acqSpId,String acqMerId,String nopassFlag,String merChantType)  {
		 //1、准备工作检查
        try {
        	CheckUtil.CheckArgs(acqSpId,acqMerId,nopassFlag,merChantType);
			new EnvConfig().initConfig();
	        EnvConfig.context.clear();
	        EnvConfig.context.put(BusiConsts.acqSpId, acqSpId);
	        EnvConfig.context.put(BusiConsts.acqMerId, acqMerId);
	        EnvConfig.context.put(BusiConsts.nopassFlag, nopassFlag);
	        EnvConfig.context.put(BusiConsts.merChantType, merChantType);
	        //
	        new AuditMer().auditMer();
            res.append(BusiResEnum.SUCCESS, "商户信息审核成功",step);
	        LogUtil.info(step.getFlowLoggerName()+"节点处理成功");
        } catch (Exception e) {
        	LogUtil.error(e.getMessage(), e);
			res.append(BusiResEnum.ERROR, e.getMessage(),step);
		}finally {
			return res.toJsonString();
		}
        
		
	}
}
