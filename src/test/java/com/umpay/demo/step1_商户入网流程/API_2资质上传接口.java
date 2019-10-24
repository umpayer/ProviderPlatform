package com.umpay.demo.step1_商户入网流程;

import com.umpay.demo.step0_准备工作.EnvConfig;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class API_2资质上传接口 extends API_2资质上传接口parent {

	public static void main(String[] args) throws Exception {
		API_2资质上传接口 group = new API_2资质上传接口();
		//小微
		group.test_小微();
		//个休
		//group.test_个体_企业商户();
	}
	/**
	 * @throws Exception 
	 * @Description: 自然人 流程
	 * @date 2019年8月16日 下午3:20:58
	 */
	@Test
	public void test_小微() throws Exception {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Assert.assertNotNull("参数缺失,服务商编号", EnvConfig.context.get("acqSpId"));
		Assert.assertNotNull("参数缺失,报备编号", EnvConfig.context.get("merId"));
		paramMap.put("acqSpId", EnvConfig.context.get("acqSpId"));
		paramMap.put("merId", EnvConfig.context.get("merId"));

		try{
			//1、上传身份证正面
			test_idCardFront(paramMap);
			//2、上传身份证反面
			test_idCardBack(paramMap);
			//3、签约授权书
			test_signAuthLetterPhoto(paramMap);
			//4、上传银行卡正面
			test_bankCardPhotoFront(paramMap);
			//5、上传银行卡反面
			test_bankCardPhotoBack(paramMap);
			//6、签约授权书
			test_signAuthLetterPhoto(paramMap);
			//门店门头照
			test_storeHeadPhoto(paramMap);
//			//门店外景照
//			test_storeShopPhoto(paramMap);
			//门店内景照
			test_storeHallPhoto(paramMap);
			//门店收银台照
			test_storeCashierPhoto(paramMap);
//			//7、法人手持身份证
//			test_idCardHandle(paramMap);
//			System.out.println("2.2.7资质上传接口_个体_企业商户:法人手持身份证上传成功！");
		}catch (Exception e) {
			Assert.assertTrue(e.getMessage(), false);
		}
		Assert.assertTrue("资质上传成功", true);

	}
	
	/**
	 * @throws Exception 
	 * @Description: 个体+企业商户 流程
	 * @date 2019年8月16日 下午3:20:58
	 */
	@Test
	public void test_个体_企业商户() throws Exception {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Assert.assertNotNull("参数缺失,服务商编号", EnvConfig.context.get("acqSpId"));
		Assert.assertNotNull("参数缺失,报备编号", EnvConfig.context.get("merId"));
		paramMap.put("acqSpId", EnvConfig.context.get("acqSpId"));
		paramMap.put("merId", EnvConfig.context.get("merId"));

		try{
			//1、上传身份证正面
			test_idCardFront(paramMap);
			//2、上传身份证反面
			test_idCardBack(paramMap);
			//3、签约授权书
			test_signAuthLetterPhoto(paramMap);
			//4、上传银行卡正面
			test_bankCardPhotoFront(paramMap);
			//5、上传银行卡反面
			test_bankCardPhotoBack(paramMap);
			//6、营业执照照片
			test_businessLicensePhoto(paramMap);
//			//7、法人手持身份证
//			test_idCardHandle(paramMap);
//			//门店外景照
//			test_storeShopPhoto(paramMap);
			//门店内景照
			test_storeHallPhoto(paramMap);
			//门店收银台照
			test_storeCashierPhoto(paramMap);
			//门店门头照
			test_storeHeadPhoto(paramMap);
		}catch (Exception e) {
			Assert.assertTrue(e.getMessage(), false);
		}
		Assert.assertTrue("资质上传成功", true);
	}
}
