package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import util.MyUtils;
import Model.BookData;
import Model.ReadModel;

public class AddBookForm extends JFrame {

	private JPanel contentPane;
	private JTextField tf_BookName;
	private JComboBox comboBox;
	private JTextArea ta_Path;

	/**
	 * Create the frame.
	 */
	public AddBookForm(final ReadModel rModel, final BookManagerForm bmf) {
		this.setTitle("新增");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 322, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		MyUtils.SetFromShowCenter(AddBookForm.this);
		// 下拉框
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "最近阅读",
				"历史阅读" }));
		comboBox.setBounds(188, 130, 108, 21);
		contentPane.add(comboBox);

		ta_Path = new JTextArea();
		ta_Path.setBounds(10, 35, 220, 44);
		contentPane.add(ta_Path);
		ta_Path.setLineWrap(true);
		ta_Path.setEditable(true);

		JButton btn_OK = new JButton("确定");
		btn_OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ta_Path.getText().isEmpty()
						|| tf_BookName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "书名或路径不能为空", "提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				BookData book = new BookData(tf_BookName.getText(), ta_Path
						.getText(), 0, (String) comboBox.getSelectedItem(), 0);
				// 新增书籍数据
				rModel.books.add(book);
				try {
					rModel.bookDao.insert(book);
				} catch (Exception e1) {
					System.out.println("新增书籍失败");
				}
				bmf.DataIni();
				// 应用至树
				// if (book.getClassfy() == "最近阅读") {
				// DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
				// book.getBookName());
				// BookManagerForm.node1.add(newNode);
				// } else if (book.getClassfy() == "历史阅读") {
				// DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
				// book.getBookName());
				// BookManagerForm.node2.add(newNode);
				// }
				dispose();
			}
		});
		btn_OK.setBounds(66, 177, 93, 23);
		contentPane.add(btn_OK);

		JButton btn_Cancel = new JButton("取消");
		btn_Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_Cancel.setBounds(203, 177, 93, 23);
		contentPane.add(btn_Cancel);

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
		tf_BookName.setBounds(23, 130, 124, 21);
		contentPane.add(tf_BookName);
		tf_BookName.setColumns(10);

		JButton btn_Select = new JButton("选择");
		btn_Select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				if (jfc.showOpenDialog(AddBookForm.this) == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					if (file == null)
						return;
					String fileName = file.getName().replaceAll(".txt", "");// 获得文件名
					String str_filePath = file.getAbsolutePath();
					ta_Path.setText(str_filePath);
					tf_BookName.setText(fileName);
				}
			}
		});
		btn_Select.setBounds(240, 40, 66, 32);
		contentPane.add(btn_Select);

	}
}
