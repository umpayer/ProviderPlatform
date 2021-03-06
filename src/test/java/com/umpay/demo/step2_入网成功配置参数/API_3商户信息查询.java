package com.umpay.demo.step2_入网成功配置参数;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.testng.annotations.Test;

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
 * @date:2019年8月6日 下午4:40:51
 * @类说明:商户查询接口
 * @产品号:
 */
public class API_3商户信息查询 extends BaseAPI {
	private String queryUrl = EnvConfig.url + "merchants/queryMerchantInfo";

	@SuppressWarnings("unchecked")
	@Test
	public void queryOrder_商户信息查询() throws Exception{
		TreeMap<String, Object> reqPay = new TreeMap<String, Object>();
		
		/***********	参数校验	*********/
    	Assert.assertNotNull("参数缺失,服务商编号", EnvConfig.context.get(BusiConsts.acqSpId));
    	Assert.assertNotNull("参数缺失,报备编号", EnvConfig.context.get(BusiConsts.merId));
    	
    	reqPay.put("acqSpId",(String) EnvConfig.context.get(BusiConsts.acqSpId));//服务商编号	10	M	服务商编号
    	reqPay.put("merId",(String) EnvConfig.context.get(BusiConsts.merId));//报备编号	16	M	报备编号
		reqPay.put("signature", "");	
		
		//对请求报文做加签处理
		String reqpay = AddSign.addSign(reqPay);
		Map<String, Object> reqMap = JSONObject.parseObject(reqpay);
		
		try{
			//发送post请求
			String result = HttpUtilClient.doPostJson(queryUrl, new JSONObject(), reqMap);
			LogUtil.info("输出请求结果:"+result);

			//将响应报文转成map
			Map<String, Object> resMap = JSON.parseObject(result, TreeMap.class);
			String respCode = (String) resMap.get("respCode");
			if ("00".equals(respCode)) {
				String acqMerId = (String) resMap.get("acqMerId");
				EnvConfig.context.put(BusiConsts.acqMerId, acqMerId);
				Assert.assertTrue("商户信息查询成功", true);
			}else{
				String respMsg = (String) resMap.get("respMsg");
				Assert.assertTrue("商户信息查询失败：" + respMsg, false);
			}
		}catch (Exception e) {
			Assert.assertTrue("商户信息查询异常", false);
		}
	}

	public static void main(String args[]){
		try {
			new API_3商户信息查询().queryOrder_商户信息查询();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
