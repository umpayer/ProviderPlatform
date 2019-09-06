package com.umpay.demo.step5_交易撤销_退费;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.umpay.call.BaseAPI;
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
		reqPay.put("orderTime", TimeUtil.datetime14());//订单时间	16	M	yyyyMMddHHmms
		reqPay.put("acqSpId", EnvConfig.acqSpId);//代理商编号	10	M	代理商编号(联动平台分配)
		reqPay.put("acqMerId", acqMerId);//商户号	8	M	商户号(联动平台分配)
		reqPay.put("origOrderNo", orderNo);//原单流水号	64	M	原交易的订单号
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

			String respCode = (String) treeMap.get("respCode");
			if ("00".equals(respCode)) {
				org.junit.Assert.assertTrue("订单退费成功", true);
			}else{
				String respMsg = (String) treeMap.get("respMsg");
				org.junit.Assert.assertTrue("订单退费失败：" + respMsg, false);
			}
		}catch (Exception e) {
			org.junit.Assert.assertTrue("订单退费异常", false);
		}
	}

}
