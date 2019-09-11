package com.umpay.busi.step1;

import com.umpay.consts.BusiConsts;
import com.umpay.consts.BusiResEnum;
import com.umpay.consts.StepEnum;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.demo.step1_商户入网流程.API_2资质上传接口;
import com.umpay.tools.check.CheckUtil;
import com.umpay.tools.json.JsonResBuild;
import com.umpay.util.LogUtil;

public class Step02QualificationUpload {
	public static	JsonResBuild res=new JsonResBuild();
	public static	StepEnum step=StepEnum.QualificationUpload;
	
	@SuppressWarnings({ "finally", "unchecked" })
	public static String  step(String acqSpId,String ysfMerId,String merchanttype)  {
		 //1、准备工作检查
        try {
        	CheckUtil.CheckArgs(acqSpId,ysfMerId,merchanttype);
			new EnvConfig().initConfig();
	        EnvConfig.context.clear();
//	        EnvConfig.acqSpId=acqSpId;
	        EnvConfig.context.put(BusiConsts.acqSpId, acqSpId);
	        EnvConfig.context.put(BusiConsts.merId, ysfMerId);
	        //商户类型：3、小微；1/2、企业/个体配置
//	        String merchantType = StringUtil.isEmpty(merchanttype)?ConfigUtil.getConfig("merchantType"):merchanttype;
	
	        if ("3".equals(merchanttype)) {//小微
	        	//2、2.2资质上传接口
	            new API_2资质上传接口().test_小微();
	            res.append(BusiResEnum.SUCCESS, "小微资质信息上传成功",step);
	            res.append(BusiConsts.merId, (String) EnvConfig.context.get("merId"));
	        }else {//企业/个体配置
	        	//2、2.2资质上传接口
	            new API_2资质上传接口().test_个体_企业商户();
	            res.append(BusiResEnum.SUCCESS, "企业个体资质信息上传成功",step);
	            res.append(BusiConsts.merId, (String) EnvConfig.context.get("merId"));
	        }
	        LogUtil.info(step.getFlowLoggerName()+"节点处理成功");
        } catch (Exception e) {
        	LogUtil.error(e.getMessage(), e);
			res.append(BusiResEnum.ERROR, e.getMessage(),step);
		}finally {
			return res.toJsonString();
		}
        
		
	}
}
