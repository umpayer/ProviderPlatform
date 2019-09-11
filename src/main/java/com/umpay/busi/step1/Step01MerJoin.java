package com.umpay.busi.step1;

import com.umpay.consts.BusiConsts;
import com.umpay.consts.BusiResEnum;
import com.umpay.consts.StepEnum;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.demo.step1_商户入网流程.API_1商户信息录入;
import com.umpay.tools.check.CheckUtil;
import com.umpay.tools.json.JsonResBuild;
import com.umpay.util.LogUtil;

public class Step01MerJoin {

	public static	JsonResBuild res=new JsonResBuild();
	public static	StepEnum step=StepEnum.MerJoin;
	
	@SuppressWarnings({ "finally", "unchecked" })
	public static String  step(String merchanttype,String acqSpId)  {
		 //1、准备工作检查
        try {
        	CheckUtil.CheckArgs(merchanttype,acqSpId);
			new EnvConfig().initConfig();
	        EnvConfig.context.clear();
	        EnvConfig.context.put(BusiConsts.acqSpId, acqSpId);
	        //商户类型：3、小微；1/2、企业/个体配置
//	        String merchantType = StringUtil.isEmpty(merchanttype)?ConfigUtil.getConfig("merchantType"):merchanttype;
	        LogUtil.info("商户入网请求参数"+merchanttype);
	        if ("3".equals(merchanttype)) {//小微
	            //1、2.1商户信息录入
	            new API_1商户信息录入().add_小微商户入网();
	            res.append(BusiResEnum.SUCCESS, "小微商户入网成功",step);
	            res.append(BusiConsts.merId, (String) EnvConfig.context.get("merId"));
	        }else {//企业/个体配置
	            //1、2.1商户信息录入
	            new API_1商户信息录入().add_企业个体商户入网();
	            res.append(BusiResEnum.SUCCESS, "企业个体商户入网成功",step);
	            res.append(BusiConsts.merId, (String) EnvConfig.context.get("merId"));
	        }
	        LogUtil.info("商户入网成功");
        } catch (Exception e) {
        	LogUtil.error(e.getMessage(), e);
			res.append(BusiResEnum.ERROR, e.getMessage(),step);
		}finally {
			LogUtil.info("商户入网调用结尾");
			return res.toJsonString();
		}
        
		
	}
}
