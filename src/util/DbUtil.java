package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtil {
	// 要连接的数据库名，　账号及密码，和驱动
	private String dbUrl = "jdbc:mysql://localhost/itcast_oa";

	private String dbUserName = "itcastoa";

	private String dbPassword = "1234";

	private String jdbcName = "com.mysql.jdbc.Driver";
	private static Connection con;

	private static DbUtil instance = null;

	public static DbUtil getInstance() throws Exception {
		if (instance == null)
			instance = new DbUtil();
		return instance;
	}

	private DbUtil() throws Exception {
		Class.forName(jdbcName);
		con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
	}

	// 关闭连接
	public void closeCon(Connection con) throws Exception {
		if (con != null) {
			con.close();
		}
	}

	public ResultSet selectsql(String sql) {
		ResultSet rs = null;
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void insertsql(String sql) {
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("插入数据库时出错：");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("插入时出错：");
			e.printStackTrace();
		}

	}

	public void deletesql(String sql) {
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("删除数据库时出错");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("删除数据时出错：");
			e.printStackTrace();
		}

	}

	public void updatasql(String sql) {
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("更新数据库时出错");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("更新数据时出错：");
			e.printStackTrace();
		}
	}

}