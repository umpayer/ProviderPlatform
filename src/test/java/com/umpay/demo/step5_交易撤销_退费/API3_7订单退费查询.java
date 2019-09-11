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
import com.umpay.util.HttpUtilClient;
import com.umpay.util.LogUtil;

/**
 * description: 退费查询接口测试类
 * author: zhanghuanqi
 * date: 2019/8/19
 * time: 下午4:18
 */
public class API3_7订单退费查询 extends BaseAPI {
	public String queryUrl = EnvConfig.url + "pay/refundQuery";
	
	@SuppressWarnings("unchecked")
	@Test
	public void refundQuery_退款订单查询() throws Exception{
		TreeMap<String, Object> reqPay = new TreeMap<String, Object>();
		Assert.assertNotNull("参数缺失,服务商编号", EnvConfig.context.get(BusiConsts.acqSpId));
    	Assert.assertNotNull("参数缺失,内部商户号编号", EnvConfig.context.get(BusiConsts.acqMerId));
    	Assert.assertNotNull("参数缺失,订单号", EnvConfig.context.get(BusiConsts.refundOrderNo));
    	reqPay.put("acqSpId",(String) EnvConfig.context.get(BusiConsts.acqSpId));//服务商编号	10	M	服务商编号
        reqPay.put("acqMerId", (String)EnvConfig.context.get(BusiConsts.acqMerId));
        reqPay.put("orderNo", (String)EnvConfig.context.get(BusiConsts.refundOrderNo));//商户订单号	64	M	商户的支付订单号
//		reqPay.put("orderNo", refundOrderNo);//商户订单号	64	M	商户的支付订单号
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
			EnvConfig.context.put(BusiConsts.transactionId,treeMap.get(BusiConsts.transactionId));
			EnvConfig.context.put(BusiConsts.RespCode,treeMap.get(BusiConsts.RespCode));
			EnvConfig.context.put(BusiConsts.RespMsg,treeMap.get(BusiConsts.RespMsg));
			EnvConfig.context.put(BusiConsts.origRespCode,treeMap.get(BusiConsts.origRespCode));
			EnvConfig.context.put(BusiConsts.orderNo,treeMap.get(BusiConsts.orderNo));
			if ("00".equals(respCode)) {
				org.junit.Assert.assertTrue("订单退费查询成功", true);
			}else{
				String respMsg = (String) treeMap.get("respMsg");
				org.junit.Assert.assertTrue("订单退费查询失败：" + respMsg, true);
			}
		}catch (Exception e) {
			org.junit.Assert.assertTrue("订单退费查询异常", false);
		}
	}
	
}
