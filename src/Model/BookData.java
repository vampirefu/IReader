package Model;

public class BookData {

	// 书名
	private String bookName;
	// 路径
	private String path;
	// 最后一次阅读位置
	private int lastSite;
	// 分类
	private String classfy;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getLastSite() {
		return lastSite;
	}

	public void setLastSite(int lastSite) {
		this.lastSite = lastSite;
	}

	public String getClassfy() {
		return classfy;
	}

	public void setClassfy(String classfy) {
		this.classfy = classfy;
	}

	public BookData(String bookName, String path, int lastSite, String classfy) {
		super();
		this.bookName = bookName;
		this.path = path;
		this.lastSite = lastSite;
		this.classfy = classfy;
	}

}
