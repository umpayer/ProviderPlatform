package com.umpay.demo.step2_入网成功配置参数;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.umpay.util.AddSign;
import com.umpay.util.Base64;
import com.umpay.util.HttpUtilClient;

/**
 * @author: weijieming
 * @date:2019年8月21日 上午10:37:51
 * @类说明:查询电子协议接口
 * @产品号:
 */
public class API_8电子协议下载Demo  {

//    private String queryUrl = "http://106.120.215.230:8011/entry-provider-plat-client/merchants/queryElectronicAgreement";
    private String queryUrl = "http://10.10.56.130:8001/entry-provider-plat-client/merchants/queryElectronicAgreement";
//    private String queryUrl = "http://10.10.178.87:6904/merchants/queryElectronicAgreement";

    @SuppressWarnings("unchecked")
	@Test
    public void down_电子协议下载() throws Exception{
        TreeMap<String, Object> reqPay = new TreeMap<String, Object>();
    	/***********	参数校验	*********/
    	
    	reqPay.put("acqSpId","Y471790403");//服务商编号	10	M	服务商编号
    	reqPay.put("merId","M2019091000000714");//报备编号	16	M	报备编号
        reqPay.put("signature", "");

        //对请求报文做加签处理
        String reqpay = AddSign.addSign(reqPay);
        Map<String, Object> reqMap = JSONObject.parseObject(reqpay);

        try{
        	System.out.println("queryUrl"+queryUrl);
            //发送post请求
        	Date a=new Date();
            String result = HttpUtilClient.doPostJson(queryUrl, new JSONObject(), reqMap);
            Date b=new Date();
            
            System.out.println(a.getTimezoneOffset());
            System.out.println(b.getTimezoneOffset());
            //将响应报文转成map
            Map<String, Object> resMap = JSON.parseObject(result, TreeMap.class);
            String respCode = (String) resMap.get("respCode");
            String elecAgreement = (String) resMap.get("elecAgreement");
            System.out.println("respCode"+respCode);
            System.out.println("elecAgreement"+elecAgreement.length());
            
            byte[] data=Base64.decode(elecAgreement.getBytes());
            
            
            if ("00".equals(respCode)) {
                Assert.assertTrue("电子协议下载成功", true);
            }else{
                String respMsg = (String) resMap.get("respMsg");
                Assert.assertTrue("电子协议下载失败：" + respMsg, false);
            }
        }catch (Exception e) {
        	e.printStackTrace();
            Assert.assertTrue("电子协议下载异常", false);
        }
    }

    
    public static void main(String[] args) throws Exception {
        new API_8电子协议下载Demo().down_电子协议下载();
	}


}
