package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import util.MyUtils;
import Model.BookData;

public class BookManagerForm extends JFrame {

	private JPanel contentPane;

	public static DefaultMutableTreeNode node1 = null;
	public static DefaultMutableTreeNode node2 = null;

	/**
	 * Create the frame.
	 */
	public BookManagerForm() {
		setTitle("书籍管理");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 307, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		MyUtils.SetFromShowCenter(BookManagerForm.this);
		final JTree tree = new JTree();
		node1 = new DefaultMutableTreeNode("最近阅读");
		node2 = new DefaultMutableTreeNode("历史阅读");
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("书籍管理") {
			{
				add(node1);
				add(node2);
			}
		}));
		tree.setBounds(0, 0, 168, 261);
		contentPane.add(tree);

		JButton btn_Add = new JButton("新增");
		btn_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBookForm add = new AddBookForm();
				add.setVisible(true);
			}
		});
		btn_Add.setBounds(178, 30, 93, 23);
		contentPane.add(btn_Add);

		JButton btn_Del = new JButton("删除");
		btn_Del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				if (selectedNode != null && selectedNode.getParent() != null) {
					DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
					// 删除指定节点
					model.removeNodeFromParent(selectedNode);
				}
			}
		});
		btn_Del.setBounds(178, 89, 93, 23);
		contentPane.add(btn_Del);

		JButton btn_Read = new JButton("阅读");
		btn_Read.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MainForm.mainForm != null)
					MainForm.mainForm.setVisible(false);
				setVisible(false);
				ReadForm rd = new ReadForm();
				rd.setVisible(true);
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				if (selectedNode == null)
					return;
				BookData open = null;
				for (BookData book : AddBookForm.books) {
					if (book.getBookName().trim() == selectedNode.toString()) {
						open = book;
						break;
					}
				}
				if (open != null)
					rd.OpenBook(open);

			}
		});
		btn_Read.setBounds(178, 152, 93, 23);
		contentPane.add(btn_Read);

		JButton btn_Back = new JButton("返回上一级");
		btn_Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_Back.setBounds(178, 218, 104, 23);
		contentPane.add(btn_Back);
		DataIni();
	}

	private void DataIni() {
		for (BookData book : AddBookForm.books) {
			if (book.getClassfy().contains("最近阅读")) {
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
						book.getBookName());
				node1.add(newNode);
			} else if (book.getClassfy().contains("历史阅读")) {
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
						book.getBookName());
				node2.add(newNode);
			}
		}
	}

}
