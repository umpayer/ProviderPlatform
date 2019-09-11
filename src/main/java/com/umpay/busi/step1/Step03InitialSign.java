package com.umpay.busi.step1;

import com.umpay.consts.BusiConsts;
import com.umpay.consts.BusiResEnum;
import com.umpay.consts.StepEnum;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.demo.step1_商户入网流程.API_6_7电子签约;
import com.umpay.tools.check.CheckUtil;
import com.umpay.tools.json.JsonResBuild;
import com.umpay.util.LogUtil;

public class Step03InitialSign {
	public static	JsonResBuild res=new JsonResBuild();
	public static	StepEnum step=StepEnum.InitialSign;
	
	/**
	 * 
	 * @param acqSpId
	 * @param ysfMerId
	 * @return
	 * 		transCaId 缓存事务ID
	 */
	@SuppressWarnings({ "finally", "unchecked" })
	public static String  step(String acqSpId,String ysfMerId)  {
		 //1、准备工作检查
        try {
        	CheckUtil.CheckArgs(acqSpId,ysfMerId);
			new EnvConfig().initConfig();
	        EnvConfig.context.clear();
	        EnvConfig.context.put(BusiConsts.acqSpId, acqSpId);
	        EnvConfig.context.put(BusiConsts.merId, ysfMerId);
	        
	        //3、2.6获取电子签约挑战码
	        new API_6_7电子签约().verifyCode_获取电子签约挑战码();
            res.append(BusiResEnum.SUCCESS, "获取电子签约挑战码成功",step);
            res.append(BusiConsts.transCaId, (String) EnvConfig.context.get(BusiConsts.transCaId));
	        LogUtil.info(step.getFlowLoggerName()+"节点处理成功");
        } catch (Exception e) {
        	LogUtil.error(e.getMessage(), e);
			res.append(BusiResEnum.ERROR, e.getMessage(),step);
		}finally {
			return res.toJsonString();
		}
        
		
	}
}
