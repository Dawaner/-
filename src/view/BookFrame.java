package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import code.Main;
import code.PayData;
import code.StringUtil;
import code.BookData;
import dao.BookDataDao;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;


public class BookFrame {

	public JFrame frame;
	private JTextField FBookid;
	private JTextField FBookname;
	private JTextField FBookprice;
	private JTextField FBooksales;
	private JTextField FBooksynopsis;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JTable booktable;
	
	private Main main = new Main();
	private BookDataDao bookDataDao = new BookDataDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookFrame window = new BookFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BookFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u5E93\u5B58\u7BA1\u7406");
		frame.setBounds(100, 100, 670, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel FBookId = new JLabel("\u56FE\u4E66\u7F16\u53F7\uFF1A");
		FBookId.setBounds(31, 29, 80, 19);
		FBookId.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JLabel FBookName = new JLabel("\u56FE\u4E66\u540D\u79F0\uFF1A");
		FBookName.setBounds(239, 29, 80, 19);
		FBookName.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JLabel FBookPrice = new JLabel("\u5355\u4EF7\uFF1A");
		FBookPrice.setBounds(486, 29, 52, 19);
		FBookPrice.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JLabel FBookSales = new JLabel("\u51FA\u552E\u60C5\u51B5\uFF1A");
		FBookSales.setBounds(31, 72, 80, 19);
		FBookSales.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JLabel FBookSynopsis = new JLabel("\u5185\u5BB9\u7B80\u4ECB\uFF1A");
		FBookSynopsis.setBounds(239, 72, 80, 19);
		FBookSynopsis.setFont(new Font("宋体", Font.PLAIN, 16));
		
		FBookid = new JTextField();
		FBookid.setBounds(115, 26, 106, 25);
		FBookid.setFont(new Font("宋体", Font.PLAIN, 16));
		FBookid.setColumns(10);
		
		FBookname = new JTextField();
		FBookname.setBounds(323, 26, 140, 25);
		FBookname.setFont(new Font("宋体", Font.PLAIN, 16));
		FBookname.setColumns(10);
		
		FBookprice = new JTextField();
		FBookprice.setBounds(542, 26, 80, 25);
		FBookprice.setFont(new Font("宋体", Font.PLAIN, 16));
		FBookprice.setColumns(10);
		
		FBooksales = new JTextField();
		FBooksales.setBounds(115, 69, 86, 25);
		FBooksales.setFont(new Font("宋体", Font.PLAIN, 16));
		FBooksales.setColumns(10);
		
		FBooksynopsis = new JTextField();
		FBooksynopsis.setBounds(323, 69, 299, 25);
		FBooksynopsis.setFont(new Font("宋体", Font.PLAIN, 16));
		FBooksynopsis.setColumns(10);
		
		btnNewButton = new JButton("\u9000\u51FA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();//销毁当前窗口
			}
		});
		btnNewButton.setBounds(546, 404, 80, 27);
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		
		btnNewButton_1 = new JButton("\u65B0\u589E");
		btnNewButton_1.setIcon(new ImageIcon(BookFrame.class.getResource("/picture/add.png")));
		btnNewButton_1.setBounds(31, 115, 97, 27);
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookAddActionPerformed(e);
				//清空
				fillTable(new BookData());
				//显示
				frame.setVisible(true);
			}
		});
		
		btnNewButton_2 = new JButton("\u4FEE\u6539");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookUpdateActionEvent(e);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(BookFrame.class.getResource("/picture/modify.png")));
		btnNewButton_2.setBounds(278, 115, 97, 27);
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 16));
		
		btnNewButton_3 = new JButton("\u5220\u9664");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookDeleteActionEvent(e);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(BookFrame.class.getResource("/picture/delete.png")));
		btnNewButton_3.setBounds(152, 115, 97, 27);
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 16));
		
		btnNewButton_4 = new JButton("\u67E5\u8BE2");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookSearchActionPerformed(e);
			}
		});
		btnNewButton_4.setIcon(new ImageIcon(BookFrame.class.getResource("/picture/search.png")));
		btnNewButton_4.setBounds(403, 115, 97, 27);
		btnNewButton_4.setFont(new Font("宋体", Font.PLAIN, 16));
		
		btnNewButton_5 = new JButton("\u91CD\u7F6E");
		btnNewButton_5.setIcon(new ImageIcon(BookFrame.class.getResource("/picture/reset.png")));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				重置
				FBookid.setText("");
				FBookname.setText("");
				FBooksales.setText("");
				FBookprice.setText("");
				FBooksynopsis.setText("");
			}
		});
		btnNewButton_5.setBounds(525, 115, 97, 27);
		btnNewButton_5.setFont(new Font("宋体", Font.PLAIN, 16));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(FBookId);
		frame.getContentPane().add(FBookid);
		frame.getContentPane().add(FBookName);
		frame.getContentPane().add(FBookSales);
		frame.getContentPane().add(FBooksales);
		frame.getContentPane().add(FBookSynopsis);
		frame.getContentPane().add(FBookname);
		frame.getContentPane().add(FBooksynopsis);
		frame.getContentPane().add(btnNewButton_1);
		frame.getContentPane().add(btnNewButton_3);
		frame.getContentPane().add(btnNewButton_2);
		frame.getContentPane().add(btnNewButton_4);
		frame.getContentPane().add(FBookPrice);
		frame.getContentPane().add(FBookprice);
		frame.getContentPane().add(btnNewButton_5);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 173, 589, 223);
		frame.getContentPane().add(scrollPane);
		
		booktable = new JTable();
//		点击
		booktable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				BookTableMousePressed(e);
			}
		});
		booktable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u56FE\u4E66\u7F16\u53F7", "\u56FE\u4E66\u540D\u79F0", "\u5355\u4EF7", "\u5185\u5BB9\u7B80\u4ECB", "\u51FA\u552E\u60C5\u51B5(1\u4E3A\u51FA\u552E)"
			}
		));
		booktable.getColumnModel().getColumn(4).setPreferredWidth(218);
		scrollPane.setViewportView(booktable);
		
		//设置Frame居中显示
		this.frame.setLocationRelativeTo(null);
		//只关闭当前窗口
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//清空
		this.fillTable(new BookData());
		//显示
		frame.setVisible(true);
	}
	
//	查询
	private void BookSearchActionPerformed(ActionEvent evts) {
		// TODO 自动生成的方法存根
		String bookid =this.FBookid.getText();
		String bookname =this.FBookname.getText();
		String bookprice =this.FBookprice.getText();
		String booksynopsis =this.FBooksynopsis.getText();
		String booksales =this.FBooksales.getText();
		BookData bookData=new BookData();
		bookData.setBookId(bookid);
		bookData.setBookName(bookname);
		bookData.setBookPrice(bookprice);
		bookData.setBookSynopsis(booksynopsis);
		bookData.setBookSales(booksales);
		this.fillTable(bookData);
	}
	
//	增加
	protected void BookAddActionPerformed(ActionEvent evt) {
		String fbookid = this.FBookid.getText();
		String fbookname = this.FBookname.getText();
		String fbookprice = this.FBookprice.getText();
		String fbooksynopsis = this.FBooksynopsis.getText();
		String fbooksales = this.FBooksales.getText();
		if(StringUtil.isempty(fbookid)){
			JOptionPane.showMessageDialog(null, "图书编号不能为空！");
			return;
		}
		if(StringUtil.isempty(fbookname)){
			JOptionPane.showMessageDialog(null, "图书名称不能为空！");
			return;
		}
		if(StringUtil.isempty(fbookprice)){
			JOptionPane.showMessageDialog(null, "单价不能为空！");
			return;
		}
		if(StringUtil.isempty(fbooksynopsis)){
			JOptionPane.showMessageDialog(null, "内容简介不能为空！");
			return;
		}
		if(StringUtil.isempty(fbooksales)){
			JOptionPane.showMessageDialog(null, "出售情况不能为空！");
			return;
		}
		BookData bookData=new BookData(fbookid, fbookname, fbookprice, fbooksynopsis, fbooksales);
		Connection con=null;
		try {
			con=main.OpenOracleConnection();
			int n = bookDataDao.addbook(con, bookData);
			if (n==1) {
				JOptionPane.showMessageDialog(null, "图书添加成功！");
				//重置
				FBookid.setText("");
				FBookname.setText("");
				FBookprice.setText("");
				FBooksynopsis.setText("");
				FBooksales.setText("");
			}else {
				JOptionPane.showMessageDialog(null, "图书添加失败！");
				//重置
				FBookid.setText("");
				FBookname.setText("");
				FBookprice.setText("");
				FBooksynopsis.setText("");
				FBooksales.setText("");
			}
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "图书添加失败！");
		}finally {
			try {
				main.CloseOracleConnection(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
//	清空	
	public void fillTable(BookData bookData) {
		DefaultTableModel dtm=(DefaultTableModel) booktable.getModel();
		dtm.setRowCount(0); // 设置成0行
		Connection con=null;
		try{
			con=main.OpenOracleConnection();
			ResultSet rs=bookDataDao.list(con, bookData);
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("Book_id"));
				v.add(rs.getString("Book_name"));
				v.add(rs.getString("Book_price"));
				v.add(rs.getString("Book_synopsis"));
				v.add(rs.getString("Book_sales"));
				dtm.addRow(v);
			}
		}catch(Exception evt){
			evt.printStackTrace();
		}finally{
			try {
				main.CloseOracleConnection(con);
			} catch (Exception evt) {
				// TODO Auto-generated catch block
				evt.printStackTrace();
			}
		}
	}
	
//	删除
	private void BookDeleteActionEvent(ActionEvent evt) {
		String fbookid = this.FBookid.getText();
		String fbookname = this.FBookname.getText();
		String fbookprice = this.FBookprice.getText();
		String fbooksynopsis = this.FBooksynopsis.getText();
		String fbooksales = this.FBooksales.getText();
		if(StringUtil.isempty(fbookid)){
			JOptionPane.showMessageDialog(null, "图书编号不能为空！");
			return;
		}
		if(StringUtil.isempty(fbookname)){
			JOptionPane.showMessageDialog(null, "图书名称不能为空！");
			return;
		}
		if(StringUtil.isempty(fbookprice)){
			JOptionPane.showMessageDialog(null, "单价不能为空！");
			return;
		}
		if(StringUtil.isempty(fbooksynopsis)){
			JOptionPane.showMessageDialog(null, "内容简介不能为空！");
			return;
		}
		if(StringUtil.isempty(fbooksales)){
			JOptionPane.showMessageDialog(null, "出售情况不能为空！");
			return;
		}
		BookData bookData=new BookData(fbookid,fbookname,fbookprice,fbooksynopsis,fbooksales);
		int n=JOptionPane.showConfirmDialog(null, "确定要删除该记录吗？");
		if(n==0){
			Connection con=null;
			try{
				con=main.OpenOracleConnection();
				int deletelist=bookDataDao.delete(con, bookData);
				if(deletelist==1 || deletelist>1){
					JOptionPane.showMessageDialog(null, "删除成功");
					//重置
					FBookid.setText("");
					FBookname.setText("");
					FBookprice.setText("");
					FBooksynopsis.setText("");
					FBooksales.setText("");
					this.fillTable(new BookData());
				}else{
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			}catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "删除失败");
			}finally{
				try {
					main.CloseOracleConnection(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		review();
	}
	
//	修改
	private void BookUpdateActionEvent(ActionEvent evt) {
		String fbookid = FBookid.getText();
		String fbookname = FBookname.getText();
		String fbookprice = FBookprice.getText();
		String fbooksynopsis = FBooksynopsis.getText();
		String fbooksales = FBooksales.getText();
		if(StringUtil.isempty(fbookid)){
			JOptionPane.showMessageDialog(null, "图书编号不能修改");
			return;
		}
		if(StringUtil.isempty(fbookname)){
			JOptionPane.showMessageDialog(null, "图书名称不能为空！");
			return;
		}
		if(StringUtil.isempty(fbookprice)){
			JOptionPane.showMessageDialog(null, "单价不能为空！");
			return;
		}
		if(StringUtil.isempty(fbooksynopsis)){
			JOptionPane.showMessageDialog(null, "内容简介不能为空！");
			return;
		}
		if(StringUtil.isempty(fbooksales)){
			JOptionPane.showMessageDialog(null, "出售情况不能为空！");
			return;
		}
		BookData bookData=new BookData(fbookid,fbookname,fbookprice,fbooksynopsis,fbooksales);
		Connection con=null;
		try{
			con=main.OpenOracleConnection();
			int modifyNum=bookDataDao.update(con, bookData);
			if(modifyNum==1){
				JOptionPane.showMessageDialog(null, "修改成功");
				//重置
				FBookid.setText("");
				FBookname.setText("");
				FBookprice.setText("");
				FBooksynopsis.setText("");
				FBooksales.setText("");
				this.fillTable(new BookData());
			}else{
				JOptionPane.showMessageDialog(null, "图书编号不能修改!");
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "修改失败");
		}finally{
			try {
				main.CloseOracleConnection(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
//	点击
	private void BookTableMousePressed(MouseEvent evt) {
		int row=booktable.getSelectedRow();
		FBookid.setText((String)booktable.getValueAt(row, 0));
		FBookname.setText((String)booktable.getValueAt(row, 1));
		FBookprice.setText((String)booktable.getValueAt(row, 2));
		FBooksynopsis.setText((String)booktable.getValueAt(row, 3));
		FBooksales.setText((String)booktable.getValueAt(row, 4));
	}
	
//	刷新
	private void review() {
		//清空
		fillTable(new BookData());
		//显示
		frame.setVisible(true);
	}
}
