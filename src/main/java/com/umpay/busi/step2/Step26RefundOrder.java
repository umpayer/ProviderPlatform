package com.umpay.busi.step2;

import com.umpay.consts.BusiConsts;
import com.umpay.consts.BusiResEnum;
import com.umpay.consts.StepEnum;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.demo.step5_交易撤销_退费.API3_5订单撤销;
import com.umpay.demo.step5_交易撤销_退费.API3_6订单退费;
import com.umpay.tools.check.CheckUtil;
import com.umpay.tools.json.JsonResBuild;
import com.umpay.util.LogUtil;

public class Step26RefundOrder {
	public static	JsonResBuild res=new JsonResBuild();
	public static	StepEnum step=StepEnum.RefundOrder;
	
	/***
	 * 
	 * @param acqSpId
	 * @param ysfMerId
	 * @return
	 * 		acqMerId
	 */
	@SuppressWarnings({ "finally", "unchecked" })
	public static String  step(String acqSpId,String acqMerId,String orderNo)  {
		 //1、准备工作检查
        try {
        	CheckUtil.CheckArgs(acqSpId,acqMerId,orderNo);
			new EnvConfig().initConfig();
	        EnvConfig.context.clear();
	        EnvConfig.context.put(BusiConsts.acqSpId, acqSpId);
	        EnvConfig.context.put(BusiConsts.acqMerId, acqMerId);
	        EnvConfig.context.put(BusiConsts.orderNo, orderNo);
	        //
	        new API3_6订单退费().refund_商户退款请求();
            res.append(BusiResEnum.SUCCESS, "商户退款交易成功",step);
            res.append(BusiConsts.RespCode, (String) EnvConfig.context.get(BusiConsts.RespCode));
            res.append(BusiConsts.RespMsg, (String) EnvConfig.context.get(BusiConsts.RespMsg));
            res.append(BusiConsts.transactionId, (String) EnvConfig.context.get(BusiConsts.transactionId));
            res.append(BusiConsts.orderNo, (String) EnvConfig.context.get(BusiConsts.orderNo));
            res.append(BusiConsts.origRespCode, (String) EnvConfig.context.get(BusiConsts.origRespCode));
	        LogUtil.info(step.getFlowLoggerName()+"节点处理成功");
        } catch (Exception e) {
        	LogUtil.error(e.getMessage(), e);
			res.append(BusiResEnum.ERROR, e.getMessage(),step);
		}finally {
			return res.toJsonString();
		}
        
		
	}
}
