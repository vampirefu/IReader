package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;

import util.MyUtils;

public class SpeedForm extends JFrame {

	private JPanel contentPane;
	private Integer msg[] = { 1, 10, 18, 36, 48, 66, 80, 90 };
	private Integer speed;
	private JComboBox cbx_Speed;

	/**
	 * Create the frame.
	 */
	public SpeedForm(final JScrollBar jsb) {
		setTitle("滚屏速度设置");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 236, 156);
		MyUtils.SetFromShowCenter(SpeedForm.this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("滚屏速度");
		label.setBounds(10, 25, 54, 15);
		contentPane.add(label);

		cbx_Speed = new JComboBox(msg);
		cbx_Speed.setBounds(74, 22, 114, 21);
		contentPane.add(cbx_Speed);
		cbx_Speed.setEditable(true);
		cbx_Speed.setSelectedItem(jsb.getUnitIncrement());

		JButton btn_Cancel = new JButton("取消");
		btn_Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_Cancel.setBounds(57, 84, 64, 23);
		contentPane.add(btn_Cancel);

		JButton btn_OK = new JButton("确定");
		btn_OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				speed = (Integer) cbx_Speed.getSelectedItem();
				jsb.setUnitIncrement(speed.intValue());
				dispose();
			}
		});
		btn_OK.setBounds(146, 84, 64, 23);
		contentPane.add(btn_OK);
	}

}
