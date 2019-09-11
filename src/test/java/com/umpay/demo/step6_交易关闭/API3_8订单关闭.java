package com.umpay.demo.step6_交易关闭;

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
 * description: 关闭订单测试类
 * author: zhanghuanqi
 * date: 2019/8/19
 * time: 下午4:27
 */
public class API3_8订单关闭 extends BaseAPI {
	public String queryUrl = EnvConfig.url + "pay/closeOrder";
	
	@SuppressWarnings("unchecked")
	@Test
	public void orderClose_商户订单关闭请求() throws Exception{
		TreeMap<String, Object> reqPay = new TreeMap<String, Object>();
		Assert.assertNotNull("参数缺失,服务商编号", EnvConfig.context.get(BusiConsts.acqSpId));
    	Assert.assertNotNull("参数缺失,内部商户号编号", EnvConfig.context.get(BusiConsts.acqMerId));
    	Assert.assertNotNull("参数缺失,订单号", EnvConfig.context.get(BusiConsts.refundOrderNo));
    	reqPay.put("acqSpId",(String) EnvConfig.context.get(BusiConsts.acqSpId));//服务商编号	10	M	服务商编号
        reqPay.put("acqMerId", (String)EnvConfig.context.get(BusiConsts.acqMerId));
        reqPay.put("orderNo", (String)EnvConfig.context.get(BusiConsts.orderNo));//商户订单号	64	M	商户的支付订单号
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
				org.junit.Assert.assertTrue("订单关闭成功", true);
			}else{
				String respMsg = (String) treeMap.get("respMsg");
				org.junit.Assert.assertTrue("订单关闭失败：" + respMsg, false);
			}
		}catch (Exception e) {
			org.junit.Assert.assertTrue("订单关闭异常", false);
		}
	}

}
