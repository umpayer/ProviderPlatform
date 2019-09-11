package com.umpay.busi.step2;

import com.umpay.consts.BusiConsts;
import com.umpay.consts.BusiResEnum;
import com.umpay.consts.StepEnum;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.demo.step4_交易查询.API3_4订单状态同步;
import com.umpay.tools.check.CheckUtil;
import com.umpay.tools.json.JsonResBuild;
import com.umpay.util.LogUtil;

public class Step24QueryOrder {
	public static	JsonResBuild res=new JsonResBuild();
	public static	StepEnum step=StepEnum.QueryOrder;
	
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
	        new API3_4订单状态同步().orderQuery_订单状态同步();
            res.append(BusiResEnum.SUCCESS, "订单状态同步成功",step);
            res.append(BusiConsts.orderNo, (String) EnvConfig.context.get(BusiConsts.orderNo));
            res.append(BusiConsts.orderType, (String) EnvConfig.context.get(BusiConsts.orderType));
            res.append(BusiConsts.acqMerId, (String) EnvConfig.context.get(BusiConsts.acqMerId));
            res.append(BusiConsts.transactionId, (String) EnvConfig.context.get(BusiConsts.transactionId));
            res.append(BusiConsts.txnAmt, (String) EnvConfig.context.get(BusiConsts.txnAmt));
            res.append(BusiConsts.origRespCode, (String) EnvConfig.context.get(BusiConsts.origRespCode));
            res.append(BusiConsts.origRespDescCode, (String) EnvConfig.context.get(BusiConsts.origRespDescCode));
	        LogUtil.info(step.getFlowLoggerName()+"节点处理成功");
        } catch (Exception e) {
        	LogUtil.error(e.getMessage(), e);
			res.append(BusiResEnum.ERROR, e.getMessage(),step);
		}finally {
			return res.toJsonString();
		}
        
		
	}
}
