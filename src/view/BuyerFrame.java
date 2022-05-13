package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import code.BookData;
import code.BuyerData;
import code.Main;
import code.PayData;
import code.StringUtil;
import dao.BuyerDataDao;

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

public class BuyerFrame {

	public JFrame frame;
	private JTextField FBuyername;
	private JTextField FBuyerage;
	private JTextField FBuyergender;
	private JTextField FBuyerid;
	private JTextField FBuyertele;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	
	private Main main = new Main();
	private BuyerDataDao buyerDataDao = new BuyerDataDao();
	private JScrollPane scrollPane;
	private JTable buyertable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyerFrame window = new BuyerFrame();
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
	public BuyerFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u4F1A\u5458\u7BA1\u7406");
		frame.setBounds(100, 100, 670, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel FBuyerName = new JLabel("\u59D3\u540D\uFF1A");
		FBuyerName.setBounds(31, 29, 80, 19);
		FBuyerName.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JLabel FBookName = new JLabel("\u6027\u522B\uFF1A");
		FBookName.setBounds(306, 73, 48, 19);
		FBookName.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JLabel FBookPrice = new JLabel("\u4F1A\u5458\u7F16\u53F7\uFF1A");
		FBookPrice.setBounds(31, 72, 80, 19);
		FBookPrice.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JLabel FBookSales = new JLabel("\u5E74\u9F84\uFF1A");
		FBookSales.setBounds(181, 29, 48, 19);
		FBookSales.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JLabel FBookSynopsis = new JLabel("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
		FBookSynopsis.setBounds(307, 29, 80, 19);
		FBookSynopsis.setFont(new Font("宋体", Font.PLAIN, 16));
		
		FBuyername = new JTextField();
		FBuyername.setBounds(80, 26, 80, 25);
		FBuyername.setFont(new Font("宋体", Font.PLAIN, 16));
		FBuyername.setColumns(10);
		
		FBuyerage = new JTextField();
		FBuyerage.setBounds(227, 26, 54, 25);
		FBuyerage.setFont(new Font("宋体", Font.PLAIN, 16));
		FBuyerage.setColumns(10);
		
		FBuyergender = new JTextField();
		FBuyergender.setBounds(351, 70, 48, 25);
		FBuyergender.setFont(new Font("宋体", Font.PLAIN, 16));
		FBuyergender.setColumns(10);
		
		FBuyerid = new JTextField();
		FBuyerid.setBounds(109, 70, 172, 25);
		FBuyerid.setFont(new Font("宋体", Font.PLAIN, 16));
		FBuyerid.setColumns(10);
		
		FBuyertele = new JTextField();
		FBuyertele.setBounds(394, 26, 149, 25);
		FBuyertele.setFont(new Font("宋体", Font.PLAIN, 16));
		FBuyertele.setColumns(10);
		
		btnNewButton = new JButton("\u9000\u51FA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();//销毁当前窗口
			}
		});
		btnNewButton.setBounds(546, 404, 80, 27);
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		
		btnNewButton_1 = new JButton("\u65B0\u589E");
		btnNewButton_1.setIcon(new ImageIcon(BuyerFrame.class.getResource("/picture/add.png")));
		btnNewButton_1.setBounds(31, 115, 97, 27);
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuyerAddActionPerformed(e);
				//清空
				fillTable(new BuyerData());
				//显示
				frame.setVisible(true);
			}
		});
		
		btnNewButton_2 = new JButton("\u4FEE\u6539");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuyerUpdateActionEvent(e);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(BuyerFrame.class.getResource("/picture/modify.png")));
		btnNewButton_2.setBounds(278, 115, 97, 27);
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 16));
		
		btnNewButton_3 = new JButton("\u5220\u9664");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuyerDeleteActionEvent(e);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(BuyerFrame.class.getResource("/picture/delete.png")));
		btnNewButton_3.setBounds(152, 115, 97, 27);
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 16));
		
		btnNewButton_4 = new JButton("\u67E5\u8BE2");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuyerSearchActionPerformed(e);
			}
		});
		btnNewButton_4.setIcon(new ImageIcon(BuyerFrame.class.getResource("/picture/search.png")));
		btnNewButton_4.setBounds(403, 115, 97, 27);
		btnNewButton_4.setFont(new Font("宋体", Font.PLAIN, 16));
		
		btnNewButton_5 = new JButton("\u91CD\u7F6E");
		btnNewButton_5.setIcon(new ImageIcon(BuyerFrame.class.getResource("/picture/reset.png")));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				重置
				FBuyername.setText("");
				FBuyerid.setText("");
				FBuyerage.setText("");
				FBuyertele.setText("");
				FBuyergender.setText("");
			}
		});
		btnNewButton_5.setBounds(525, 115, 97, 27);
		btnNewButton_5.setFont(new Font("宋体", Font.PLAIN, 16));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(FBuyerName);
		frame.getContentPane().add(FBuyername);
		frame.getContentPane().add(FBookName);
		frame.getContentPane().add(FBookSales);
		frame.getContentPane().add(FBuyerid);
		frame.getContentPane().add(FBookSynopsis);
		frame.getContentPane().add(FBuyerage);
		frame.getContentPane().add(FBuyertele);
		frame.getContentPane().add(btnNewButton_1);
		frame.getContentPane().add(btnNewButton_3);
		frame.getContentPane().add(btnNewButton_2);
		frame.getContentPane().add(btnNewButton_4);
		frame.getContentPane().add(FBookPrice);
		frame.getContentPane().add(FBuyergender);
		frame.getContentPane().add(btnNewButton_5);
		frame.getContentPane().add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 166, 590, 230);
		frame.getContentPane().add(scrollPane);
		
		buyertable = new JTable();
//		点击
		buyertable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				BuyerTableMousePressed(e);
			}
		});
		buyertable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u59D3\u540D", "\u6027\u522B", "\u5E74\u9F84", "\u8054\u7CFB\u65B9\u5F0F", "\u4F1A\u5458\u7F16\u53F7"
			}
		));
		scrollPane.setViewportView(buyertable);
		
		// 设置Frame居中显示
		this.frame.setLocationRelativeTo(null);
		// 只关闭当前窗口
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//清空
		this.fillTable(new BuyerData());
		//显示
		frame.setVisible(true);			
	}
	
	private void BuyerSearchActionPerformed(ActionEvent evts) {
		// TODO 自动生成的方法存根
		String buyername =this.FBuyername.getText();
		String buyergender =this.FBuyergender.getText();
		String buyerage =this.FBuyerage.getText();
		String buyertele =this.FBuyertele.getText();
		String buyerid =this.FBuyerid.getText();
		BuyerData buyerData=new BuyerData();
		buyerData.setBuyerName(buyername);
		buyerData.setBuyerGender(buyergender);
		buyerData.setBuyerAge(buyerage);
		buyerData.setBuyerTele(buyertele);
		buyerData.setBuyerId(buyerid);
		this.fillTable(buyerData);
	}
	
//	增加
	protected void BuyerAddActionPerformed(ActionEvent evt) {
		String fbuyername = this.FBuyername.getText();
		String fbuyergender = this.FBuyergender.getText();
		String fbuyerage = this.FBuyerage.getText();
		String fbuyertele = this.FBuyertele.getText();
		String fbuyerid = this.FBuyerid.getText();
		if(StringUtil.isempty(fbuyername)){
			JOptionPane.showMessageDialog(null, "姓名不能为空！");
			return;
		}
		if(StringUtil.isempty(fbuyergender)){
			JOptionPane.showMessageDialog(null, "性别不能为空！");
			return;
		}
		if(StringUtil.isempty(fbuyerage)){
			JOptionPane.showMessageDialog(null, "年龄不能为空！");
			return;
		}
		if(StringUtil.isempty(fbuyertele)){
			JOptionPane.showMessageDialog(null, "联系方式不能为空！");
			return;
		}
		if(StringUtil.isempty(fbuyerid)){
			JOptionPane.showMessageDialog(null, "会员编号不能为空！");
			return;
		}
		BuyerData buyerData=new BuyerData(fbuyername, fbuyergender, fbuyerage, fbuyertele, fbuyerid);
		Connection con=null;
		try {
			con=main.OpenOracleConnection();
			int n = buyerDataDao.addpay(con, buyerData);
			if (n==1) {
				JOptionPane.showMessageDialog(null, "会员添加成功！");
				//重置
				FBuyername.setText("");
				FBuyergender.setText("");
				FBuyerage.setText("");
				FBuyertele.setText("");
				FBuyerid.setText("");
			}else {
				JOptionPane.showMessageDialog(null, "会员添加失败！");
				//重置
				FBuyername.setText("");
				FBuyergender.setText("");
				FBuyerage.setText("");
				FBuyertele.setText("");
				FBuyerid.setText("");
			}
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "会员添加失败！");
		}finally {
			try {
				main.CloseOracleConnection(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
//	清空	
	public void fillTable(BuyerData buyerData) {
		DefaultTableModel dtm=(DefaultTableModel) buyertable.getModel();
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
	
//	删除
	private void BuyerDeleteActionEvent(ActionEvent evt) {
		String fbuyername = this.FBuyername.getText();
		String fbuyergender = this.FBuyergender.getText();
		String fbuyerage = this.FBuyerage.getText();
		String fbuyertele = this.FBuyertele.getText();
		String fbuyerid = this.FBuyerid.getText();
		if(StringUtil.isempty(fbuyername)){
			JOptionPane.showMessageDialog(null, "姓名不能为空！");
			return;
		}
		if(StringUtil.isempty(fbuyergender)){
			JOptionPane.showMessageDialog(null, "性别不能为空！");
			return;
		}
		if(StringUtil.isempty(fbuyerage)){
			JOptionPane.showMessageDialog(null, "年龄不能为空！");
			return;
		}
		if(StringUtil.isempty(fbuyertele)){
			JOptionPane.showMessageDialog(null, "联系方式不能为空！");
			return;
		}
		if(StringUtil.isempty(fbuyerid)){
			JOptionPane.showMessageDialog(null, "会员编号不能为空！");
			return;
		}
		BuyerData buyerData=new BuyerData(fbuyername, fbuyergender, fbuyerage, fbuyertele, fbuyerid);
		int n=JOptionPane.showConfirmDialog(null, "确定要删除该记录吗？");
		if(n==0){
			Connection con=null;
			try{
				con=main.OpenOracleConnection();
				int deletelist=buyerDataDao.delete(con, buyerData);
				if(deletelist==1 || deletelist>1){
					JOptionPane.showMessageDialog(null, "删除成功");
					//重置
					FBuyername.setText("");
					FBuyergender.setText("");
					FBuyerage.setText("");
					FBuyertele.setText("");
					FBuyerid.setText("");
					this.fillTable(new BuyerData());
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
	private void BuyerUpdateActionEvent(ActionEvent evt) {
		String fbuyername = FBuyername.getText();
		String fbuyergender = FBuyergender.getText();
		String fbuyerage = FBuyerage.getText();
		String fbuyertele = FBuyertele.getText();
		String fbuyerid = FBuyerid.getText();
		if(StringUtil.isempty(fbuyername)){
			JOptionPane.showMessageDialog(null, "姓名不能为空！");
			return;
		}
		if(StringUtil.isempty(fbuyergender)){
			JOptionPane.showMessageDialog(null, "性别不能为空！");
			return;
		}
		if(StringUtil.isempty(fbuyerage)){
			JOptionPane.showMessageDialog(null, "年龄不能为空！");
			return;
		}
		if(StringUtil.isempty(fbuyertele)){
			JOptionPane.showMessageDialog(null, "联系方式不能为空！");
			return;
		}
		if(StringUtil.isempty(fbuyerid)){
			JOptionPane.showMessageDialog(null, "会员编号不能修改！");
			return;
		}
		BuyerData buyerData=new BuyerData(fbuyername, fbuyergender, fbuyerage, fbuyertele, fbuyerid);
		Connection con=null;
		try{
			con=main.OpenOracleConnection();
			int modifyNum=buyerDataDao.update(con, buyerData);
			if(modifyNum==1){
				JOptionPane.showMessageDialog(null, "修改成功");
				//重置
				FBuyername.setText("");
				FBuyergender.setText("");
				FBuyerage.setText("");
				FBuyertele.setText("");
				FBuyerid.setText("");
				this.fillTable(new BuyerData());
			}else{
				JOptionPane.showMessageDialog(null, "会员编号不能修改!");
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
	private void BuyerTableMousePressed(MouseEvent evt) {
		int row=buyertable.getSelectedRow();
		FBuyername.setText((String)buyertable.getValueAt(row, 0));
		FBuyergender.setText((String)buyertable.getValueAt(row, 1));
		FBuyerage.setText((String)buyertable.getValueAt(row, 2));
		FBuyertele.setText((String)buyertable.getValueAt(row, 3));
		FBuyerid.setText((String)buyertable.getValueAt(row, 4));
	}
	
//	刷新
	private void review() {
		//清空
		fillTable(new BuyerData());
		//显示
		frame.setVisible(true);
	}
}
