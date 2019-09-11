package com.umpay.busi.step2;

import com.umpay.consts.BusiConsts;
import com.umpay.consts.BusiResEnum;
import com.umpay.consts.StepEnum;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.demo.step3_收款交易.API3_2刷卡支付_用户被扫;
import com.umpay.tools.check.CheckUtil;
import com.umpay.tools.json.JsonResBuild;
import com.umpay.util.LogUtil;

public class Step22MicroPay {
	public static	JsonResBuild res=new JsonResBuild();
	public static	StepEnum step=StepEnum.MicroPay;
	
	/***
	 * 
	 * @param acqSpId
	 * @param ysfMerId
	 * @return
	 * 		acqMerId
	 */
	@SuppressWarnings({ "finally", "unchecked" })
	public static String  step(String acqSpId,String acqMerId,String orderType,String authCode)  {
		 //1、准备工作检查
        try {
        	CheckUtil.CheckArgs(acqSpId,acqMerId,orderType,authCode);
			new EnvConfig().initConfig();
	        EnvConfig.context.clear();
	        EnvConfig.context.put(BusiConsts.acqSpId, acqSpId);
	        EnvConfig.context.put(BusiConsts.acqMerId, acqMerId);
	        EnvConfig.context.put(BusiConsts.orderType, orderType);
	        EnvConfig.context.put(BusiConsts.authCode, authCode);
	        //
	        new API3_2刷卡支付_用户被扫().pay_刷卡支付_用户被扫();
            res.append(BusiResEnum.SUCCESS, "用户被扫支付成功",step);
            res.append(BusiConsts.orderNo, (String) EnvConfig.context.get(BusiConsts.orderNo));
            res.append(BusiConsts.transactionId, (String) EnvConfig.context.get(BusiConsts.transactionId));
            res.append(BusiConsts.RespCode, (String) EnvConfig.context.get(BusiConsts.RespCode));
            res.append(BusiConsts.RespMsg, (String) EnvConfig.context.get(BusiConsts.RespMsg));
            
	        LogUtil.info(step.getFlowLoggerName()+"节点处理成功");
        } catch (Exception e) {
        	LogUtil.error(e.getMessage(), e);
			res.append(BusiResEnum.ERROR, e.getMessage(),step);
		}finally {
			return res.toJsonString();
		}
        
		
	}
}
