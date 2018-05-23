package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

import util.MyUtils;
import Conntrol.MSWordManager;
import Conntrol.SaveAndSaveAs;
import Model.BookData;

public class ReadForm extends JFrame {
	JTextArea content;
	JMenuBar menubar = new JMenuBar();
	JMenu menu_file = new JMenu("文件");
	JMenuItem mui_Open = new JMenuItem("打开");
	JMenuItem mui_Save = new JMenuItem("保存");
	JMenuItem mui_SaveAs = new JMenuItem("另存为");
	JMenuItem mui_Exit = new JMenuItem("退出");
	// 是否换行
	private Boolean autoLineWrap = true;
	// 文本是否支持编辑
	private Boolean isEdit = false;
	JScrollPane contentScroll;
	private JScrollBar jsb;
	int delay = 10;
	Timer timer = new Timer(delay, new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			jsb.setValue(lastSite = jsb.getValue() + jsb.getUnitIncrement());
		}
	});

	boolean flag = true;
	String str_filePath = null;
	int lastSite;

	public ReadForm() {
		this.setSize(780, 553);
		// 文字输入框（文字显示窗口）
		content = new JTextArea(10, 50);
		content.setAutoscrolls(true);
		content.setEditable(isEdit);
		if (!isEdit)
			mui_Save.setVisible(false);
		content.setLineWrap(autoLineWrap);// 设置自动换行
		contentScroll = new JScrollPane(content);
		// 滚动条
		jsb = contentScroll.getVerticalScrollBar();
		content.setBorder(BorderFactory.createBevelBorder(1));
		JPanel upper = new JPanel(new BorderLayout());
		upper.add(contentScroll);

		menu_file.add(mui_Open);
		menu_file.add(mui_Save);
		menu_file.add(mui_SaveAs);
		menu_file.add(mui_Exit);
		menubar.add(menu_file);
		JPanel buttonp = new JPanel();
		JPanel all = new JPanel(new GridLayout(1, 1));
		all.add(upper);
		this.getContentPane().add(menubar, BorderLayout.NORTH);

		JMenu mu_edit = new JMenu("编辑");
		menubar.add(mu_edit);

		JMenuItem mui_Font = new JMenuItem("字体");
		mu_edit.add(mui_Font);

		JMenuItem mui_FontColor = new JMenuItem("字体颜色");
		mu_edit.add(mui_FontColor);

		JMenuItem mui_Background = new JMenuItem("背景色");
		mu_edit.add(mui_Background);

		JMenuItem mui_Speed = new JMenuItem("滚轮速度");
		mu_edit.add(mui_Speed);
		this.getContentPane().add(buttonp, BorderLayout.SOUTH);
		this.getContentPane().add(all, BorderLayout.CENTER);
		// this.pack();
		MyUtils.SetFromShowCenter(ReadForm.this);
		this.setTitle("TXT小说阅读器");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				if (MainForm.mainForm != null)
					MainForm.mainForm.setVisible(true);
			}
		});
		content.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				if (e.getClickCount() == 2) {
					timer.start();
				}
				if (e.getClickCount() == 1) {
					timer.stop();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根

			}
		});
		// 监听上下键//
		content.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_UP) {
					jsb.setValue(lastSite = jsb.getValue()
							- jsb.getUnitIncrement());
				}
				if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
					jsb.setValue(lastSite = jsb.getValue()
							+ jsb.getUnitIncrement());
				}
			}

			public void keyReleased(KeyEvent arg0) {
			}

			public void keyTyped(KeyEvent arg0) {
			}
		});

		// （菜单栏）文件——打开
		mui_Open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser jfc = new JFileChooser();
					if (jfc.showOpenDialog(ReadForm.this) == JFileChooser.APPROVE_OPTION) {
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
							// 滚动条置顶
							content.setCaretPosition(0);
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
		// （菜单栏）文件——另存为
		mui_SaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				if (jfc.showSaveDialog(ReadForm.this) == JFileChooser.APPROVE_OPTION)
					new SaveAndSaveAs(content, jfc.getSelectedFile().getPath());
			}
		});
		// （菜单栏）文件——保存
		mui_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SaveAndSaveAs(content, str_filePath);
			}
		});
		// （菜单栏）文件——退出
		mui_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = -1;
				Object options[] = { "Yes", "No" };
				option = JOptionPane
						.showOptionDialog(ReadForm.this, "是否退出阅读？", "exit",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
				switch (option) {
				case JOptionPane.YES_OPTION:
					System.exit(0);
				}
			}
		});
		// （菜单栏）编辑——字体
		mui_Font.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FontSetting fsForm = new FontSetting(content);
				fsForm.setVisible(true);
			}
		});
		// （菜单栏）编辑——字体颜色
		mui_FontColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(ReadForm.this, "选择颜色",
						Color.BLACK);
				content.setForeground(color);
			}
		});
		// （菜单栏）编辑——背景色
		mui_Background.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 弹出颜色色选择器对话框
				Color color = JColorChooser.showDialog(ReadForm.this, "选择颜色",
						Color.WHITE);
				content.setBackground(color);
			}
		});
		// （菜单栏）编辑——滚轮速度
		mui_Speed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SpeedForm(jsb).setVisible(true);
			}
		});

		GridBagLayout gbl_buttonp = new GridBagLayout();
		gbl_buttonp.columnWidths = new int[] { 105, 105, 105, 105, 105 };
		gbl_buttonp.rowHeights = new int[] { 23, 0 };
		gbl_buttonp.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_buttonp.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		buttonp.setLayout(gbl_buttonp);
		// 上一页按钮
		JButton btn_Last = new JButton("上一页");
		btn_Last.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastSite = contentScroll.getVerticalScrollBar().getValue();
				jsb.setValue(lastSite -= contentScroll.getHeight());
			}
		});
		GridBagConstraints gbc_btn_Last = new GridBagConstraints();
		gbc_btn_Last.anchor = GridBagConstraints.WEST;
		gbc_btn_Last.insets = new Insets(0, 0, 0, 5);
		gbc_btn_Last.gridx = 0;
		gbc_btn_Last.gridy = 0;
		buttonp.add(btn_Last, gbc_btn_Last);

		// 下一页按钮
		JButton btn_Next = new JButton("下一页");
		btn_Next.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btn_Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastSite = contentScroll.getVerticalScrollBar().getValue();
				jsb.setValue(lastSite += contentScroll.getHeight());
			}
		});
		GridBagConstraints gbc_btn_Next = new GridBagConstraints();
		gbc_btn_Next.anchor = GridBagConstraints.EAST;
		gbc_btn_Next.gridx = 4;
		gbc_btn_Next.gridy = 0;
		buttonp.add(btn_Next, gbc_btn_Next);

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
}
