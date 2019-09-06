package com.umpay.util;

import java.io.*;
import java.net.URL;
import java.util.Properties;


/** ******************  类说明  *********************
 * class       :  ConfigUtil
 * @author     :  LiuJiLong
 * @version    :  1.0
 * description :  配置工具类
 * @see        :
 * ************************************************/
public class ConfigUtil {
	static private String configPath;

	private static Properties props = null;

	static {
		loadConfig("/conf/config.properties", "utf-8");
		loadConfig("/conf/" + props.get("env") + ".properties", "utf-8");
	}
	
	/**
	 * 初始化
	 * @param configPath="/config/jdbc.properties"
	 * @param charSet = UTF-8|GBK
	 */
	
	public static void loadConfig(String configPath,String charSet){
		try {
			if (props == null) {
				props = new Properties();
			}
			File classpathRoot = new File(System.getProperty("user.dir"));
			File dir = new File(classpathRoot,"");//项目所在根目录
			String prefix = dir.getAbsolutePath() + configPath;
			LogUtil.info("读取配置文件："+prefix);
			LogUtil.info("读取配置文件："+prefix);
			ConfigUtil.configPath = prefix;
			InputStreamReader in = new InputStreamReader(new FileInputStream(prefix),charSet);
			props.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadConfig(String configPath){
		ConfigUtil.configPath = configPath;
		InputStream in = null;

		try {
			in = ConfigUtil.class.getResourceAsStream(configPath);
			props = new Properties();
			props.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** ********************************************
	 * method name   : getConfig 
	 * description   : 获取配置的VALUE
	 * @return       : String
	 * @param        : @param key
	 * @param        : @return
	 * modified      : LiuJiLong ,  2011-12-20  上午11:15:23
	 * @see          : 
	 * ********************************************/      
	public static String getConfig(String key){
		return StringUtil.trim(props.getProperty(key));//配置文件读取
	}
}



