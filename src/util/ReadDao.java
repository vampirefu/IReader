package util;

import java.sql.ResultSet;
import java.util.ArrayList;

import Model.ReadData;

public class ReadDao {
	public ArrayList<ReadData> queryAllUser() throws Exception {
		ArrayList<ReadData> rds = new ArrayList<ReadData>();
		ReadData resultUser = null;
		String sql = "SELECT * FROM t_readinfo";// 查询表中所有记录
		ResultSet rs = DbUtil.getInstance().selectsql(sql);
		while (rs.next()) {
			resultUser = new ReadData(rs.getString("fk_path"),
					rs.getInt("fontSize"), rs.getInt("fontStyle"),
					rs.getString("fontName"), rs.getString("fontColor"),
					rs.getString("background"), rs.getInt("speed"));
			rds.add(resultUser);
		}
		return rds;
	}

	// 添加操作
	public void insert(ReadData sd) throws Exception {
		String sql = "INSERT INTO t_readinfo(fk_path,fontSize,fontStyle,fontName,fontColor,background,speed)"
				+ "VALUES('"
				+ sd.getFk_path()
				+ "',"
				+ sd.getFontSize()
				+ ","
				+ sd.getFontStyle()
				+ ",'"
				+ sd.getFontName()
				+ "','"
				+ sd.getFontColor()
				+ "','"
				+ sd.getBackground()
				+ "',"
				+ sd.getSpeed() + ")";
		DbUtil.getInstance().insertsql(sql);
	}

	// 删除操作
	public void delete(ReadData sd) throws Exception {
		String sql = "DELETE FROM t_readinfo WHERE fk_path = '"
				+ sd.getFk_path() + "'";
		DbUtil.getInstance().deletesql(sql);
	}

	// 更新操作
	public void update(ReadData sd) throws Exception {
		String sql = "UPDATE t_readinfo SET fk_path='" + sd.getFk_path()
				+ "',fontSize= " + sd.getFontSize() + ",fontStyle= "
				+ sd.getFontStyle() + ",fontName= '" + sd.getFontName()
				+ "',fontColor= '" + sd.getFontColor() + "',background= '"
				+ sd.getBackground() + "',speed= " + sd.getSpeed();
		DbUtil.getInstance().updatasql(sql);
	}

}
