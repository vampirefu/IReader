package Model;

public class SettingData {
	// 连续阅读模式(boolean)
	private String reading;
	// 阅读完成时自动归类(boolean)
	private String autoClassfy;

	public String getReading() {
		return reading;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}

	public String getAutoClassfy() {
		return autoClassfy;
	}

	public void setAutoClassfy(String autoClassfy) {
		this.autoClassfy = autoClassfy;
	}
}
