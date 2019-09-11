package com.umpay.busi.step2;

import com.umpay.consts.BusiConsts;
import com.umpay.consts.BusiResEnum;
import com.umpay.consts.StepEnum;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.demo.step3_收款交易.API3_1公众号小程序支付;
import com.umpay.tools.check.CheckUtil;
import com.umpay.tools.json.JsonResBuild;
import com.umpay.util.LogUtil;

public class Step21UnifiedOrder {
	public static	JsonResBuild res=new JsonResBuild();
	public static	StepEnum step=StepEnum.UnifiedOrder;
	
	/***
	 * 
	 * @param acqSpId
	 * @param ysfMerId
	 * @return
	 * 		acqMerId
	 */
	@SuppressWarnings({ "finally", "unchecked" })
	public static String  step(String acqSpId,String acqMerId,String orderType)  {
		 //1、准备工作检查
        try {
        	CheckUtil.CheckArgs(acqSpId,acqMerId,orderType);
			new EnvConfig().initConfig();
	        EnvConfig.context.clear();
	        EnvConfig.context.put(BusiConsts.acqSpId, acqSpId);
	        EnvConfig.context.put(BusiConsts.acqMerId, acqMerId);
	        EnvConfig.context.put(BusiConsts.orderType, orderType);
	        //
	        new API3_1公众号小程序支付().pay_公众号小程序支付();
            res.append(BusiResEnum.SUCCESS, "公众号小程序支付成功",step);
            res.append(BusiConsts.orderNo, (String) EnvConfig.context.get(BusiConsts.orderNo));
            res.append(BusiConsts.orderType, (String) EnvConfig.context.get(BusiConsts.orderType));
            res.append(BusiConsts.acqMerId, (String) EnvConfig.context.get(BusiConsts.acqMerId));
            res.append(BusiConsts.transactionId, (String) EnvConfig.context.get(BusiConsts.transactionId));
            res.append(BusiConsts.txnAmt, (String) EnvConfig.context.get(BusiConsts.txnAmt));
	        LogUtil.info(step.getFlowLoggerName()+"节点处理成功");
        } catch (Exception e) {
        	LogUtil.error(e.getMessage(), e);
			res.append(BusiResEnum.ERROR, e.getMessage(),step);
		}finally {
			return res.toJsonString();
		}
        
		
	}
}
