package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTree;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class BookManagerForm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookManagerForm frame = new BookManagerForm();
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
	public BookManagerForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 307, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("书籍管理") {
				{
					add(new DefaultMutableTreeNode("最近阅读"));
					add(new DefaultMutableTreeNode("历史阅读"));
				}
			}
		));
		tree.setBounds(0, 0, 168, 261);
		contentPane.add(tree);
		
		JButton btn_Add = new JButton("新增书籍");
		btn_Add.setBounds(178, 30, 93, 23);
		contentPane.add(btn_Add);
		
		JButton btn_Del = new JButton("删除");
		btn_Del.setBounds(178, 89, 93, 23);
		contentPane.add(btn_Del);
		
		JButton btn_Read = new JButton("开始阅读");
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
	}
}
