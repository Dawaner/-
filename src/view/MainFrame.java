package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JDesktopPane;

import view.BookFrame;
import view.BuyFrame;
import view.BuyerFrame;
import view.PayFrame;
import view.SendFrame;
import view.TypeFrame;

import code.Main;
import code.BookData;
import code.BuyData;
import code.BuyerData;
import code.PayData;
import code.SendData;
import code.TypeData;

import dao.BookDataDao;
import dao.BuyDataDao;
import dao.BuyerDataDao;
import dao.PayDataDao;
import dao.SendDataDao;
import dao.TypeDataDao;



import javax.swing.table.DefaultTableModel;

public class MainFrame {
	private Main main = new Main();
	private BookDataDao bookDataDao = new BookDataDao();
	private BuyDataDao buyDataDao = new BuyDataDao();
	private BuyerDataDao buyerDataDao = new BuyerDataDao();
	private PayDataDao payDataDao = new PayDataDao();
	private SendDataDao sendDataDao = new SendDataDao();
	private TypeDataDao typeDataDao = new TypeDataDao();
	

	public JFrame frame;
	private JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
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
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		table = new JTable();
		table.setEnabled(false);
		frame.setResizable(false);
		frame.setTitle("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		frame.setBounds(100, 100, 670, 553);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		查看库存
		JButton btnNewButton = new JButton("\u67E5\u770B\u5E93\u5B58");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//表格
				table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"\u56FE\u4E66\u7F16\u53F7", "\u56FE\u4E66\u540D\u79F0", "\u5355\u4EF7", "\u5185\u5BB9\u7B80\u4ECB", "\u51FA\u552E\u60C5\u51B5(1\u4E3A\u5DF2\u552E\u51FA)"
				}
						));
				table.getColumnModel().getColumn(0).setPreferredWidth(118);
				table.getColumnModel().getColumn(1).setPreferredWidth(118);
				
				//数据生成			
				BookData bookData =new BookData();
				DefaultTableModel dtm=(DefaultTableModel) table.getModel();
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
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
//		查看订单
		JButton btnNewButton_1 = new JButton("\u67E5\u770B\u8BA2\u5355");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//表格
				table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"\u8BA2\u5355\u7F16\u53F7", "\u652F\u4ED8\u65B9\u5F0F\u7F16\u53F7", "\u53D1\u8D27\u65B9\u5F0F\u7F16\u53F7", "\u8D2D\u4E70\u65E5\u671F(\u6708/\u65E5/\u5E74)"
				}
						));
				table.getColumnModel().getColumn(0).setPreferredWidth(118);
				table.getColumnModel().getColumn(1).setPreferredWidth(118);
				
				//数据生成			
				BuyData buyData =new BuyData();
				DefaultTableModel dtm=(DefaultTableModel) table.getModel();
				dtm.setRowCount(0); // 设置成0行
				Connection con=null;
				try{
					con=main.OpenOracleConnection();
					ResultSet rs=buyDataDao.list(con, buyData);
					while(rs.next()){
						Vector v=new Vector();
						v.add(rs.getString("Buy_id"));
						v.add(rs.getString("Buy_pay_id"));
						v.add(rs.getString("Buy_send_id"));
						v.add(rs.getString("Buy_time"));
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
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 16));
//		查看会员
		JButton btnNewButton_2 = new JButton("\u67E5\u770B\u4F1A\u5458");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//表格
				table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"\u59D3\u540D", "\u6027\u522B", "\u5E74\u9F84", "\u8054\u7CFB\u65B9\u5F0F", "\u4F1A\u5458\u7F16\u53F7"
				}
						));
				table.getColumnModel().getColumn(0).setPreferredWidth(118);
				table.getColumnModel().getColumn(1).setPreferredWidth(118);
				
				//数据生成			
				BuyerData buyerData =new BuyerData();
				DefaultTableModel dtm=(DefaultTableModel) table.getModel();
				dtm.setRowCount(0); // 设置成0行
				Connection con=null;
				try{
					con=main.OpenOracleConnection();
					ResultSet rs=buyerDataDao.list(con, buyerData);
					while(rs.next()){
						Vector v=new Vector();
						v.add(rs.getString("Buyer_name"));
						v.add(rs.getString("Buyer_gender"));
						v.add(rs.getString("Buyer_age"));
						v.add(rs.getString("Buyer_tele"));
						v.add(rs.getString("Buyer_id"));
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
		});
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 16));
//		查看书籍种类
		JButton btnNewButton_3 = new JButton("\u67E5\u770B\u4E66\u7C4D\u79CD\u7C7B");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//表格
				table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"\u79CD\u7C7B\u7F16\u53F7", "\u79CD\u7C7B\u540D\u79F0"
				}
						));
				table.getColumnModel().getColumn(0).setPreferredWidth(118);
				table.getColumnModel().getColumn(1).setPreferredWidth(118);
				
				//数据生成			
				TypeData typeData =new TypeData();
				DefaultTableModel dtm=(DefaultTableModel) table.getModel();
				dtm.setRowCount(0); // 设置成0行
				Connection con=null;
				try{
					con=main.OpenOracleConnection();
					ResultSet rs=typeDataDao.list(con, typeData);
					while(rs.next()){
						Vector v=new Vector();
						v.add(rs.getString("Type_id"));
						v.add(rs.getString("Type_name"));
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
		});
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 16));
//		查看支付方式
		JButton btnNewButton_4 = new JButton("\u67E5\u770B\u652F\u4ED8\u65B9\u5F0F");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//表格
				table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"\u652F\u4ED8\u65B9\u5F0F\u7F16\u53F7", "\u652F\u4ED8\u65B9\u5F0F\u540D\u79F0"
				}
						));
				table.getColumnModel().getColumn(0).setPreferredWidth(118);
				table.getColumnModel().getColumn(1).setPreferredWidth(118);
				
				//数据生成			
				PayData payData =new PayData();
				DefaultTableModel dtm=(DefaultTableModel) table.getModel();
				dtm.setRowCount(0); // 设置成0行
				Connection con=null;
				try{
					con=main.OpenOracleConnection();
					ResultSet rs=payDataDao.list(con, payData);
					while(rs.next()){
						Vector v=new Vector();
						v.add(rs.getString("Pay_id"));
						v.add(rs.getString("Pay_name"));
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
		});
		btnNewButton_4.setFont(new Font("宋体", Font.PLAIN, 16));
//		查看发货方式
		JButton btnNewButton_5 = new JButton("\u67E5\u770B\u53D1\u8D27\u65B9\u5F0F");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//表格
				table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"\u53D1\u8D27\u65B9\u5F0F\u7F16\u53F7", "\u53D1\u8D27\u65B9\u5F0F\u540D\u79F0"
				}
						));
				table.getColumnModel().getColumn(0).setPreferredWidth(118);
				table.getColumnModel().getColumn(1).setPreferredWidth(118);
				
				//数据生成			
				SendData sendData =new SendData();
				DefaultTableModel dtm=(DefaultTableModel) table.getModel();
				dtm.setRowCount(0); // 设置成0行
				Connection con=null;
				try{
					con=main.OpenOracleConnection();
					ResultSet rs=sendDataDao.list(con, sendData);
					while(rs.next()){
						Vector v=new Vector();
						v.add(rs.getString("Send_id"));
						v.add(rs.getString("Send_name"));
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
		});
		btnNewButton_5.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JButton btnNewButton_6 = new JButton("\u5E93\u5B58\u7BA1\u7406");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookFrame().frame.setVisible(true);//弹出新的窗口BookFrame
			}
		});
		btnNewButton_6.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JButton btnNewButton_7 = new JButton("\u8BA2\u5355\u7BA1\u7406");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BuyFrame().frame.setVisible(true);//弹出新的窗口BuyFrame
			}
		});
		btnNewButton_7.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JButton btnNewButton_8 = new JButton("\u4F1A\u5458\u7BA1\u7406");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BuyerFrame().frame.setVisible(true);//弹出新的窗口BuyerFrame
			}
		});
		btnNewButton_8.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JButton btnNewButton_9 = new JButton("\u4E66\u7C4D\u79CD\u7C7B\u7BA1\u7406");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TypeFrame().frame.setVisible(true);//弹出新的窗口Frame
			}
		});
		btnNewButton_9.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JButton btnNewButton_10 = new JButton("\u652F\u4ED8\u65B9\u5F0F\u7BA1\u7406");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PayFrame().frame.setVisible(true);//弹出新的窗口PayFrame
			}
		});
		btnNewButton_10.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JButton btnNewButton_11 = new JButton("\u53D1\u8D27\u65B9\u5F0F\u7BA1\u7406");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SendFrame().frame.setVisible(true);//弹出新的窗口SendFrame
			}
		});
		btnNewButton_11.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnNewButton_3)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
								.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_4, Alignment.TRAILING)
								.addComponent(btnNewButton_5, Alignment.TRAILING))))
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNewButton_6, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
						.addComponent(btnNewButton_7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNewButton_9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(51))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(47)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 567, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(52, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_6)
						.addComponent(btnNewButton_9)
						.addComponent(btnNewButton_3))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_4)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_7)
						.addComponent(btnNewButton_10))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_5)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_8)
						.addComponent(btnNewButton_11))
					.addGap(28))
		);
		
		//table
		scrollPane.setViewportView(table);
		frame.getContentPane().setLayout(groupLayout);
		// 设置Frame居中显示
		this.frame.setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u5E2E\u52A9");
		mnNewMenu.setIcon(new ImageIcon(MainFrame.class.getResource("/picture/base.png")));
		mnNewMenu.setFont(new Font("宋体", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u5173\u4E8E");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//弹出新的窗口
				new AboutUs().frame.setVisible(true);
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/picture/about.png")));
		mntmNewMenuItem.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		mnNewMenu.add(mntmNewMenuItem);
			
	}
}
