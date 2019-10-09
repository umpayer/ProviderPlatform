package com.umpay.call;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Assert;

import com.umpay.consts.BusiConsts;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.util.LogUtil;
import com.umpay.util.db.OnTestUmssfrnaDBConnect;

public class QueryYsfMerInfoForDB {

	
	@SuppressWarnings("unchecked")
	public void QueryYsfMerInfo(){
		String merid= (String) EnvConfig.context.get(BusiConsts.merId);
		String sql = "SELECT  ACQSPID, YSFMERID, ACQMERID,AUDITSTATUS FROM UMPAY.T_YSFPAY_MER_INF  WHERE YSFMERID= '"+merid+"'";		
		LogUtil.info(sql);
		Connection conn = OnTestUmssfrnaDBConnect.getConnect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			LogUtil.info("查询商户信息成功，结果集为："+rs.getString(1));
			String acqMerId = rs.getString(3);
			String auditStatus = rs.getString(4);
			EnvConfig.context.put(BusiConsts.acqMerId, acqMerId);
			EnvConfig.context.put(BusiConsts.auditStatus, auditStatus);
			LogUtil.info("查询商户信息成功，acqMerId为："+acqMerId);
			Assert.assertTrue("查询商户信息成功", true);
		} catch (Exception e) {
			LogUtil.error("查询商户信息失败，SQL语句为：" + sql,e);
			Assert.assertTrue("查询商户信息失败异常", false);
		} finally {
			OnTestUmssfrnaDBConnect.release(conn, pstmt, rs);
		}
	}
}
