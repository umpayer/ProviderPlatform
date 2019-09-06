package com.umpay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.umpay.util.AnnotationUtil;


@SpringBootApplication
//@ComponentScan({"com.umpay"})
@EnableScheduling // 启动定时任务配置
public class StartTraceApplication {
	private static final Logger log = LoggerFactory.getLogger(StartTraceApplication.class);
	public static void main(String[] args) {
		// 指定默认的配置文件文件路径
		System.setProperty("spring.config.location", "config\\application.properties");

		log.info("spring启动设置：扫描的包路径，{}", AnnotationUtil.get(StartTraceApplication.class, ComponentScan.class, "value", "basePackages"));
	    log.info("spring启动设置：配置文件地址，spring.config.location={}", System.getProperty("spring.config.location"));
		log.info("spring启动设置：扫描启动定时任务");
		SpringApplication.run(StartTraceApplication.class, args);
	}

}
