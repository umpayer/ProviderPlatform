package com.umpay.demo.step1_商户入网流程;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.util.AddSign;
import com.umpay.util.HttpUtilClient;
import com.umpay.util.LogUtil;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author: zhangjing
 * @date:2019年8月5日 下午3:06:59
 * @类说明:商户信息录入
 * @产品号:
 */

public class API_1商户信息录入 {
	private static final Logger log = LoggerFactory.getLogger(API_1商户信息录入.class);
	private static final String PATH = EnvConfig.url + "merchants/apply";
	
	

	@SuppressWarnings("unchecked")
	@Test
	public void add_小微商户入网() throws Exception{
	
		TreeMap<String, Object> reqMer = new TreeMap<String, Object>();
		TreeMap<String, Object> reqPaper = new TreeMap<String, Object>();
		TreeMap<String, Object> reqRate = new TreeMap<String, Object>();
		TreeMap<String, Object> reqbankCardRateLevel1 = new TreeMap<String, Object>();
		TreeMap<String, Object> reqbankCardRateLevel2 = new TreeMap<String, Object>();
		
		Assert.assertNotNull("参数缺失,服务商编号", EnvConfig.context.get("acqSpId"));
		//微信：208493420 支付宝：270729587
		reqMer.put("acqSpId", EnvConfig.context.get("acqSpId"));//服务商编号	10	M	服务商编号(联动平台分配)Y471790403有D0
		reqMer.put("merchantName", "自然人测试商户2");//商户简称	16	M	商户交易显示名称 
		reqMer.put("paper", reqPaper);//商户详细信息		M	json 格式字符串
		reqMer.put("rate", reqRate);//手续费费率		M	json 格式字符串
		reqMer.put("wechatChannelId", "208493420");//服务商微信渠道号	64	M	微信报备的服务商渠道号
		reqMer.put("alipayChannelId", "2088901023449763");//服务商支付宝渠道号	64	M	支付宝报备的服务商渠道号
		reqMer.put("signature", "");//
		reqMer.put("reqDate", "20190815");//
		reqMer.put("reqTime", "120000");//
		//paper
		reqPaper.put("merchantType", "3");//商户类型	1	M	1:个体 2:企业 3:小微
		//reqPaper.put("businessLicenseCode", "911306056665997504");//营业执照编号	32	C	merFlag= 1、2必传
		//reqPaper.put("businessLicenseName", "保定市浪味仙餐饮有限公司");//商户经营名称	64	C	merFlag= 1、2必传
		//reqPaper.put("businessLicenseExpired", "20270920");//营业执照有效期	8	C	merFlag= 1、2必传
		reqPaper.put("industryCategoryId", "5411");//经营类目	4	M	参见附件一
		reqPaper.put("businessAddress", "海淀区学院路");//所属地址详细地址	256	M
		reqPaper.put("province", "0010");//所属地址省	4	M	0010
		reqPaper.put("city", "1000");//city	所属地址市	4	M	1000
		reqPaper.put("area", "1021");//所属地址区	4	M	merFlag= 1、2必传 1026
		//reqPaper.put("lawyerName", "王贝贝");//法人姓名	32	C	merFlag= 1、2必传
		//reqPaper.put("lawyerCertType", "1");//法人证件类型	1	C	1：身份证
		//reqPaper.put("lawyerCertNo", "412701198707292019");//法人证件号	20	C	merFlag= 1、2必传
		reqPaper.put("contactPerson", "王贝贝");//联系人姓名	32	Y	
		reqPaper.put("contactPersonId", "412701198707292019");//联系人身份证	32	Y	
		reqPaper.put("contactPhone", "13811112222");//联系人手机号	11	Y 13811112222
		reqPaper.put("serviceTel", "01088659979");//客服电话	12	Y	
		reqPaper.put("email", "ww@163.com");//邮箱	64	Y
		reqPaper.put("settleAccountType", "2");//结算账户类型	1	Y	1:对公账户 2:法人账户 merFlag=3时必填2
		reqPaper.put("settleAccountNo", "6228510503221856");//结算账号	30	Y 对私6212260200051558001  贷记卡4270200000000001 ， 对公453359213570
		reqPaper.put("settleAccount", "王贝贝");//结算户名	256	Y 吕倩
		reqPaper.put("settlePeriod", "2");//结算周期	1	Y	1:T1 2:D0 3:T0 4:D1
		reqPaper.put("settleIdNo", "412701198707292019");//身份证号	18	Y	 513436200008027310
		reqPaper.put("openBank", "南京银行");//开户银行名称	4	Y	参见附件二
		reqPaper.put("openSubBank", "北京银行");//开户支行名称	64	Y
		reqPaper.put("signType", "1");//1-开通电子签章
		reqPaper.put("webSite", "http://www.wang163.com");
		reqPaper.put("signMobileNo", EnvConfig.signMobileNo);
		reqPaper.put("signCertNo", "412701198707292019");
		reqPaper.put("appName", "王贝贝的APP");
		reqPaper.put("signName", "王贝贝");
		reqPaper.put("settleIdEffective", "20140101");
		reqPaper.put("settleIdExpired", "永久");
		//rate
		reqRate.put("feeRateAlipay", "0.51");//支付宝费率	
		reqRate.put("feeRateWechatpay", "0.52");//微信费率	
		reqRate.put("bankCardRateLevel1", reqbankCardRateLevel1);//银行卡费率一档	
		reqRate.put("bankCardRateLevel2", reqbankCardRateLevel2);//银行卡费率二档	
		reqRate.put("feeRateD0", "0.04");//D0手续费	
		reqRate.put("feeRateWithdraw", "1000");//提现手续费	
		//bankCardRateLevel1
		reqbankCardRateLevel1.put("feeRateUnionpayDebit", "0.5001");//银联手续费率(借记)	
		reqbankCardRateLevel1.put("feeRateUnionpayDebitCap", "2000");//银联手续费率(借记封顶)		 
		reqbankCardRateLevel1.put("feeRateUnionpayCredit", "0.5201");//银联手续费率(贷记)	
		//bankCardRateLevel2
		reqbankCardRateLevel2.put("feeRateUnionpayDebit", "0.60");//银联手续费率(借记)	
		reqbankCardRateLevel2.put("feeRateUnionpayDebitCap", "3000");//银联手续费率(借记封顶)
		reqbankCardRateLevel2.put("feeRateUnionpayCredit", "0.62");//银联手续费率(贷记)
		
		
		//对请求报文做加签处理
		String reqMerinfo = AddSign.addSign(reqMer);
		Map<String, Object> reqMap = JSONObject.parseObject(reqMerinfo);

		try{
			//发送post请求
			String result = HttpUtilClient.doPostJson(PATH, new JSONObject(), reqMap);
			LogUtil.info("输出请求结果:"+ result.toString());
			Map resMap = new Gson().fromJson(result, Map.class);
			String respCode = (String) resMap.get("respCode");
			if ("00".equals(respCode)) {
				String merId = (String) resMap.get("merId");
				EnvConfig.context.put("merId", merId);
				//开户成功
				Assert.assertTrue("小微商户入网开户成功", true);
			}else{
				//开户失败
				String respMsg = (String) resMap.get("respMsg");
				Assert.assertTrue("小微商户入网开户失败：" + respMsg, false);
			}
		}catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue("小微商户入网开户异常", false);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void add_企业个体商户入网() throws Exception{
		TreeMap<String, Object> reqMer = new TreeMap<String, Object>();
		TreeMap<String, Object> reqPaper = new TreeMap<String, Object>();
		TreeMap<String, Object> reqRate = new TreeMap<String, Object>();
		TreeMap<String, Object> reqbankCardRateLevel1 = new TreeMap<String, Object>();
		TreeMap<String, Object> reqbankCardRateLevel2 = new TreeMap<String, Object>();
		
		LogUtil.info("企业个体商户入网参数,服务商编号"+EnvConfig.context.get("acqSpId"));
		Assert.assertNotNull("参数缺失,服务商编号", EnvConfig.context.get("acqSpId"));
		//微信：208493420 支付宝：270729587
		reqMer.put("acqSpId", EnvConfig.context.get("acqSpId"));//服务商编号	10	M	服务商编号(联动平台分配)Y471790403有D0
		reqMer.put("merchantName", "企业renjie测试商户3");//商户简称	16	M	商户交易显示名称 
		reqMer.put("paper", reqPaper);//商户详细信息		M	json 格式字符串
		reqMer.put("rate", reqRate);//手续费费率		M	json 格式字符串
		reqMer.put("wechatChannelId", "208493420");//服务商微信渠道号	64	M	微信报备的服务商渠道号
		reqMer.put("alipayChannelId", "2088901023449763");//服务商支付宝渠道号	64	M	支付宝报备的服务商渠道号
		reqMer.put("signature", "");//
		reqMer.put("reqDate", "20190815");//
		reqMer.put("reqTime", "120000");//

		//paper
		reqPaper.put("merchantType", "2");//商户类型	1	M	1:个体 2:企业 3:小微
		reqPaper.put("businessLicenseCode", "911306056665997504");//营业执照编号	32	C	merFlag= 1、2必传
		reqPaper.put("businessLicenseName", "保定市浪味仙餐饮有限公司");//商户经营名称	64	C	merFlag= 1、2必传
		reqPaper.put("businessLicenseEffective", "20070920");//营业执照生效期	8	C	merFlag= 1、2必传
		reqPaper.put("businessLicenseExpired", "20270920");//营业执照有效期	8	C	merFlag= 1、2必传
		reqPaper.put("industryCategoryId", "5411");//经营类目	4	M	参见附件一
		reqPaper.put("businessAddress", "海淀区学院路");//所属地址详细地址	256	M
		reqPaper.put("province", "0010");//所属地址省	4	M	0010
		reqPaper.put("city", "1000");//city	所属地址市	4	M	1000
		reqPaper.put("area", "1021");//所属地址区	4	M	merFlag= 1、2必传 1026
		reqPaper.put("lawyerName", "王贝贝");//法人姓名	32	C	merFlag= 1、2必传
		reqPaper.put("lawyerCertType", "1");//法人证件类型	1	C	1：身份证
		reqPaper.put("lawyerCertNo", "412701198707292019");//法人证件号	20	C	merFlag= 1、2必传
		reqPaper.put("contactPerson", "王贝贝");//联系人姓名	32	Y	
		reqPaper.put("contactPersonId", "412701198707292019");//联系人身份证	32	Y	
		reqPaper.put("contactPhone", "13811112222");//联系人手机号	11	Y 13811112222
		reqPaper.put("serviceTel", "01088659979");//客服电话	12	Y	
		reqPaper.put("email", "ww@163.com");//邮箱	64	Y
		reqPaper.put("settleAccountType", "2");//结算账户类型	1	Y	1:对公账户 2:法人账户 merFlag=3时必填2
		reqPaper.put("settleAccountNo", "6228510503221856");//结算账号	30	Y 对私6212260200051558001  贷记卡4270200000000001 ， 对公453359213570
		reqPaper.put("settleAccount", "王贝贝");//结算户名	256	Y 吕倩
		reqPaper.put("settlePeriod", "2");//结算周期	1	Y	1:T1 2:D0 3:T0 4:D1
		reqPaper.put("settleIdNo", "412701198707292019");//身份证号	18	Y	 513436200008027310
		reqPaper.put("openBank", "北京银行");//开户银行名称	4	Y	参见附件二
		reqPaper.put("openSubBank", "北京银行");//开户支行名称	64	Y
		
		reqPaper.put("signType", "1");//1-开通电子签章
		reqPaper.put("webSite", "http://www.wang163.com");
		reqPaper.put("signMobileNo", EnvConfig.signMobileNo);
		reqPaper.put("signCertNo", "412701198707292019");
		reqPaper.put("appName", "王贝贝的APP");
		reqPaper.put("signName", "王贝贝");
		reqPaper.put("settleIdEffective", "20140101");
		reqPaper.put("settleIdExpired", "永久");
		reqPaper.put("certNoEffective", "20140101");
		reqPaper.put("certNoExpired", "永久");
		//rate
		reqRate.put("feeRateAlipay", "0.51");//支付宝费率	
		reqRate.put("feeRateWechatpay", "0.52");//微信费率	
		//reqRate.put("bankCardRateLevel1", reqbankCardRateLevel1);//银行卡费率一档	
		//reqRate.put("bankCardRateLevel2", reqbankCardRateLevel2);//银行卡费率二档	
		reqRate.put("feeRateD0", "0.04");//D0手续费	
		reqRate.put("feeRateWithdraw", "1000");//提现手续费	
		//bankCardRateLevel1
		reqbankCardRateLevel1.put("feeRateUnionpayDebit", "0.50");//银联手续费率(借记)	
		reqbankCardRateLevel1.put("feeRateUnionpayDebitCap", "2000");//银联手续费率(借记封顶)		 
		reqbankCardRateLevel1.put("feeRateUnionpayCredit", "0.52");//银联手续费率(贷记)	
		//bankCardRateLevel2
		reqbankCardRateLevel2.put("feeRateUnionpayDebit", "0.60");//银联手续费率(借记)	
		reqbankCardRateLevel2.put("feeRateUnionpayDebitCap", "3000");//银联手续费率(借记封顶)
		reqbankCardRateLevel2.put("feeRateUnionpayCredit", "0.62");//银联手续费率(贷记)
		
        LogUtil.info("企业个体商户入网参数配置成功");
		//对请求报文做加签处理
        try{
        	String reqMerinfo = AddSign.addSign(reqMer);
        	Map<String, Object> reqMap = JSONObject.parseObject(reqMerinfo);

			LogUtil.info("输出请求地址:"+ PATH);
			//发送post请求
			String result = HttpUtilClient.doPostJson(PATH, new JSONObject(), reqMap);
			LogUtil.info("输出请求结果:"+ result.toString());
			Map resMap = new Gson().fromJson(result, Map.class);
			String respCode = (String) resMap.get("respCode");
			if ("00".equals(respCode)) {
				String merId = (String) resMap.get("merId");
				EnvConfig.context.put("merId", merId);
				//开户成功
				Assert.assertTrue("企业个体商户入网开户成功", true);
			}else{
				//开户失败
				String respMsg = (String) resMap.get("respMsg");
				Assert.assertTrue("企业个体商户入网开户失败：" + respMsg, false);
			}
		}catch (Exception e) {
			LogUtil.error(e.getMessage(),e);
			Assert.assertTrue("企业个体商户入网开户异常", false);
		}
	}
	
}
