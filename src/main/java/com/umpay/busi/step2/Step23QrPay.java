package com.umpay.busi.step2;

import com.umpay.consts.BusiConsts;
import com.umpay.consts.BusiResEnum;
import com.umpay.consts.StepEnum;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.demo.step3_收款交易.API3_2刷卡支付_用户被扫;
import com.umpay.demo.step3_收款交易.API3_3扫码支付_用户主扫;
import com.umpay.tools.check.CheckUtil;
import com.umpay.tools.json.JsonResBuild;
import com.umpay.util.LogUtil;

public class Step23QrPay {
	public static	JsonResBuild res=new JsonResBuild();
	public static	StepEnum step=StepEnum.QrPay;
	
	/***
	 * 
	 * @param acqSpId
	 * @param ysfMerId
	 * @return
	 * 		acqMerId
	 */
	@SuppressWarnings({ "finally", "unchecked" })
	public static String  step(String acqSpId,String acqMerId,String orderType,String txnAmt)  {
		 //1、准备工作检查
        try {
        	CheckUtil.CheckArgs(acqSpId,acqMerId,orderType,txnAmt);
			new EnvConfig().initConfig();
	        EnvConfig.context.clear();
	        EnvConfig.context.put(BusiConsts.acqSpId, acqSpId);
	        EnvConfig.context.put(BusiConsts.acqMerId, acqMerId);
	        EnvConfig.context.put(BusiConsts.orderType, orderType);
	        EnvConfig.context.put(BusiConsts.txnAmt, txnAmt);
	        //
	        new API3_3扫码支付_用户主扫().pay_扫码支付_用户主扫();
            res.append(BusiResEnum.SUCCESS, "用户主扫支付成功",step);
            res.append(BusiConsts.orderNo, (String) EnvConfig.context.get(BusiConsts.orderNo));
            res.append(BusiConsts.qrCode, (String) EnvConfig.context.get(BusiConsts.qrCode));
            res.append(BusiConsts.RespCode, (String) EnvConfig.context.get(BusiConsts.RespCode));
            res.append(BusiConsts.RespMsg, (String) EnvConfig.context.get(BusiConsts.RespMsg));
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
