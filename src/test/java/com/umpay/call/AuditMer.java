package com.umpay.call;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.umpay.consts.BusiConsts;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.util.AddSign;
import com.umpay.util.HttpUtilClient;
import com.umpay.util.LogUtil;
import com.umpay.util.TimeUtil;

/**
 * 审核商户开户接口测试类
 */
public class AuditMer {

    public String queryUrl ="http://10.10.178.87:7807/joinflow/JoinFlow";

    @SuppressWarnings("unchecked")
    @Test
    public void auditMer() throws Exception{
    	/***********	参数校验	*********/
    	Assert.assertNotNull("参数缺失,服务商编号", EnvConfig.context.get(BusiConsts.acqSpId));
    	Assert.assertNotNull("参数缺失,内部商户号编号", EnvConfig.context.get(BusiConsts.acqMerId));
    	Assert.assertNotNull("参数缺失,审核通过与否", EnvConfig.context.get(BusiConsts.nopassFlag));
    	Assert.assertNotNull("参数缺失,商户类型", EnvConfig.context.get(BusiConsts.merChantType));
        
    	TreeMap<String, Object> reqPay = new TreeMap<String, Object>();
        reqPay.put("acqMerId", EnvConfig.context.get(BusiConsts.acqMerId));
        reqPay.put("acqSpId", EnvConfig.context.get(BusiConsts.acqSpId));
        reqPay.put("funCode", "ALIVE");

        reqPay.put("nopassFlag", EnvConfig.context.get(BusiConsts.nopassFlag));
        reqPay.put("rpid", TimeUtil.date("yyyyMMddHHmmSSSSS"));
        reqPay.put("merChantType", EnvConfig.context.get(BusiConsts.merChantType));
        reqPay.put("nopassid", "");
        reqPay.put("nopassinfo", "");
        reqPay.put("userName", "");

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
            if ("0000".equals(respCode)) {
                //开户成功
                Assert.assertTrue("审核商户开户成功", true);
            }else{
                //开户失败
                String respMsg = (String) resMap.get("respMsg");
                Assert.assertTrue("审核商户开户失败：" + respMsg, false);
            }
        }catch (Exception e) {
            Assert.assertTrue("审核商户开户异常", false);
        }


    }
}
