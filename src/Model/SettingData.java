package Model;

public class SettingData {
	// 连续阅读模式(boolean)
	private boolean continueRead;
	// 阅读完成时自动归类
	private boolean autoClassfy;
	private boolean canEdit;
	//自动换行
	private boolean autoLineWrap;
	//自动翻页
	private boolean autoPaging;

	public boolean isContinueRead() {
		return continueRead;
	}

	public void setContinueRead(boolean continueRead) {
		this.continueRead = continueRead;
	}

	public boolean isAutoClassfy() {
		return autoClassfy;
	}

	public void setAutoClassfy(boolean autoClassfy) {
		this.autoClassfy = autoClassfy;
	}

	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}

	public boolean isAutoLineWrap() {
		return autoLineWrap;
	}

	public void setAutoLineWrap(boolean autoLineWrap) {
		this.autoLineWrap = autoLineWrap;
	}

	public boolean isAutoPaging() {
		return autoPaging;
	}

	public void setAutoPaging(boolean autoPaging) {
		this.autoPaging = autoPaging;
	}

	public SettingData(boolean continueRead, boolean autoClassfy,
			boolean canEdit, boolean autoLineWrap, boolean autoPaging) {
		super();
		this.continueRead = continueRead;
		this.autoClassfy = autoClassfy;
		this.canEdit = canEdit;
		this.autoLineWrap = autoLineWrap;
		this.autoPaging = autoPaging;
	}
}
