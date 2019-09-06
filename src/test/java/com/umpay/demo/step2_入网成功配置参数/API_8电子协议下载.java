package com.umpay.demo.step2_入网成功配置参数;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.umpay.call.BaseAPI;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.util.AddSign;
import com.umpay.util.HttpUtilClient;
import com.umpay.util.LogUtil;

/**
 * @author: weijieming
 * @date:2019年8月21日 上午10:37:51
 * @类说明:查询电子协议接口
 * @产品号:
 */
public class API_8电子协议下载 extends BaseAPI {

    private String queryUrl = EnvConfig.url + "merchants/queryElectronicAgreement";

    @Test
    public void down_电子协议下载() throws Exception{
        TreeMap<String, Object> reqPay = new TreeMap<String, Object>();
        reqPay.put("acqSpId", EnvConfig.acqSpId);//代理商编号	10	M	代理商编号(联动平台分配)
        reqPay.put("merId", merId);//报备编号	17	M
        reqPay.put("signature", "");

        //对请求报文做加签处理
        String reqpay = AddSign.addSign(reqPay);
        Map<String, Object> reqMap = JSONObject.parseObject(reqpay);

        try{
            //发送post请求
            String result = HttpUtilClient.doPostJson(queryUrl, new JSONObject(), reqMap);
//            LogUtil.info("输出请求结果:"+result);

            //将响应报文转成map
            Map<String, Object> resMap = JSON.parseObject(result, TreeMap.class);
            String respCode = (String) resMap.get("respCode");
            if ("00".equals(respCode)) {
                Assert.assertTrue("电子协议下载成功", true);
            }else{
                String respMsg = (String) resMap.get("respMsg");
                Assert.assertTrue("电子协议下载失败：" + respMsg, false);
            }
        }catch (Exception e) {
            Assert.assertTrue("电子协议下载异常", false);
        }
    }



}
