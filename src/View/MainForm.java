package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.MyUtils;
import Model.ReadModel;

public class MainForm extends JFrame {

	private JPanel contentPane;
	public static MainForm mainForm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainForm = new MainForm();
					mainForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 213, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		MyUtils.SetFromShowCenter(MainForm.this);
		//数据初始化
		ReadModel rModel = new ReadModel();
		//书籍管理按钮
		JButton btn_Book = new JButton("\u4E66\u7C4D\u7BA1\u7406");
		btn_Book.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookManagerForm bmForm = new BookManagerForm(rModel);
				bmForm.setVisible(true);
			}
		});
		btn_Book.setBounds(38, 23, 93, 23);
		contentPane.add(btn_Book);
		//阅读管理功能
		JButton btn_Read = new JButton("\u9605\u8BFB\u7BA1\u7406");
		btn_Read.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainForm.setVisible(false);
				ReadForm rdForm = new ReadForm(rModel);
				rdForm.setVisible(true);
			}
		});
		btn_Read.setBounds(38, 74, 93, 23);
		contentPane.add(btn_Read);
		//设置按钮
		JButton btn_Set = new JButton("\u8BBE\u7F6E");
		btn_Set.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingForm setForm = new SettingForm(rModel);
				setForm.setVisible(true);
			}
		});
		btn_Set.setBounds(38, 129, 93, 23);
		contentPane.add(btn_Set);
	}
}
