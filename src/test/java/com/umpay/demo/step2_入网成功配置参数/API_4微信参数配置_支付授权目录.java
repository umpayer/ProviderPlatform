package com.umpay.demo.step2_入网成功配置参数;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
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
import com.umpay.util.Common;
import com.umpay.util.LogUtil;

/**
 * 微信参数配置-支付授权目录
 * @author: zhangjing
 * @date:2019年8月6日 下午4:50:15
 * @类说明:
 * @产品号:
 */
public class API_4微信参数配置_支付授权目录 extends BaseAPI {
	private String payUrl = EnvConfig.url + "merchants/jsApiPath";

	@Test
	public void config_支付授权目录() throws UnsupportedEncodingException, GeneralSecurityException, IOException{
		TreeMap<String, Object> reqPay = new TreeMap<String, Object>();
		/***********	参数校验	*********/
    	Assert.assertNotNull("参数缺失,服务商编号", EnvConfig.context.get(BusiConsts.acqSpId));
    	Assert.assertNotNull("参数缺失,报备编号", EnvConfig.context.get(BusiConsts.acqMerId));
    	
    	reqPay.put("acqSpId",(String) EnvConfig.context.get(BusiConsts.acqSpId));//服务商编号	10	M	服务商编号
    	reqPay.put("acqMerId",(String) EnvConfig.context.get(BusiConsts.acqMerId));
		reqPay.put("jsapiPath", "abc");//支付授权目录
		reqPay.put("signature", "");	

		String reqpay = AddSign.addSign(reqPay);
		JSONObject jsonObject1 =JSONObject.parseObject(reqpay);

		try{
			//发送post请求
			String result = Common.runJsonPost(payUrl, jsonObject1,"UTF-8");
			LogUtil.info("输出请求结果:"+result);

			//将响应报文转成map
			Map<String, Object> resMap = JSON.parseObject(result, TreeMap.class);
			String respCode = (String) resMap.get("respCode");
			if ("00".equals(respCode)) {
				Assert.assertTrue("微信参数配置_支付授权目录成功", true);
			}else{
				String respMsg = (String) resMap.get("respMsg");
				Assert.assertTrue("微信参数配置_支付授权目录失败：" + respMsg, false);
			}
		}catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("微信参数配置_支付授权目录异常", e);
			Assert.assertTrue("微信参数配置_支付授权目录异常", false);
		}
	}
	
	public static void main(String[] args) throws Exception {
	    	  //1、准备工作检查
	        new EnvConfig().initConfig();
	        EnvConfig.context.clear();
	        EnvConfig.context.put(BusiConsts.acqSpId, "Y471790403");
	        EnvConfig.context.put(BusiConsts.acqMerId, "42517305");
	        EnvConfig.context.put(BusiConsts.jsapiPath, "");
	        //
	        new API_4微信参数配置_支付授权目录().config_支付授权目录();
	}
}
