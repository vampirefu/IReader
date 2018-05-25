package util;

import java.sql.ResultSet;
import java.util.ArrayList;

import Model.BookData;
import View.AddBookForm;

public class BookDao {

	public ArrayList<BookData> queryAll() throws Exception {
		ArrayList<BookData> books = new ArrayList<BookData>();
		BookData book = null;
		String sql = "SELECT * FROM t_bookinfo";// 查询表中所有记录
		ResultSet rs = DbUtil.getInstance().selectsql(sql);
		while (rs.next()) {
			book = new BookData(rs.getString("bookName"), rs.getString("path"),
					rs.getInt("lastSite"), rs.getString("classfy"));
			books.add(book);
		}
		return books;
	}

	// 添加操作
	public void insert(BookData sd) throws Exception {
		String path = sd.getPath().replace("\\", "\\\\");
		String sql = "INSERT INTO t_bookinfo(path,bookName,lastSite,classfy)"
				+ "VALUES('" + path + "','" + sd.getBookName() + "',"
				+ sd.getLastSite() + ",'" + sd.getClassfy() + "')";
		DbUtil.getInstance().insertsql(sql);
	}

	// 删除操作
	public void delete(BookData sd) throws Exception {
		String sql = "DELETE FROM t_bookinfo WHERE bookName = '"
				+ sd.getBookName() + "'";
		DbUtil.getInstance().deletesql(sql);
	}

	// 更新操作
	public void update(BookData sd) throws Exception {
		String path = sd.getPath().replace("\\", "\\\\");
		String sql = "UPDATE t_bookinfo SET path = '" + path + "',bookName='"
				+ sd.getBookName() + "',lastSite= " + sd.getLastSite()
				+ ",classfy= '" + sd.getClassfy() + "'";
		DbUtil.getInstance().updatasql(sql);
	}

}
