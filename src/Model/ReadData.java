package Model;

public class ReadData {
	private String fk_path;
	private int fontSize;
	private int fontStyle;
	private String fontName;
	private String fontColor;
	private String background;
	private int speed;

	public String getFk_path() {
		return fk_path;
	}

	public void setFk_path(String fk_path) {
		this.fk_path = fk_path;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public int getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(int fontStyle) {
		this.fontStyle = fontStyle;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public ReadData(String fk_path, int fontSize, int fontStyle,
			String fontName, String fontColor, String background, int speed) {
		super();
		this.fk_path = fk_path;
		this.fontSize = fontSize;
		this.fontStyle = fontStyle;
		this.fontName = fontName;
		this.fontColor = fontColor;
		this.background = background;
		this.speed = speed;
	}
}
