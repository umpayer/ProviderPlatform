package com.umpay.busi.step1;

import com.umpay.consts.BusiConsts;
import com.umpay.consts.BusiResEnum;
import com.umpay.consts.StepEnum;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.demo.step2_入网成功配置参数.API_3商户信息查询;
import com.umpay.tools.check.CheckUtil;
import com.umpay.tools.json.JsonResBuild;
import com.umpay.util.LogUtil;

public class Step07QueryMerInfo {
	public static	JsonResBuild res=new JsonResBuild();
	public static	StepEnum step=StepEnum.QueryMerInfo;
	
	/***
	 * 
	 * @param acqSpId
	 * @param ysfMerId
	 * @return
	 * 		acqMerId
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
	        new API_3商户信息查询().queryOrder_商户信息查询();
            res.append(BusiResEnum.SUCCESS, "商户信息查询成功",step);
            res.append(BusiConsts.acqMerId, (String) EnvConfig.context.get(BusiConsts.acqMerId));
	        LogUtil.info(step.getFlowLoggerName()+"节点处理成功");
        } catch (Exception e) {
        	LogUtil.error(e.getMessage(), e);
			res.append(BusiResEnum.ERROR, e.getMessage(),step);
		}finally {
			return res.toJsonString();
		}
        
		
	}
}
