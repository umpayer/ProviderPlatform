package com.umpay.demo.step3_收款交易;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
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
import com.umpay.util.LogUtil;
import com.umpay.util.TimeUtil;

/**
 * @author: zhangjing
 * @date:2019年8月6日 下午12:51:08
 * @类说明:被扫交易
 * @产品号:微信被扫，支付宝被扫，银联二维码被扫
 */
public class API3_2刷卡支付_用户被扫 extends BaseAPI {

	public String payUrl = EnvConfig.url + "pay/micropay";

	@Test
	public void pay_刷卡支付_用户被扫() throws UnsupportedEncodingException, GeneralSecurityException, IOException{
		TreeMap<String, Object> reqPay = new TreeMap<String, Object>();
		String orderNo = Common.genOrderId();
		reqPay.put("orderTime",TimeUtil.datetime14());
		reqPay.put("acqSpId", EnvConfig.acqSpId);//代理商编号	10	M	代理商编号(联动平台分配)
//		reqPay.put("acqMerId", "41509208");//商户号	8	M	商户号(联动平台分配)
		reqPay.put("acqMerId", acqMerId);
		reqPay.put("orderNo", orderNo);//商户订单号	64	M	商户的支付订单号
		reqPay.put("txnAmt", "1");//交易金额	13	M	是人民币，且以分为单位
		reqPay.put("orderType", "wechat");//订单类型	12	M alipay-支付宝,wechat-微信支付 ,unionpay-银联二维码
//		reqPay.put("goodsInfo", "aaa");//商品信息	128	O	可上送商品描述、商户订单号等信息，用户付款成功后会在微信账单页面展示
		reqPay.put("authCode", "280389411343685293");//付款码	32	M	05:微信刷卡：以10~15开头的长度18位的数字,06:支付宝条码：以25~30开头的长度为16~24位的数字,09:银联二维码：以62开头长度19位数字
//		reqPay.put("goodsId", "123");//商品ID	16	M	商品ID
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
				Assert.assertTrue("刷卡支付_用户被扫成功", true);
			}else{
				String respMsg = (String) resMap.get("respMsg");
				Assert.assertTrue("刷卡支付_用户被扫失败：" + respMsg, false);
			}
		}catch (Exception e) {
			Assert.assertTrue("刷卡支付_用户被扫异常", false);
		}
	}
	
}
