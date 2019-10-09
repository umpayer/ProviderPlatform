package com.umpay.consts;

import com.umpay.util.StringUtil;

public enum StepEnum {
	
	MerJoin("0001","商户信息录入节点","MerJoin","【商户信息录入】","merchants/apply"),
	QualificationUpload("0002","资质信息上传节点","QualificationUpload","【资质信息上传】","upload/qualification"),
	InitialSign("0003","获取短信验证码","InitialSign","【获取短信验证码】","sign/getVerifyCode"),
	QurayContId("0004","查询合同编号","QurayContId","【查询合同编号】","DB"),
	QurayVerifyCode("0005","查询验证码","QurayVerifyCode","【查询验证码】","DB"),
	FinalSign("0006","电子签约确认","FinalSign","【电子签约确认】","sign/doVerifyCodeSign"),
	QueryMerInfo("0007","商户信息查询","QueryMerInfo","【商户信息查询】","merchants/queryMerchantInfo"),
	AuditMer("0008","商户信息审核","AuditMer","【商户信息审核】","JoinFlow/JoinFlow"),
	DownPdf("0009","电子协议下载","DownPdf","【电子协议下载】","merchants/queryElectronicAgreement"),
	ConfigPayDir("0010","微信支付授权目录配置","ConfigPayDir","【微信支付授权目录配置】","merchants/jsapiPath"),
	ConfigSubAppId("0011","微信子商户appid配置","ConfigSubAppId","【微信子商户appid配置】","merchants/subAppid"),

	UnifiedOrder("0021","公众号小程序支付","UnifiedOrder","【公众号小程序支付】","pay/unifiedOrder"),
	MicroPay("0022","用户被扫刷卡支付","MicroPay","【用户被扫刷卡支付】","pay/micropay"),
	QrPay("0023","用户主扫扫码支付","QrPay","【用户主扫扫码支付】","pay/qrpay"),
	QueryOrder("0024","订单状态同步","QueryOrder","【订单状态同步】","pay/queryOrder"),
	
	CancelOrder("0025","订单撤销","CancelOrder","【订单撤销】","pay/cancelOrder"),
	RefundOrder("0026","订单退费","RefundOrder","【订单退费】","pay/refund"),
	QueryRefundOrder("0027","订单退费查询","QueryRefundOrder","【订单退费查询】","pay/queryOrder"),
	CloseOrder("0028","关闭订单","CloseOrder","【关闭订单】","pay/queryOrder"),
	SettleFileDown("0029","对账文件下载","SettleFileDown","【对账文件下载】","pay/queryOrder"),
	
	QurayYsfMerInfo("3001","查询商户信息","QurayYsfMerInfo","【查询商户信息】","DB"),
	UpdateYsfMerInfo("3002","更新商户信息","UpdateYsfMerInfo","【更新商户信息】","DB"),

	
	Other("9999","其他节点","Other","【其他节点】","其他");
	private String flowId;//流程编号
	private String flowDesc;//流程描述
	private String flowName;//流程名称标识
	private String flowLoggerName;//流程日志打印标识
	private String flowPath;//流程日志打印标识
	
	public static StepEnum selectEnumByFlowDesc(String flowDesc){
		if(StringUtil.isEmpty(flowDesc))return Other;
		for (StepEnum ose : StepEnum.values()) {
			if(ose.getFlowDesc().equals(flowDesc)){
				return ose;
			}
		}
		return Other;
	}
	
	private StepEnum(String flowId, String flowDesc, String flowName, String flowLoggerName, String flowPath) {
		this.flowId = flowId;
		this.flowDesc = flowDesc;
		this.flowName = flowName;
		this.flowLoggerName = flowLoggerName;
		this.flowPath = flowPath;
	}
	
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public String getFlowDesc() {
		return flowDesc;
	}
	public void setFlowDesc(String flowDesc) {
		this.flowDesc = flowDesc;
	}
	public String getFlowName() {
		return flowName;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getFlowLoggerName() {
		return flowLoggerName;
	}

	public void setFlowLoggerName(String flowLoggerName) {
		this.flowLoggerName = flowLoggerName;
	}

	public String getFlowPath() {
		return flowPath;
	}

	public void setFlowPath(String flowPath) {
		this.flowPath = flowPath;
	}
}
