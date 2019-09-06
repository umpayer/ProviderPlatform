package com.umpay.call;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.umpay.jnuit.step1_入网相关接口用例;

public class WebCallDemo {
    public static final Logger APP = LoggerFactory.getLogger("info");  
    public static String callMerInNet() {
    	APP.info("+++++++++++++++进入联动入网流程");
        try{
            new step1_入网相关接口用例().test_商户入网();
        }catch (Exception e) {
            return e.getMessage();
        }
        return "0000";
    }
    
    
    public static void main(String[] args) {
    	WebCallDemo.callMerInNet();
	}
}
