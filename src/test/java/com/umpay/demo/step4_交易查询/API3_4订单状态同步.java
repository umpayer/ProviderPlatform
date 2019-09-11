package com.umpay.demo.step4_交易查询;

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
 * @author: zhangjing
 * @date:2019年8月6日 下午2:30:09
 * @类说明:订单查询接口
 * @产品号:
 */
public class API3_4订单状态同步 extends BaseAPI {
	public String queryUrl = EnvConfig.url + "pay/queryOrder";
	
	@Test
	@SuppressWarnings("unchecked")
	public void orderQuery_订单状态同步() throws Exception{
		TreeMap<String, Object> reqPay = new TreeMap<String, Object>();
		/***********	参数校验	*********/
    	Assert.assertNotNull("参数缺失,服务商编号", EnvConfig.context.get(BusiConsts.acqSpId));
    	Assert.assertNotNull("参数缺失,内部商户号编号", EnvConfig.context.get(BusiConsts.acqMerId));
    	Assert.assertNotNull("参数缺失,订单号", EnvConfig.context.get(BusiConsts.orderNo));
    	reqPay.put("acqSpId",(String) EnvConfig.context.get(BusiConsts.acqSpId));//服务商编号	10	M	服务商编号
        reqPay.put("acqMerId", (String)EnvConfig.context.get(BusiConsts.acqMerId));
        reqPay.put("orderNo", (String)EnvConfig.context.get(BusiConsts.orderNo));//商户订单号	64	M	商户的支付订单号
        
		reqPay.put("transactionId", "");//联动订单号	32	O	联动优势的订单号，建议优先使用	
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
				org.junit.Assert.assertTrue("订单状态同步成功", true);
				EnvConfig.context.put(BusiConsts.RespCode,treeMap.get(BusiConsts.RespCode));
				EnvConfig.context.put(BusiConsts.RespMsg,treeMap.get(BusiConsts.RespMsg));
				EnvConfig.context.put(BusiConsts.origRespCode,treeMap.get(BusiConsts.origRespCode));
				EnvConfig.context.put(BusiConsts.origRespDescCode,treeMap.get(BusiConsts.origRespDescCode));
			}else{
				String respMsg = (String) treeMap.get("respMsg");
				EnvConfig.context.put(BusiConsts.RespCode,treeMap.get(BusiConsts.RespCode));
				EnvConfig.context.put(BusiConsts.RespMsg,treeMap.get(BusiConsts.RespMsg));
				org.junit.Assert.assertTrue("订单状态同步失败：" + respMsg, false);
			}
		}catch (Exception e) {
			org.junit.Assert.assertTrue("订单状态同步异常", false);
		}
	}

}
