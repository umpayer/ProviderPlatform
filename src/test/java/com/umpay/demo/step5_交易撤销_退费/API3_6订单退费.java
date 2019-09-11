package com.umpay.demo.step5_交易撤销_退费;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.umpay.call.BaseAPI;
import com.umpay.consts.BusiConsts;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.util.AddSign;
import com.umpay.util.Common;
import com.umpay.util.HttpUtilClient;
import com.umpay.util.LogUtil;
import com.umpay.util.TimeUtil;

/**
 * description: 退费接口测试类
 * author: zhanghuanqi
 * date: 2019/8/19
 * time: 下午3:59
 */
public class API3_6订单退费 extends BaseAPI {
	public String queryUrl = EnvConfig.url + "pay/refund";
	
	@SuppressWarnings("unchecked")
	@Test
	public void refund_商户退款请求() throws Exception{
		TreeMap<String, Object> reqPay = new TreeMap<String, Object>();
		String refundOrderNo = Common.genOrderId();
		Assert.assertNotNull("参数缺失,服务商编号", EnvConfig.context.get(BusiConsts.acqSpId));
    	Assert.assertNotNull("参数缺失,内部商户号编号", EnvConfig.context.get(BusiConsts.acqMerId));
    	Assert.assertNotNull("参数缺失,订单号", EnvConfig.context.get(BusiConsts.orderNo));
    	reqPay.put("acqSpId",(String) EnvConfig.context.get(BusiConsts.acqSpId));//服务商编号	10	M	服务商编号
        reqPay.put("acqMerId", (String)EnvConfig.context.get(BusiConsts.acqMerId));
        reqPay.put("origOrderNo", (String)EnvConfig.context.get(BusiConsts.orderNo));//商户订单号	64	M	商户的支付订单号
        
		reqPay.put("orderTime", TimeUtil.datetime14());//订单时间	16	M	yyyyMMddHHmms
//		reqPay.put("origOrderNo", orderNo);//原单流水号	64	M	原交易的订单号
		reqPay.put("origTxnAmt", "1");//原单交易金额	13	M	单位:原交易的总金额
		reqPay.put("orderNo", refundOrderNo);//退款流水号	64	M	本次退货交易的订单号
		reqPay.put("txnAmt", "1");//退款金额	13	M	本次退货的金额
		reqPay.put("signature", "");
		
		//对请求报文做加签处理
		String reqpay = AddSign.addSign(reqPay);
		Map<String, Object> reqMap = JSONObject.toJavaObject(JSONObject.parseObject(reqpay), Map.class);

		try{
			//发送post请求
			String result = HttpUtilClient.doPostJson(queryUrl, new JSONObject(), reqMap);
			LogUtil.info("输出请求结果:"+result);

			//将响应报文转成map
			Map<String, Object> treeMap = JSON.parseObject(result, TreeMap.class);
			EnvConfig.context.put(BusiConsts.transactionId,treeMap.get(BusiConsts.transactionId));
			EnvConfig.context.put(BusiConsts.RespCode,treeMap.get(BusiConsts.RespCode));
			EnvConfig.context.put(BusiConsts.RespMsg,treeMap.get(BusiConsts.RespMsg));
			String respCode = (String) treeMap.get("respCode");
			if ("00".equals(respCode)) {
				org.junit.Assert.assertTrue("订单退费成功", true);
			}else{
				String respMsg = (String) treeMap.get("respMsg");
				org.junit.Assert.assertTrue("订单退费失败：" + respMsg, true);
			}
		}catch (Exception e) {
			org.junit.Assert.assertTrue("订单退费异常", false);
		}
	}

}
