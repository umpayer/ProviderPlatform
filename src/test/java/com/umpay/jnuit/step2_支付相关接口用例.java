package com.umpay.jnuit;

import com.umpay.demo.step3_收款交易.API3_1公众号小程序支付;
import com.umpay.demo.step3_收款交易.API3_2刷卡支付_用户被扫;
import com.umpay.demo.step3_收款交易.API3_3扫码支付_用户主扫;
import com.umpay.demo.step4_交易查询.API3_4订单状态同步;
import com.umpay.demo.step5_交易撤销_退费.API3_5订单撤销;
import com.umpay.demo.step5_交易撤销_退费.API3_6订单退费;
import com.umpay.demo.step5_交易撤销_退费.API3_7订单退费查询;
import com.umpay.demo.step6_交易关闭.API3_8订单关闭;
import org.junit.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付相关接口用例执行
 */
public class step2_支付相关接口用例 {

    /**
     * 支付相关接口用例
     * 1、调用所有收款交易
     * 2、调用订单状态同步
     * 3、调用交易撤销_退款
     * 4、调用交易关闭
     * 5、调用对账文件下载
     * @throws Exception
     */
    @Test
    public void test_交易() throws Exception {
        //1、调用收款交易
        callPayTrade();

        //2、调用订单状态同步
        new API3_4订单状态同步().orderQuery_订单状态同步();

        //3、调用交易撤销_退款
        callPayCancelAndRefund();

        //4、调用交易关闭
        new API3_2刷卡支付_用户被扫().pay_刷卡支付_用户被扫();//正向交易
        new API3_8订单关闭().orderClose_商户订单关闭请求();

        //5、调用对账文件下载

    }

    /**
     * 收款交易
     * 1、公众号小程序支付
     * 2、刷卡支付_用户被扫
     * 3、扫码支付_用户主扫
     */
    private void callPayTrade() throws Exception {
        //1、公众号小程序支付
        new API3_1公众号小程序支付().pay_公众号小程序支付();

        //2、刷卡支付_用户被扫
        new API3_2刷卡支付_用户被扫().pay_刷卡支付_用户被扫();

        //3、扫码支付_用户主扫
        new API3_3扫码支付_用户主扫().pay_扫码支付_用户主扫();
    }

    /**
     * 调用交易撤销_退款
     * 1、订单撤销
     * 2、订单退费
     * 3、订单退费查询
     */
    private void callPayCancelAndRefund() throws Exception {
        //1、订单撤销
        new API3_5订单撤销().cancel_商户撤销交易();

        //2、订单退费
        new API3_2刷卡支付_用户被扫().pay_刷卡支付_用户被扫();//正向交易
        new API3_6订单退费().refund_商户退款请求();

        //3、订单退费查询
        new API3_7订单退费查询().refundQuery_退款订单查询();
    }
}
