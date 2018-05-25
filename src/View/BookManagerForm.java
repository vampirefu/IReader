package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import util.MyUtils;
import Model.BookData;
import Model.ReadData;
import Model.ReadModel;

public class BookManagerForm extends JFrame {

	private JPanel contentPane;

	public static DefaultMutableTreeNode node1 = null;
	public static DefaultMutableTreeNode node2 = null;
	private ReadModel rModel = null;
	private JTree tree;

	/**
	 * Create the frame.
	 */
	public BookManagerForm(final ReadModel rModel) {
		this.rModel = rModel;
		setTitle("书籍管理");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 307, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		MyUtils.SetFromShowCenter(BookManagerForm.this);

		// 树
		tree = new JTree();
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

		// 新增按钮
		JButton btn_Add = new JButton("新增");
		btn_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBookForm add = new AddBookForm(rModel);
				add.setVisible(true);
			}
		});
		btn_Add.setBounds(178, 30, 93, 23);
		contentPane.add(btn_Add);

		// 删除按钮
		JButton btn_Del = new JButton("删除");
		btn_Del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 当前节点
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				if (selectedNode.toString() == "书籍管理"
						|| selectedNode.toString() == "最近阅读"
						|| selectedNode.toString() == "历史阅读") {
					JOptionPane.showMessageDialog(null, "该节点无法删除", "提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if (selectedNode != null && selectedNode.getParent() != null) {
					DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
					// 删除指定节点
					model.removeNodeFromParent(selectedNode);
					for (int i = 0; i < rModel.books.size(); i++) {
						BookData bd = rModel.books.get(i);
						if (bd.getBookName().contains(selectedNode.toString())) {
							// 数据库同步
							try {
								rModel.curRead = rModel.readDao.select(bd
										.getPath());
								rModel.bookDao.delete(bd);
								if (rModel.curRead != null
										&& bd.getPath() == rModel.curRead
												.getFk_path()) {
									rModel.readDao.delete(rModel.curRead);
								}
							} catch (Exception e1) {
								System.out.println("删除书籍失败");
							}
							rModel.books.remove(bd);
							i--;
						}
					}
				}
			}
		});
		btn_Del.setBounds(178, 89, 93, 23);
		contentPane.add(btn_Del);

		// 阅读按钮
		JButton btn_Read = new JButton("阅读");
		btn_Read.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MainForm.mainForm != null)
					MainForm.mainForm.setVisible(false);
				setVisible(false);
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				if (selectedNode == null)
					return;
				//获取节点对应的数据
				for (BookData book : rModel.books) {
					if (book.getBookName().trim() == selectedNode.toString()) {
						// 设置当前阅读
						rModel.curBook = book;
						break;
					}
				}											
				ReadForm rd = new ReadForm(rModel);
				rd.setVisible(true);
				if (rModel.curBook != null)
					rd.OpenBook(rModel.curBook, BookManagerForm.this);
			}
		});
		btn_Read.setBounds(178, 152, 93, 23);
		contentPane.add(btn_Read);
		// 按钮
		JButton btn_Back = new JButton("返回上一级");
		btn_Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_Back.setBounds(178, 218, 104, 23);
		contentPane.add(btn_Back);
		// 树数据初始化
		DataIni();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				if (MainForm.mainForm != null)
					MainForm.mainForm.setVisible(true);
			}
		});
	}

	private void DataIni() {
		for (BookData book : rModel.books) {
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
