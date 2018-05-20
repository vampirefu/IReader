package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.BookData;
import View.AddBookForm;

public class UserDao {

	public void queryAllUser() throws Exception {
		BookData resultUser = null;
		Connection con = DbUtil.getInstance().getCon();
		String sql = "SELECT * FROM t_bookinfo";// 查询表中所有记录
		PreparedStatement pstmt = con.prepareStatement(sql);
		// 这一步要弄明白！！！这是连接SQL的步骤中的其中几步
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {

			resultUser = new BookData(rs.getString("bookName"),
					rs.getString("path"), rs.getString("lastSite"),
					rs.getString("classfy"));
			AddBookForm.books.add(resultUser);
		}
	}

	// 添加操作
	public void insert(BookData sd) throws Exception {
		Connection con = DbUtil.getInstance().getCon();
		String sql = "INSERT INTO t_bookinfo(path,bookName,lastSite,classfy)"
				+ "VALUES('" + sd.getPath() + "','" + sd.getBookName() + "','"
				+ sd.getLastSite() + "','" + sd.getClassfy() + "')";
		Statement stmt = con.createStatement();
		int count = stmt.executeUpdate(sql);
	}

	// 删除操作
	public void delete(BookData sd) throws Exception {
		Connection con = DbUtil.getInstance().getCon();
		String sql = "DELETE FROM t_bookinfo WHERE bookName = '"
				+ sd.getBookName() + "'";
		Statement stmt = con.createStatement();
		int count = stmt.executeUpdate(sql);
	}

	// 更新操作
	public void update(BookData sd) throws Exception {
		Connection con = DbUtil.getInstance().getCon();
		String sql = "UPDATE t_bookinfo SET bookName='" + sd.getBookName()
				+ "',classfy= '" + sd.getClassfy() + "',lastSite= '"
				+ sd.getLastSite() + "'WHERE path = '" + sd.getPath() + "'";
		Statement stmt = con.createStatement();
		int count = stmt.executeUpdate(sql);
	}

}
