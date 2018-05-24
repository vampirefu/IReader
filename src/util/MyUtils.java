package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MyUtils {
	// 设置窗体显示在屏幕中心
	public static void SetFromShowCenter(JFrame jf) {
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension screen = tool.getScreenSize();
		jf.setLocation(screen.width / 2 - jf.getWidth() / 2, screen.height / 2
				- jf.getHeight() / 2);
	}

	public static boolean String2Bool(String str) {
		return str.contains("true");
	}

	public static String Bool2String(boolean bo) {
		return bo ? "true" : "false";
	}

	public static Color String2Color(String str) {
		String str1 = str.replaceAll("(", "");
		String str2 = str1.replaceAll(")", "").trim();
		String[] splits = str2.split(",");
		return new Color(Integer.parseInt(splits[0]),
				Integer.parseInt(splits[1]), Integer.parseInt(splits[2]));
	}
}
