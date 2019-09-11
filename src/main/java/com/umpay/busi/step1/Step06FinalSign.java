package com.umpay.busi.step1;

import com.umpay.consts.BusiConsts;
import com.umpay.consts.BusiResEnum;
import com.umpay.consts.StepEnum;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.demo.step1_商户入网流程.API_6_7电子签约;
import com.umpay.tools.check.CheckUtil;
import com.umpay.tools.json.JsonResBuild;
import com.umpay.util.LogUtil;

public class Step06FinalSign {
	public static	JsonResBuild res=new JsonResBuild();
	public static	StepEnum step=StepEnum.FinalSign;
	
	/***
	 * 
	 * @param acqSpId
	 * @param ysfMerId
	 * @param transCaId
	 * @param verifyCode
	 * @return
	 * 		
	 */
	@SuppressWarnings({ "finally", "unchecked" })
	public static String  step(String acqSpId,String ysfMerId,String transCaId,String verifyCode)  {
		 //1、准备工作检查
        try {
        	CheckUtil.CheckArgs(acqSpId,ysfMerId,transCaId,verifyCode);
			new EnvConfig().initConfig();
	        EnvConfig.context.clear();
	        EnvConfig.context.put(BusiConsts.acqSpId, acqSpId);
	        EnvConfig.context.put(BusiConsts.merId, ysfMerId);
	        EnvConfig.context.put(BusiConsts.transCaId, transCaId);
	        EnvConfig.context.put(BusiConsts.verifyCode, verifyCode);
	        
	        //3、2.6获取电子签约挑战码
	        new API_6_7电子签约().verifyCode_电子签约确认();
            res.append(BusiResEnum.SUCCESS, "电子签约确认成功",step);
//            res.append(BusiConsts.transCaId, (String) EnvConfig.context.get(BusiConsts.transCaId));
	        LogUtil.info(step.getFlowLoggerName()+"节点处理成功");
        } catch (Exception e) {
        	LogUtil.error(e.getMessage(), e);
			res.append(BusiResEnum.ERROR, e.getMessage(),step);
		}finally {
			return res.toJsonString();
		}
        
		
	}
}
