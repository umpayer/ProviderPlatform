package com.umpay.call;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Assert;

import com.umpay.consts.BusiConsts;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.util.LogUtil;
import com.umpay.util.db.OnTestUmssfrnaDBConnect;

public class UpdateYsfMerInfoForDB {

	
	@SuppressWarnings({ "unchecked", "unused" })
	public void UpdateAuditStatusYsfMerInfo(){
		String merid= (String) EnvConfig.context.get(BusiConsts.merId);
		String auditStatus= (String) EnvConfig.context.get(BusiConsts.auditStatus);
		String sql = "UPDATE UMPAY.T_YSFPAY_MER_INF SET AUDITSTATUS='"+auditStatus+"' WHERE YSFMERID='"+merid+"'";		
		LogUtil.info(sql);
		Connection conn = OnTestUmssfrnaDBConnect.getConnect();
		PreparedStatement pstmt = null;
		int  rs;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeUpdate();
			LogUtil.error("修改商户状态成功，rs="+rs+"SQL语句为：" + sql);
			Assert.assertTrue("修改商户状态成功", true);
		} catch (Exception e) {
			LogUtil.error("修改商户状态失败，SQL语句为：" + sql,e);
			Assert.assertTrue("修改商户状态失败异常", false);
		} finally {
			OnTestUmssfrnaDBConnect.release(conn, pstmt);
		}
	}
}
