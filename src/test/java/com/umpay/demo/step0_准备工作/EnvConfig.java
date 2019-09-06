package com.umpay.demo.step0_准备工作;

import com.umpay.util.ConfigUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 环境配置
 */
public class EnvConfig {

    public static final String url = ConfigUtil.getConfig("umpayUrl");
    public static final String acqSpId = ConfigUtil.getConfig("acqSpId");
    public static final String signMobileNo = ConfigUtil.getConfig("signMobileNo");

    public static final String filePath_资质 = ConfigUtil.getConfig("filePath");

    public static Map context = new HashMap();

    static{
        /** TODO 不依赖上下文调试时在此处赋值 */
        context.put("merId", "M2019090400000478");//报备编号M2019082000000270
        context.put("verifyCode", "098500");//挑战码
        context.put("acqMerId", "42516662");//商户号
//        context.put("orderNo", "JD201909030945440001");//订单号
//        context.put("refundOrderNo", "JD201909030945440001");//退费订单号
    }

    /**
     * 初始化配置
     * 1、设置服务器地址url
     * 2、配置商户私钥
     * 3、配置联动公钥
     * @throws Exception
     */
    @Test
    public void initConfig() throws Exception {
        //TODO 修改config/dev.properties文件umpayUrl参数以及其它参数
        //配置好地址后更改为:true
        boolean urlFlag = true;
        //1、设置服务器地址url
        Assert.assertTrue("请配置调用服务器地址", urlFlag);

        //TODO 在cert文件下导入商户私钥
        //配置好地址后更改为:true
        boolean privateKeyFlag = true;
        //2、配置商户私钥
        Assert.assertTrue("请配置商户私钥", privateKeyFlag);

        //TODO 在cert文件下导入联动公钥
        //配置好地址后更改为:true
        boolean serverCertFlag = true;
        //3、配置联动公钥
        Assert.assertTrue("配置联动公钥", serverCertFlag);


    }
}
