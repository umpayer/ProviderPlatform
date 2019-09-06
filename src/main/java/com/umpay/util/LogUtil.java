package com.umpay.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
	  public static final Logger log = LoggerFactory.getLogger("info");  
	  
	  public static void info(int msg){
		  log.info(""+msg);
	  }
	  public static void info(Object msg){
		  log.info(msg.toString());
	  }
	  public static void info(String msg){
		  log.info(msg);
	  }
	  public static void error(String msg,Exception e){
		  log.error(msg,e);
	  }
}
