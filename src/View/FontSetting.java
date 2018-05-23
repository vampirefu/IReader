package View;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import util.MyUtils;

public class FontSetting extends JFrame {

	private JPanel contentPane;
	GraphicsEnvironment ge;// 定义系统字体对象
	String[] style = { "PLAIN", "BOLD", "ITALIC", "BOLD&ITALIC" };
	String[] size = { "8", "10", "12", "14", "16", "18", "20", "22", "24",
			"26", "28", "32", "36", "48", "72", "96" };

	/**
	 * Create the frame.
	 */
	public FontSetting(final JTextArea content) {
		setTitle("字体设置");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 256, 195);
		MyUtils.SetFromShowCenter(FontSetting.this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("字体大小");
		label.setBounds(10, 10, 54, 15);
		contentPane.add(label);

		JLabel label_1 = new JLabel("字体样式");
		label_1.setBounds(10, 45, 54, 15);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("字体");
		label_2.setBounds(10, 78, 54, 15);
		contentPane.add(label_2);

		final JComboBox cbx_FontSize = new JComboBox(size);
		cbx_FontSize.setBounds(76, 7, 68, 21);
		contentPane.add(cbx_FontSize);
		cbx_FontSize.setEditable(true);

		final JComboBox cbx_FontStyle = new JComboBox(style);
		cbx_FontStyle.setSelectedIndex(0);
		cbx_FontStyle.setBounds(76, 40, 86, 24);
		contentPane.add(cbx_FontStyle);
		cbx_FontStyle.setEditable(true);

		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();// 获取系统字体
		String[] fontname = ge.getAvailableFontFamilyNames();
		final JComboBox cbx_Font = new JComboBox(fontname);
		cbx_Font.setBounds(76, 75, 154, 21);
		contentPane.add(cbx_Font);
		cbx_Font.setEditable(true);

		if (!content.getText().isEmpty()) {
			Font font = content.getFont();
			cbx_FontSize.setSelectedItem(font.getSize());
			cbx_Font.setSelectedItem(font.getFontName());
			cbx_FontStyle.setSelectedItem(style[font.getStyle()]);
		}

		JButton btn_Cancel = new JButton("取消");
		btn_Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FontSetting.this.dispose();
			}
		});
		btn_Cancel.setBounds(76, 125, 68, 23);
		contentPane.add(btn_Cancel);

		JButton btn_OK = new JButton("确定");
		btn_OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String wordName = (String) cbx_Font.getSelectedItem();
					int wordStyle = cbx_FontStyle.getSelectedIndex();
					String wordSize = cbx_FontSize.getSelectedItem().toString();
					content.setFont(new Font(wordName, wordStyle, Integer
							.parseInt(wordSize)));
					dispose();
				} catch (Exception e0) {
					e0.printStackTrace();
				}
			}
		});
		btn_OK.setBounds(162, 125, 68, 23);
		contentPane.add(btn_OK);

	}
}
