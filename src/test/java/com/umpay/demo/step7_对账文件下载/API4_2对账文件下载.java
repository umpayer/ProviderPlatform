package com.umpay.demo.step7_对账文件下载;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.umpay.call.BaseAPI;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.util.AddSign;
import com.umpay.util.HttpUtilClient;
import com.umpay.util.LogUtil;

public class API4_2对账文件下载 extends BaseAPI {

    public String queryUrl = EnvConfig.url + "pay/downloadSettleFile";

    @SuppressWarnings("unchecked")
    @Test
    public void orderClose_对账文件下载() throws Exception{
        TreeMap<String, Object> reqPay = new TreeMap<String, Object>();
        reqPay.put("acqSpId", "S145871705");//代理商编号	10	M	代理商编号(联动平台分配)
        reqPay.put("stlDate", "20190723");//对账日期

        //对请求报文做加签处理
        String reqpay = AddSign.addSign(reqPay);
        Map<String, Object> reqMap = JSONObject.toJavaObject(JSONObject.parseObject(reqpay), Map.class);

        try{
            //发送post请求
            String result = HttpUtilClient.doGet(queryUrl, new JSONObject(), reqMap);
            LogUtil.info("输出请求结果:"+result);

            //将响应报文转成map
            Map<String, Object> treeMap = JSON.parseObject(result, TreeMap.class);

            String respCode = (String) treeMap.get("respCode");
            if ("00".equals(respCode)) {
                org.junit.Assert.assertTrue("订单关闭成功", true);
            }else{
                String respMsg = (String) treeMap.get("respMsg");
                org.junit.Assert.assertTrue("订单关闭失败：" + respMsg, false);
            }
        }catch (Exception e) {
            org.junit.Assert.assertTrue("订单关闭异常", false);
        }
    }
}
