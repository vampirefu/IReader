package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.MyUtils;
import Model.ReadModel;

public class SettingForm extends JFrame {

	private JPanel contentPane;
	private JCheckBox ckb_CanEdit;
	private JCheckBox ckb_autoLineWrap;
	private JCheckBox ckb_AutoPaging;

	/**
	 * Create the frame.
	 */
	public SettingForm(final ReadModel rModel) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 297, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		MyUtils.SetFromShowCenter(SettingForm.this);
		// 连续阅读模式
		final JCheckBox ckb_Continue = new JCheckBox("连续阅读模式");
		ckb_Continue.setBounds(39, 18, 103, 23);
		contentPane.add(ckb_Continue);
		// 阅读完成时自动归类
		final JCheckBox ckb_AutoClassfy = new JCheckBox("阅读完成时自动归类");
		ckb_AutoClassfy.setBounds(39, 44, 162, 23);
		contentPane.add(ckb_AutoClassfy);

		JButton btn_OK = new JButton("确定");
		btn_OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rModel.setData.setContinueRead(ckb_Continue.isSelected());
				rModel.setData.setAutoClassfy(ckb_AutoClassfy.isSelected());
				rModel.setData.setCanEdit(ckb_CanEdit.isSelected());
				rModel.setData.setAutoLineWrap(ckb_autoLineWrap.isSelected());
				rModel.setData.setAutoPaging(ckb_AutoPaging.isSelected());
				try {
					rModel.setDao.update(rModel.setData);
				} catch (Exception e1) {
					System.out.println("更新系统设置失败");
				}
				dispose();
			}
		});
		btn_OK.setBounds(30, 171, 93, 23);
		contentPane.add(btn_OK);

		JButton btn_Cancel = new JButton("取消");
		btn_Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_Cancel.setBounds(155, 171, 93, 23);
		contentPane.add(btn_Cancel);

		ckb_CanEdit = new JCheckBox("文本可编辑");
		ckb_CanEdit.setBounds(39, 69, 103, 23);
		contentPane.add(ckb_CanEdit);

		ckb_autoLineWrap = new JCheckBox("文本自动换行");
		ckb_autoLineWrap.setBounds(39, 94, 103, 23);
		contentPane.add(ckb_autoLineWrap);

		ckb_AutoPaging = new JCheckBox("自动翻页");
		ckb_AutoPaging.setBounds(39, 119, 103, 23);
		contentPane.add(ckb_AutoPaging);

		if (rModel.setData == null) {
			ckb_Continue.setSelected(false);
			ckb_AutoClassfy.setSelected(false);
			ckb_CanEdit.setSelected(false);
			ckb_autoLineWrap.setSelected(false);
			ckb_AutoPaging.setSelected(false);
		} else {
			ckb_Continue.setSelected(rModel.setData.isContinueRead());
			ckb_AutoClassfy.setSelected(rModel.setData.isAutoClassfy());
			ckb_CanEdit.setSelected(rModel.setData.isCanEdit());
			ckb_autoLineWrap.setSelected(rModel.setData.isAutoLineWrap());
			ckb_AutoPaging.setSelected(rModel.setData.isAutoPaging());
		}
	}
}
