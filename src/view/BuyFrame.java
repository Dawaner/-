package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import code.BookData;
import code.BuyData;
import code.Main;
import code.PayData;
import code.StringUtil;
import dao.BuyDataDao;

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
import javax.swing.JTable;

public class BuyFrame {

	public JFrame frame;
	private JTextField FBuyid;
	private JTextField FBuypayid;
	private JTextField FBuysendid;
	private JTextField FBuytime;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	
	private Main main = new Main();
	private BuyDataDao buyDataDao = new BuyDataDao();
	private JScrollPane scrollPane;
	private JTable buytable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyFrame window = new BuyFrame();
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
	public BuyFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u8BA2\u5355\u7BA1\u7406");
		frame.setBounds(100, 100, 670, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel FBookId = new JLabel("\u8BA2\u5355\u7F16\u53F7\uFF1A");
		FBookId.setBounds(31, 29, 80, 19);
		FBookId.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JLabel FBookName = new JLabel("\u4ED8\u6B3E\u65B9\u5F0F\u7F16\u53F7\uFF1A");
		FBookName.setBounds(239, 29, 118, 19);
		FBookName.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JLabel FBookSales = new JLabel("\u53D1\u8D27\u624B\u6BB5\u7F16\u53F7\uFF1A");
		FBookSales.setBounds(429, 29, 112, 19);
		FBookSales.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JLabel FBookSynopsis = new JLabel("\u8D2D\u4E70\u65E5\u671F(\u6708/\u65E5/\u5E74)\uFF1A");
		FBookSynopsis.setBounds(31, 72, 160, 19);
		FBookSynopsis.setFont(new Font("宋体", Font.PLAIN, 16));
		
		FBuyid = new JTextField();
		FBuyid.setBounds(115, 26, 106, 25);
		FBuyid.setFont(new Font("宋体", Font.PLAIN, 16));
		FBuyid.setColumns(10);
		
		FBuypayid = new JTextField();
		FBuypayid.setBounds(354, 26, 50, 25);
		FBuypayid.setFont(new Font("宋体", Font.PLAIN, 16));
		FBuypayid.setColumns(10);
		
		FBuysendid = new JTextField();
		FBuysendid.setBounds(545, 26, 50, 25);
		FBuysendid.setFont(new Font("宋体", Font.PLAIN, 16));
		FBuysendid.setColumns(10);
		
		FBuytime = new JTextField();
		FBuytime.setBounds(201, 69, 128, 25);
		FBuytime.setFont(new Font("宋体", Font.PLAIN, 16));
		FBuytime.setColumns(10);
		
		btnNewButton = new JButton("\u9000\u51FA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();//销毁当前窗口
			}
		});
		btnNewButton.setBounds(545, 404, 81, 27);
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		
		btnNewButton_1 = new JButton("\u65B0\u589E");
		btnNewButton_1.setIcon(new ImageIcon(BuyFrame.class.getResource("/picture/add.png")));
		btnNewButton_1.setBounds(31, 115, 97, 27);
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuyAddActionPerformed(e);
				//清空
				fillTable(new BuyData());
				//显示
				frame.setVisible(true);
			}
		});
		
		btnNewButton_2 = new JButton("\u4FEE\u6539");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuyUpdateActionEvent(e);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(BuyFrame.class.getResource("/picture/modify.png")));
		btnNewButton_2.setBounds(278, 115, 97, 27);
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 16));
		
		btnNewButton_3 = new JButton("\u5220\u9664");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuyDeleteActionEvent(e);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(BuyFrame.class.getResource("/picture/delete.png")));
		btnNewButton_3.setBounds(152, 115, 97, 27);
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 16));
		
		btnNewButton_4 = new JButton("\u67E5\u8BE2");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuySearchActionPerformed(e);
			}
		});
		btnNewButton_4.setIcon(new ImageIcon(BuyFrame.class.getResource("/picture/search.png")));
		btnNewButton_4.setBounds(403, 115, 97, 27);
		btnNewButton_4.setFont(new Font("宋体", Font.PLAIN, 16));
		
		btnNewButton_5 = new JButton("\u91CD\u7F6E");
		btnNewButton_5.setIcon(new ImageIcon(BuyFrame.class.getResource("/picture/reset.png")));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				重置
				FBuyid.setText("");
				FBuysendid.setText("");
				FBuypayid.setText("");
				FBuytime.setText("");
			}
		});
		btnNewButton_5.setBounds(525, 115, 97, 27);
		btnNewButton_5.setFont(new Font("宋体", Font.PLAIN, 16));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(FBookId);
		frame.getContentPane().add(FBuyid);
		frame.getContentPane().add(FBookName);
		frame.getContentPane().add(FBookSales);
		frame.getContentPane().add(FBuysendid);
		frame.getContentPane().add(FBookSynopsis);
		frame.getContentPane().add(FBuypayid);
		frame.getContentPane().add(FBuytime);
		frame.getContentPane().add(btnNewButton_1);
		frame.getContentPane().add(btnNewButton_3);
		frame.getContentPane().add(btnNewButton_2);
		frame.getContentPane().add(btnNewButton_4);
		frame.getContentPane().add(btnNewButton_5);
		frame.getContentPane().add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 159, 591, 236);
		frame.getContentPane().add(scrollPane);
		
		buytable = new JTable();
//		点击
		buytable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				BuyTableMousePressed(e);
			}
		});
		buytable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BA2\u5355\u7F16\u53F7", "\u652F\u4ED8\u65B9\u5F0F\u7F16\u53F7", "\u53D1\u8D27\u65B9\u5F0F\u7F16\u53F7", "\u8D2D\u4E70\u65E5\u671F(\u6708/\u65E5/\u5E74)"
			}
		));
		scrollPane.setViewportView(buytable);
		
		// 设置Frame居中显示
		this.frame.setLocationRelativeTo(null);
		// 只关闭当前窗口
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//清空
		this.fillTable(new BuyData());
		//显示
		frame.setVisible(true);
	}
	
//	查询
	private void BuySearchActionPerformed(ActionEvent evts) {
		// TODO 自动生成的方法存根
		String buyid =this.FBuyid.getText();
		String buypayid =this.FBuypayid.getText();
		String buysendid =this.FBuysendid.getText();
		String buytime =this.FBuytime.getText();
		BuyData buyData=new BuyData();
		buyData.setBuyId(buyid);
		buyData.setBuyPayId(buypayid);
		buyData.setBuySendId(buysendid);
		buyData.setBuyTime(buytime);
		this.fillTable(buyData);
	}
	
//	增加
	protected void BuyAddActionPerformed(ActionEvent evt) {
		String fbuyid = this.FBuyid.getText();
		String fbuypayid = this.FBuypayid.getText();
		String fbuysendid = this.FBuysendid.getText();
		String fbuytime = this.FBuytime.getText();
		if(StringUtil.isempty(fbuyid)){
			JOptionPane.showMessageDialog(null, "订单编号不能为空！");
			return;
		}
		if(StringUtil.isempty(fbuypayid)){
			JOptionPane.showMessageDialog(null, "支付方式编号不能为空！");
			return;
		}
		if(StringUtil.isempty(fbuysendid)){
			JOptionPane.showMessageDialog(null, "发货方式编号不能为空！");
			return;
		}
		if(StringUtil.isempty(fbuytime)){
			JOptionPane.showMessageDialog(null, "购买日期为空！");
			return;
		}
		BuyData buyData=new BuyData(fbuyid, fbuypayid, fbuysendid, fbuytime);
		Connection con=null;
		try {
			con=main.OpenOracleConnection();
			int n = buyDataDao.addbuy(con, buyData);
			if (n==1) {
				JOptionPane.showMessageDialog(null, "订单添加成功！");
				//重置
				FBuyid.setText("");
				FBuypayid.setText("");
				FBuysendid.setText("");
				FBuytime.setText("");
			}else {
				JOptionPane.showMessageDialog(null, "订单添加失败！");
				//重置
				FBuyid.setText("");
				FBuypayid.setText("");
				FBuysendid.setText("");
				FBuytime.setText("");
			}
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "订单添加失败！");
		}finally {
			try {
				main.CloseOracleConnection(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
//	清空	
	public void fillTable(BuyData buyData) {
		DefaultTableModel dtm=(DefaultTableModel) buytable.getModel();
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
	
//	删除
	private void BuyDeleteActionEvent(ActionEvent evt) {
		String fbuyid = this.FBuyid.getText();
		String fbuypayid = this.FBuypayid.getText();
		String fbuysendid = this.FBuysendid.getText();
		String fbuytime = this.FBuytime.getText();
		if(StringUtil.isempty(fbuyid)){
			JOptionPane.showMessageDialog(null, "订单编号不能为空！");
			return;
		}
		if(StringUtil.isempty(fbuypayid)){
			JOptionPane.showMessageDialog(null, "支付方式编号不能为空！");
			return;
		}
		if(StringUtil.isempty(fbuysendid)){
			JOptionPane.showMessageDialog(null, "发货方式编号不能为空！");
			return;
		}
		if(StringUtil.isempty(fbuytime)){
			JOptionPane.showMessageDialog(null, "购买日期为空！");
			return;
		}
		BuyData buyData=new BuyData(fbuyid, fbuypayid, fbuysendid, fbuytime);
		int n=JOptionPane.showConfirmDialog(null, "确定要删除该记录吗？");
		if(n==0){
			Connection con=null;
			try{
				con=main.OpenOracleConnection();
				int deletelist=buyDataDao.delete(con, buyData);
				if(deletelist==1 || deletelist>1){
					JOptionPane.showMessageDialog(null, "删除成功");
					//重置
					FBuyid.setText("");
					FBuypayid.setText("");
					FBuysendid.setText("");
					FBuytime.setText("");
					this.fillTable(new BuyData());
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
	private void BuyUpdateActionEvent(ActionEvent evt) {
		String fbuyid = FBuyid.getText();
		String fbuypayid = FBuypayid.getText();
		String fbuysendid = FBuysendid.getText();
		String fbuytime = FBuytime.getText();
		if(StringUtil.isempty(fbuyid)){
			JOptionPane.showMessageDialog(null, "订单编号不能修改！");
			return;
		}
		if(StringUtil.isempty(fbuypayid)){
			JOptionPane.showMessageDialog(null, "支付方式编号不能为空！");
			return;
		}
		if(StringUtil.isempty(fbuysendid)){
			JOptionPane.showMessageDialog(null, "发货方式编号不能为空！");
			return;
		}
		if(StringUtil.isempty(fbuytime)){
			JOptionPane.showMessageDialog(null, "购买日期为空！");
			return;
		}
		BuyData buyData=new BuyData(fbuyid, fbuypayid, fbuysendid, fbuytime);
		Connection con=null;
		try{
			con=main.OpenOracleConnection();
			int modifyNum=buyDataDao.update(con, buyData);
			if(modifyNum==1){
				JOptionPane.showMessageDialog(null, "修改成功");
				//重置
				FBuyid.setText("");
				FBuypayid.setText("");
				FBuysendid.setText("");
				FBuytime.setText("");
				this.fillTable(new BuyData());
			}else{
				JOptionPane.showMessageDialog(null, "订单编号不能修改!");
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
	private void BuyTableMousePressed(MouseEvent evt) {
		int row=buytable.getSelectedRow();
		FBuyid.setText((String)buytable.getValueAt(row, 0));
		FBuypayid.setText((String)buytable.getValueAt(row, 1));
		FBuysendid.setText((String)buytable.getValueAt(row, 2));
		FBuytime.setText((String)buytable.getValueAt(row, 3));
	}
	
//	刷新
	private void review() {
		//清空
		fillTable(new BuyData());
		//显示
		frame.setVisible(true);
	}
}
