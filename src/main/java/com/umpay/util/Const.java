package com.umpay.util;

import java.io.File;

public class Const {
	public static final String ENV = "env";// 线上、UAT、测试环境选择
	public static final String ENABLED = "enabled";// 用例是否加入自动化
	public static final String CASETYPE = "caseType";// 用例级别

	public static final String XMLFILENAMEKEY = "xmlFileName";// 运行suit的key

	/** 对账任务名 **/
	public static final String COL_ID = "stdPay4.0";
	/** 对账任务名 **/
	public static final String COL_IDSPLIT = "stdPay4.0Split";
	/** 对账文件下载路径 **/
	public static final String SETTLE_PATH = "settle_path";
	public static final String REQ_FRONT_PAGE_PAY_WEB2JSP = "web2_0.jsp";// web收银台模拟页面
	public static final String ATDPAY = "/usr/mpsp/collate3/admin.sh stdPay4.0 -d ";// 对账stdPay4.0
	public static final String ATDPAYSPLIT = "/usr/mpsp/collate3/admin.sh stdPay4.0Split -d ";// 分账对账stdPay4.0Split
	public static final String ATDPAYDir = "/AppData/mercheck/all/";// 对账文件生成路径

	static File directory = new File("");// 设定为当前文件夹
	public final static String SEPARATOR = System.getProperty("file.separator");
	public static String dataSourse = directory.getAbsolutePath() + SEPARATOR + "datasource" + SEPARATOR;
	public static String downloadDir = dataSourse + "download" + SEPARATOR;
}