package com.umpay.demo.step3_收款交易;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
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
 * @author: zhangjing
 * @date:2019年8月6日 上午9:54:20
 * @类说明:主扫交易
 * @产品号:微信主扫，支付宝主扫，银联二维码主扫
 */
public class API3_3扫码支付_用户主扫 extends BaseAPI {
	public String payUrl = EnvConfig.url + "pay/qrpay";

	String orderNo ="";
	String transactionId = "";
	
	@SuppressWarnings("unchecked")
	@Test
	public void pay_扫码支付_用户主扫() throws Exception{
		TreeMap<String, Object> reqPay = new TreeMap<String, Object>();
		reqPay.put("orderTime",TimeUtil.datetime14());
		reqPay.put("acqSpId", EnvConfig.acqSpId);//代理商编号	10	M	代理商编号(联动平台分配)
		reqPay.put("acqMerId", acqMerId);//商户号	8	M	商户号(联动平台分配)
		reqPay.put("orderNo", Common.genOrderId());//商户订单号	64	M	商户的支付订单号
		reqPay.put("txnAmt", "1");//交易金额	13	M	是人民币，且以分为单位
		reqPay.put("orderType", "wechat");//订单类型	12	M alipay-支付宝,wechat-微信支付 ,unionpay-银联二维码
		reqPay.put("goodsInfo", "商品信息");//商品信息	128	O	可上送商品描述、商户订单号等信息，用户付款成功后会在微信账单页面展示
		reqPay.put("signature", "");
		
		//对请求报文做加签处理
		String reqpay = AddSign.addSign(reqPay);
		Map<String, Object> reqMap = JSONObject.toJavaObject(JSONObject.parseObject(reqpay), Map.class);
		
		try{
			//发送post请求
			String result = HttpUtilClient.doPostJson(payUrl, new JSONObject(), reqMap);
			LogUtil.info("result------------"+result);
			//LogUtil.info("对响应报文验签的结果是：" +AddSign.doCheckSign(result));
			//将响应报文转成Map类型
			Map<String, Object> treeMap = JSON.parseObject(result, TreeMap.class);
			//提取响应结果
			LogUtil.info(reqPay.get("orderType")+"生成主扫支付链接，点击支付链接开始支付："+treeMap.get("qrCode").toString());
			orderNo = treeMap.get("orderNo").toString();
			transactionId = treeMap.get("transactionId").toString();
			LogUtil.info("orderNo=" + orderNo);
			LogUtil.info("transactionId=" + transactionId);

			String respCode = (String) treeMap.get("respCode");
			if ("00".equals(respCode)) {
				Assert.assertTrue("扫码支付_用户主扫成功", true);
			}else{
				String respMsg = (String) treeMap.get("respMsg");
				Assert.assertTrue("扫码支付_用户主扫失败：" + respMsg, false);
			}
		}catch (Exception e) {
			Assert.assertTrue("扫码支付_用户主扫异常", false);
		}
	}

}
