package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	// 要连接的数据库名，　账号及密码，和驱动
	private String dbUrl = "jdbc:mysql://localhost/itcast_oa";

	private String dbUserName = "itcastoa";

	private String dbPassword = "1234";

	private String jdbcName = "com.mysql.jdbc.Driver";
	private static DbUtil instance = null;

	public static DbUtil getInstance() {
		if (instance == null)
			instance = new DbUtil();
		return instance;
	}

	private DbUtil() {
	}

	/**
	 * 
	 * 获得mysql链接
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */

	public Connection getCon() throws Exception {

		Class.forName(jdbcName);

		Connection con = DriverManager.getConnection(dbUrl, dbUserName,
				dbPassword);

		return con;

	}

	// 连接

	public void closeCon(Connection con) throws Exception {

		if (con != null) {

			con.close();

		}

	}

	// 测试代码

	public static void main(String[] args) {

		DbUtil dbUtil = new DbUtil();

		try {

			dbUtil.getCon();

			System.out.println("数据库连接成功");

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

}