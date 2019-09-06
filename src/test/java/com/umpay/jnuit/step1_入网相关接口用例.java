package com.umpay.jnuit;

import org.junit.Test;

import com.umpay.call.AuditMer;
import com.umpay.call.VerifyCode;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.demo.step1_商户入网流程.API_1商户信息录入;
import com.umpay.demo.step1_商户入网流程.API_2资质上传接口;
import com.umpay.demo.step1_商户入网流程.API_6_7电子签约;
import com.umpay.demo.step2_入网成功配置参数.API_3商户信息查询;
import com.umpay.demo.step2_入网成功配置参数.API_8电子协议下载;
import com.umpay.util.ConfigUtil;
import com.umpay.util.LogUtil;

/**
 * 入网整体流程测试用例执行
 */
public class step1_入网相关接口用例 {

    /**
     * 入网相关接口用例
     * 1、准备工作检查
     * 2、调用商户入网流程
     *  2.1、入网成功调用商户审核流程
     * 3、调用入网成功配置参数
     * @throws Exception
     */
    @Test
    public void test_商户入网() throws Exception {
        //1、准备工作检查
        new EnvConfig().initConfig();
        EnvConfig.context.clear();

        //2、调用商户入网流程
        callMerNetIn();

        //2.1、入网成功调用商户审核流程
        //TODO 联系联动审核该入网商户
        new API_3商户信息查询().queryOrder_商户信息查询();
        new AuditMer().auditMer();

        //3、调用入网成功配置参数
        callConfig();
    }

    /**
     * 调用商户入网流程
     * 1、2.1商户信息录入
     * 2、2.2资质上传接口
     * 3、2.6获取电子签约挑战码
     * 4、2.7电子签约确认
     * 5、模拟调用商户审核接口
     */
    private void callMerNetIn() throws Exception {
        //商户类型：3、小微；1/2、企业/个体配置
        String merchantType = ConfigUtil.getConfig("merchantType");

        if ("3".equals(merchantType)) {//小微
            //1、2.1商户信息录入
            new API_1商户信息录入().add_小微商户入网();

            //2、2.2资质上传接口
            new API_2资质上传接口().test_小微();

        }else {//企业/个体配置
            //1、2.1商户信息录入
            new API_1商户信息录入().add_企业个体商户入网();

            //2、2.2资质上传接口
            new API_2资质上传接口().test_个体_企业商户();

        }
        Thread.sleep(2000);
        //3、2.6获取电子签约挑战码
        new API_6_7电子签约().verifyCode_获取电子签约挑战码();

        //TODO 查询签约挑战码
        new VerifyCode().getVerifyCode();
        
        //4、2.7电子签约确认
        new API_6_7电子签约().verifyCode_电子签约确认();
    }

    /**
     * 调用入网成功配置参数
     * 1、2.3商户信息查询
     * 2、2.4微信参数配置-支付授权目录
     * 3、2.5微信参数配置-子商户appid
     * 4、2.8电子协议下载
     */
    private void callConfig() throws Exception {
        //1、2.3商户信息查询
        new API_3商户信息查询().queryOrder_商户信息查询();

        //2、2.4微信参数配置-支付授权目录
//        new API_4微信参数配置_支付授权目录().config_支付授权目录();

        //3、2.5微信参数配置-子商户appid
//        new API_5微信参数配置_子商户appid().config_子商户appid();

        //4、2.8电子协议下载
        new API_8电子协议下载().down_电子协议下载();

        LogUtil.info("success=========================================================");
    }

}
