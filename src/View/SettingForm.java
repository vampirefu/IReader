package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SettingForm extends JFrame {

	private JPanel contentPane;
	public static boolean Reading;
	public static boolean Classfy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingForm frame = new SettingForm();
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
	public SettingForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 297, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//连续阅读模式
		final JCheckBox ckb_Continue = new JCheckBox("\u8FDE\u7EED\u9605\u8BFB\u6A21\u5F0F");
		ckb_Continue.setBounds(39, 18, 103, 23);
		contentPane.add(ckb_Continue);
		//阅读完成时自动归类
		final JCheckBox ckb_Class = new JCheckBox("\u9605\u8BFB\u5B8C\u6210\u65F6\u81EA\u52A8\u5F52\u7C7B");
		ckb_Class.setBounds(39, 67, 162, 23);
		contentPane.add(ckb_Class);
		
		ckb_Continue.setSelected(Reading);
		ckb_Class.setSelected(Classfy);
		
		JButton btn_OK = new JButton("\u786E\u5B9A");
		btn_OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reading = ckb_Continue.isSelected();
				Classfy = ckb_Class.isSelected();
				dispose();
				//System.exit(0);
			}
		});
		btn_OK.setBounds(29, 141, 93, 23);
		contentPane.add(btn_OK);
		
		JButton btn_Cancel = new JButton("\u53D6\u6D88");
		btn_Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_Cancel.setBounds(154, 141, 93, 23);
		contentPane.add(btn_Cancel);
	}
}
