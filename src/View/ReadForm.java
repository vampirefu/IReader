package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Conntrol.MSWordManager;
import Conntrol.SaveAndSaveAs;
import Conntrol.WordStyleSet;
import Model.BookData;

public class ReadForm extends JFrame {
	JFrame jframe = new JFrame();
	JTextArea content, wordSizeSetArea, wordStyleSetArea, wordNameSetArea;
	JMenuBar menubar = new JMenuBar();
	JMenu menu_file = new JMenu("文件");
	JMenuItem menuItem_open = new JMenuItem("打开");
	JMenuItem menuItem_save = new JMenuItem("保存");
	JMenuItem menuItem_saveAs = new JMenuItem("另存为");
	JMenuItem menuItem_exit = new JMenuItem("退出");

	boolean flag = true;
	String str_filePath = null;

	public ReadForm() {
		// 文字输入框（文字显示窗口）
		content = new JTextArea(10, 50);
		content.setAutoscrolls(true);
		JScrollPane contentScroll = new JScrollPane(content);
		content.setBorder(BorderFactory.createBevelBorder(1));
		JPanel upper = new JPanel(new BorderLayout());
		upper.add(contentScroll);
		// 字体大小设置窗口
		wordSizeSetArea = new JTextArea(1, 3);
		wordSizeSetArea.setBorder(BorderFactory.createBevelBorder(1));
		wordSizeSetArea.setText("12");
		// 字体样式设置窗口（加粗等）
		wordStyleSetArea = new JTextArea(1, 3);
		wordStyleSetArea.setBorder(BorderFactory.createBevelBorder(1));
		wordStyleSetArea.setText("0");
		// 字体名字设置窗口（宋体等）
		wordNameSetArea = new JTextArea(1, 3);
		wordNameSetArea.setBorder(BorderFactory.createBevelBorder(1));
		wordNameSetArea.setText("宋体");
		// （菜单栏）文件——打开
		menuItem_open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser jfc = new JFileChooser();
					if (jfc.showOpenDialog(jframe) == JFileChooser.APPROVE_OPTION) {
						str_filePath = jfc.getSelectedFile().getAbsolutePath();
						// 打开TXT
						if (str_filePath.contains(".txt")) {
							BufferedReader bufferedReader = new BufferedReader(
									new FileReader(str_filePath));
							String str_line;
							while ((str_line = bufferedReader.readLine()) != null) {
								if (flag) {
									content.setText(str_line);
									flag = false;
								} else {
									content.setText(content.getText() + "\n"
											+ str_line);
								}
							}
							bufferedReader.close();
							// 打开word文件
						} else if (str_filePath.contains(".doc")
								|| str_filePath.contains(".docx")) {
							MSWordManager ms = new MSWordManager(true);
							ms.openDocument(str_filePath);
						}
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		});
		// 按钮
		JButton wordSizeSet = new JButton("设置字体大小");
		wordSizeSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					WordStyleSet.StyleSet(content, wordNameSetArea.getText(),
							Integer.parseInt(wordStyleSetArea.getText()),
							Integer.parseInt(wordSizeSetArea.getText()));
				} catch (Exception e0) {
					e0.printStackTrace();
				}
			}
		});
		// 按钮
		JButton wordStyleSet = new JButton("设置字体样式");
		wordStyleSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					WordStyleSet.StyleSet(content, wordNameSetArea.getText(),
							Integer.parseInt(wordStyleSetArea.getText()),
							Integer.parseInt(wordSizeSetArea.getText()));
				} catch (Exception e0) {
					e0.printStackTrace();
				}
			}
		});
		// 按钮
		JButton wordNameSet = new JButton("设置字体名字");
		wordNameSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					WordStyleSet.StyleSet(content, wordNameSetArea.getText(),
							Integer.parseInt(wordStyleSetArea.getText()),
							Integer.parseInt(wordSizeSetArea.getText()));
				} catch (Exception e0) {
					e0.printStackTrace();
				}
			}
		});
		// （菜单栏）文件——另存为
		menuItem_saveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				if (jfc.showSaveDialog(jframe) == JFileChooser.APPROVE_OPTION)
					new SaveAndSaveAs(content, jfc.getSelectedFile().getPath());
			}
		});
		// （菜单栏）文件——保存
		menuItem_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SaveAndSaveAs(content, str_filePath);
			}
		});
		// （菜单栏）文件——退出
		menuItem_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		menu_file.add(menuItem_open);
		menu_file.add(menuItem_save);
		menu_file.add(menuItem_saveAs);
		menu_file.add(menuItem_exit);
		menubar.add(menu_file);
		JPanel buttonp = new JPanel();
		buttonp.add(wordSizeSet);
		buttonp.add(wordSizeSetArea);
		buttonp.add(wordStyleSet);
		buttonp.add(wordStyleSetArea);
		buttonp.add(wordNameSet);
		buttonp.add(wordNameSetArea);
		JPanel all = new JPanel(new GridLayout(1, 1));
		all.add(upper);
		jframe.getContentPane().add(menubar, BorderLayout.NORTH);
		jframe.getContentPane().add(buttonp, BorderLayout.SOUTH);
		jframe.getContentPane().add(all, BorderLayout.CENTER);
		jframe.pack();
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension screen = tool.getScreenSize();
		jframe.setLocation(screen.width / 2 - jframe.getWidth() / 2,
				screen.height / 2 - jframe.getHeight() / 2);
		jframe.setTitle("TXT小说阅读器");
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				if (MainForm.mainForm != null)
					MainForm.mainForm.setVisible(true);
			}

		});
	}

	public void OpenBook(BookData book) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					book.getPath()));
			String str_line;
			while ((str_line = bufferedReader.readLine()) != null) {
				if (flag) {
					content.setText(str_line);
					flag = false;
				} else {
					content.setText(content.getText() + "\n" + str_line);
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	public static void main(String args[]) {
		ReadForm display_demo = new ReadForm();
	}

}
