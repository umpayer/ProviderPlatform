package com.umpay.consts;

import java.math.BigDecimal;

public class BusiConsts {
	//********************************返回常量值KEY*********************************************//
	public static final String RETCODE = "RetCode";//返回码
	public static final String RETMSG = "RetMsg";//返回描述
	public static final String EXRETMSG = "ExRetMsg";//异常/扩展返回描述
	public static final String STEPNAME = "stepName";//节点描述
	public static final String STEPPATH = "stepPath";//节点地址
	//********************************业务常量值*********************************************//
//	public static final String ysfMerId = "ysfMerId";//报备编号
	public static final String acqMerId = "acqMerId";//内部商户号
	public static final String auditStatus = "auditStatus";//审核状态
	public static final String acqSpId = "acqSpId";//代理商编号
	public static final String merId = "merId";//代理商编号
	public static final String nopassFlag = "nopassFlag";//审核通过与否
	public static final String merChantType = "merChantType";//商户类型
	public static final String transCaId = "transCaId";//事务ID
	public static final String verifyCode = "verifyCode";//验证码
	public static final String contId = "contId";//合同ID
	public static final String jsapiPath = "jsapiPath";//支付授权路径
	public static final String orderNo = "orderNo";//订单号
	public static final String refundOrderNo = "refundOrderNo";//退费订单号
	
	public static final String orderType = "orderType";//订单类型
	public static final String authCode = "authCode";//付款码
	public static final String qrCode = "qrCode";//二维码
	public static final String transactionId = "transactionId";//联动流水号
	public static final String txnAmt = "txnAmt";//金额
	public static final String origRespCode = "origRespCode";//金额
	public static final String origRespDescCode = "origRespDescCode";//金额
	public static final String RespCode = "respCode";//金额
	public static final String RespMsg = "respMsg";//金额
	//********************************业务标量值*********************************************//
	public static final String BUSI_FLAG_Y = "Y";	//业务标量值Y
	public static final String BUSI_FLAG_F1 = "-1";	//业务标量值-1
	public static final String BUSI_FLAG_0 = "0";	//业务标量值0
	public static final String BUSI_FLAG_1 = "1";	//业务标量值1
	public static final String BUSI_FLAG_2 = "2";	//业务标量值2
	public static final String BUSI_FLAG_3 = "3";	//业务标量值3
	public static final String BUSI_FLAG_4 = "4";	//业务标量值4
	public static final String BUSI_FLAG_5 = "5";	//业务标量值5
	public static final String BUSI_FLAG_6 = "6";	//业务标量值6
	public static final String BUSI_FLAG_7 = "7";	//业务标量值7
	public static final String BUSI_FLAG_8 = "8";	//业务标量值8
	public static final String BUSI_FLAG_9 = "9";	//业务标量值9
	public static final String BUSI_FLAG_10 = "10";	//业务标量值10
	public static final String BUSI_FLAG_00 = "00";	//业务标量值00
	public static final String BUSI_FLAG_01 = "01";	//业务标量值01
	public static final String BUSI_FLAG_02 = "02";	//业务标量值02
	public static final String BUSI_FLAG_03 = "03";	//业务标量值03
	public static final String BUSI_FLAG_04 = "04";	//业务标量值04
	public static final String BUSI_FLAG_05 = "05";	//业务标量值05
	public static final String BUSI_FLAG_06 = "06";	//业务标量值06
	public static final String BUSI_FLAG_07 = "07";	//业务标量值07
	public static final String BUSI_FLAG_08 = "08";	//业务标量值08
	public static final String BUSI_FLAG_09 = "09";	//业务标量值09
	public static final String BUSI_FLAG_EMPTY = "";	//业务标量值空字符串
	
	public static final Short BUSI_SHORT_FLAG_0 = 0;	//业务标量值0
	public static final Short BUSI_SHORT_FLAG_1 = 1;	//业务标量值1
	public static final Short BUSI_SHORT_FLAG_2 = 2;	//业务标量值2
	public static final Short BUSI_SHORT_FLAG_3 = 3;	//业务标量值3
	public static final double BUSI_DOUBLE_FLAG_0 = 0;	//业务标量值3
	
	public static final Integer BUSI_INTEGER_FLAG_F1 = -1;	//业务标量值-99
	public static final Integer BUSI_INTEGER_FLAG_F99 = -99;	//业务标量值-99
	public static final BigDecimal BUSI_BIGDECIMAL_FLAG_0 = BigDecimal.valueOf(0) ;	//业务标量值4
	public static final BigDecimal BUSI_BIGDECIMAL_FLAG_F99 = BigDecimal.valueOf(-99) ;	//业务标量值4
		
		
}
