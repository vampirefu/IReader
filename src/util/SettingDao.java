package util;

import java.sql.ResultSet;

import Model.SettingData;

public class SettingDao {

	public SettingData queryAllUser() throws Exception {
		String sql = "SELECT * FROM t_settinginfo";// 查询表中所有记录
		ResultSet rs = DbUtil.getInstance().selectsql(sql);
		String s_ContinueRead = "false", s_AutoClassfy = "false", s_CanEdit = "false", s_AutoLineWrap = "false", s_AutoPaging = "false";
		boolean continueRead, autoClassfy, canEdit, autoLineWrap, autoPaging;
		if (rs.next()) {
			s_ContinueRead = rs.getString("continueRead");
			s_AutoClassfy = rs.getString("autoClassfy");
			s_CanEdit = rs.getString("canEdit");
			s_AutoLineWrap = rs.getString("autoLineWrap");
			s_AutoPaging = rs.getString("autoPaging");
		} else
			insert();
		continueRead = s_ContinueRead.contains("true");
		autoClassfy = s_AutoClassfy.contains("true");
		canEdit = s_CanEdit.contains("true");
		autoLineWrap = s_AutoLineWrap.contains("true");
		autoPaging = s_AutoPaging.contains("true");
		return new SettingData(continueRead, autoClassfy, canEdit,
				autoLineWrap, autoPaging);
	}

	// 添加操作
	public void insert() throws Exception {
		String sql = "INSERT INTO t_settinginfo(continueRead,autoClassfy,canEdit,autoLineWrap,autoPaging)VALUES('false','false','false','false','false')";
		DbUtil.getInstance().insertsql(sql);
	}

	// 更新操作
	public void update(SettingData sd) throws Exception {
		String sql = "UPDATE t_settinginfo SET continueRead='"
				+ MyUtils.Bool2String(sd.isContinueRead()) + "',autoClassfy= '"
				+ MyUtils.Bool2String(sd.isAutoClassfy()) + "',canEdit= '"
				+ MyUtils.Bool2String(sd.isCanEdit()) + "',autoLineWrap= '"
				+ MyUtils.Bool2String(sd.isAutoLineWrap()) + "',autoPaging= '"
				+ MyUtils.Bool2String(sd.isAutoPaging()) + "'";
		DbUtil.getInstance().updatasql(sql);
	}

}
