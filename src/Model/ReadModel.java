package Model;

import java.util.ArrayList;

import util.BookDao;
import util.ReadDao;
import util.SettingDao;

public class ReadModel {
	public ArrayList<BookData> books;
	public ArrayList<ReadData> reads;
	// 系统设置数据
	public SettingData setData;
	// 当前书籍数据
	public BookData curBook;
	// 当前阅读设置数据
	public ReadData curRead;
	public BookDao bookDao;
	public ReadDao readDao;
	public SettingDao setDao;

	public ReadModel() {
	 bookDao = new BookDao();
		try {
			books = bookDao.queryAll();
		} catch (Exception e) {
			books = new ArrayList<BookData>();
			System.out.println("获取书籍数据失败");
		}
		readDao = new ReadDao();
		try {
			reads = readDao.queryAllUser();
		} catch (Exception e) {
			reads = new ArrayList<ReadData>();
			System.out.println("获取阅读数据失败");
		}
		setDao = new SettingDao();
		try {
			setData = setDao.queryAllUser();
		} catch (Exception e) {
			setData = new SettingData(false, false, false, false, false);
			System.out.println("获取系统设置数据失败");
		}
	}
}
