package View;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AddBookForm extends JFrame {

	private JPanel contentPane;
	private JTextField tf_Path;
	private JTextField tf_BookName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBookForm frame = new AddBookForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddBookForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 322, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"最近阅读", "历史阅读"}));
		comboBox.setBounds(188, 130, 108, 21);
		contentPane.add(comboBox);
		
		JButton btn_OK = new JButton("确定");
		btn_OK.setBounds(66, 177, 93, 23);
		contentPane.add(btn_OK);
		
		JButton btn_Cancel = new JButton("取消");
		btn_Cancel.setBounds(203, 177, 93, 23);
		contentPane.add(btn_Cancel);
		
		tf_Path = new JTextField();
		tf_Path.setBounds(23, 35, 260, 60);
		contentPane.add(tf_Path);
		tf_Path.setColumns(10);
		
		JLabel lb_Path = new JLabel("路径：");
		lb_Path.setBounds(23, 10, 54, 15);
		contentPane.add(lb_Path);
		
		JLabel lb_Classfy = new JLabel("书籍分类");
		lb_Classfy.setBounds(188, 105, 54, 15);
		contentPane.add(lb_Classfy);
		
		JLabel lb_BookName = new JLabel("书名");
		lb_BookName.setBounds(23, 105, 54, 15);
		contentPane.add(lb_BookName);
		
		tf_BookName = new JTextField();
		tf_BookName.setBounds(23, 130, 66, 21);
		contentPane.add(tf_BookName);
		tf_BookName.setColumns(10);
	}
}
