package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import code.Main;
import code.PayData;
import code.SendData;
import code.StringUtil;
import dao.PayDataDao;

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
import javax.swing.table.DefaultTableModel;


import javax.swing.UIManager;


public class PayFrame {

	public JFrame frame;
	private JTextField FPayid;
	private JTextField FPayname;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	
	
	private Main main = new Main();
	private PayDataDao payDataDao = new PayDataDao();
	private JTable paytable;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayFrame window = new PayFrame();
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
	public PayFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u652F\u4ED8\u65B9\u5F0F\u7BA1\u7406");
		frame.setBounds(100, 100, 471, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel FBookId = new JLabel("\u652F\u4ED8\u65B9\u5F0F\u7F16\u53F7\uFF1A");
		FBookId.setBounds(31, 29, 112, 19);
		FBookId.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JLabel FBookName = new JLabel("\u652F\u4ED8\u65B9\u5F0F\u540D\u79F0\uFF1A");
		FBookName.setBounds(218, 29, 118, 19);
		FBookName.setFont(new Font("宋体", Font.PLAIN, 16));
		
		FPayid = new JTextField();
		FPayid.setBounds(143, 26, 50, 25);
		FPayid.setFont(new Font("宋体", Font.PLAIN, 16));
		FPayid.setColumns(10);
		
		FPayname = new JTextField();
		FPayname.setBounds(326, 26, 86, 25);
		FPayname.setFont(new Font("宋体", Font.PLAIN, 16));
		FPayname.setColumns(10);
		
		btnNewButton = new JButton("\u9000\u51FA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();//销毁当前窗口
			}
		});
		btnNewButton.setBounds(367, 404, 80, 27);
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		
		btnNewButton_1 = new JButton("\u65B0\u589E");
		btnNewButton_1.setIcon(new ImageIcon(PayFrame.class.getResource("/picture/add.png")));
		btnNewButton_1.setBounds(31, 71, 97, 27);
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PayAddActionPerformed(e);
				review();
			}
		});
		
		btnNewButton_2 = new JButton("\u4FEE\u6539");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PayUpdateActionEvent(e);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(PayFrame.class.getResource("/picture/modify.png")));
		btnNewButton_2.setBounds(336, 71, 97, 27);
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 16));
		
		btnNewButton_3 = new JButton("\u5220\u9664");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PayDeleteActionEvent(e);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(PayFrame.class.getResource("/picture/delete.png")));
		btnNewButton_3.setBounds(189, 71, 97, 27);
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 16));
		
		btnNewButton_4 = new JButton("\u67E5\u8BE2");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaySearchActionPerformed(e);
			}
		});
		btnNewButton_4.setIcon(new ImageIcon(PayFrame.class.getResource("/picture/search.png")));
		btnNewButton_4.setBounds(31, 108, 97, 27);
		btnNewButton_4.setFont(new Font("宋体", Font.PLAIN, 16));
		
		btnNewButton_5 = new JButton("\u91CD\u7F6E");
		btnNewButton_5.setIcon(new ImageIcon(PayFrame.class.getResource("/picture/reset.png")));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				重置
				FPayid.setText("");
				FPayname.setText("");
			}
		});
		btnNewButton_5.setBounds(189, 108, 97, 27);
		btnNewButton_5.setFont(new Font("宋体", Font.PLAIN, 16));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(FBookId);
		frame.getContentPane().add(FPayid);
		frame.getContentPane().add(FBookName);
		frame.getContentPane().add(FPayname);
		frame.getContentPane().add(btnNewButton_1);
		frame.getContentPane().add(btnNewButton_3);
		frame.getContentPane().add(btnNewButton_2);
		frame.getContentPane().add(btnNewButton_4);
		frame.getContentPane().add(btnNewButton_5);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 157, 401, 237);
		frame.getContentPane().add(scrollPane);
		
		paytable = new JTable();
//		点击
		paytable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PayTableMousePressed(e);
			}
		});
		paytable.setFont(UIManager.getFont("Button.font"));
		paytable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u652F\u4ED8\u65B9\u5F0F\u7F16\u53F7", "\u652F\u4ED8\u65B9\u5F0F\u540D\u79F0"
			}
		));
		paytable.getColumnModel().getColumn(0).setPreferredWidth(86);
		paytable.getColumnModel().getColumn(1).setPreferredWidth(86);
		scrollPane.setViewportView(paytable);
		
		//设置Frame居中显示
		this.frame.setLocationRelativeTo(null);
		//只关闭当前窗口
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//清空
		this.fillTable(new PayData());
		//显示
		frame.setVisible(true);
	}

//	查询
	private void PaySearchActionPerformed(ActionEvent evts) {
		// TODO 自动生成的方法存根
		String payid =this.FPayid.getText();
		String payname =this.FPayname.getText();
		PayData payData=new PayData();
		payData.setPayId(payid);
		payData.setPayName(payname);
		this.fillTable(payData);
	}

//	增加
	protected void PayAddActionPerformed(ActionEvent evt) {
		String fpayid = this.FPayid.getText();
		String fpayname = this.FPayname.getText();
		if(StringUtil.isempty(fpayid)){
			JOptionPane.showMessageDialog(null, "支付方式编号不能为空！");
			return;
		}
		if(StringUtil.isempty(fpayname)){
			JOptionPane.showMessageDialog(null, "支付方式名称不能为空！");
			return;
		}
		PayData payData=new PayData(fpayid, fpayname);
		Connection con=null;
		try {
			con=main.OpenOracleConnection();
			int n = payDataDao.addpay(con, payData);
			if (n==1) {
				JOptionPane.showMessageDialog(null, "支付方式添加成功！");
				//重置
				FPayid.setText("");
				FPayname.setText("");
			}else {
				JOptionPane.showMessageDialog(null, "支付方式添加失败！");
				//重置
				FPayid.setText("");
				FPayname.setText("");
			}
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "支付方式添加失败！");
		}finally {
			try {
				main.CloseOracleConnection(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
//	清空
	public void fillTable(PayData payData) {
		DefaultTableModel dtm=(DefaultTableModel) paytable.getModel();
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
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				main.CloseOracleConnection(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
//	删除
	private void PayDeleteActionEvent(ActionEvent evt) {
		String fpayid = this.FPayid.getText();
		String fpayname = this.FPayname.getText();
		if(StringUtil.isempty(fpayid)){
			JOptionPane.showMessageDialog(null, "支付方式编号不能为空！");
			return;
		}
		if(StringUtil.isempty(fpayname)){
			JOptionPane.showMessageDialog(null, "支付方式名称不能为空！");
			return;
		}
		PayData payData=new PayData(fpayid, fpayname);
		int n=JOptionPane.showConfirmDialog(null, "确定要删除该记录吗？");
		if(n==0){
			Connection con=null;
			try{
				con=main.OpenOracleConnection();
				int deletelist=payDataDao.delete(con, payData);
				if(deletelist==1 || deletelist>1){
					JOptionPane.showMessageDialog(null, "删除成功");
					//重置
					FPayid.setText("");
					FPayname.setText("");
					this.fillTable(new PayData());
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
	private void PayUpdateActionEvent(ActionEvent evt) {
		String fpayid=FPayid.getText();
		String fpayname=FPayname.getText();
		if(StringUtil.isempty(fpayid)){
			JOptionPane.showMessageDialog(null, "支付方式编号不能修改");
			return;
		}
		if(StringUtil.isempty(fpayname)){
			JOptionPane.showMessageDialog(null, "支付方式名称不能为空");
			return;
		}
		PayData payData=new PayData(fpayid,fpayname);
		Connection con=null;
		try{
			con=main.OpenOracleConnection();
			int modifyNum=payDataDao.update(con, payData);
			if(modifyNum==1){
				JOptionPane.showMessageDialog(null, "修改成功");
				//重置
				FPayid.setText("");
				FPayname.setText("");
				this.fillTable(new PayData());
			}else{
				JOptionPane.showMessageDialog(null, "支付方式编号不能修改!");
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
	private void PayTableMousePressed(MouseEvent evt) {
		int row=paytable.getSelectedRow();
		FPayid.setText((String)paytable.getValueAt(row, 0));
		FPayname.setText((String)paytable.getValueAt(row, 1));
	}
	
//	刷新
	private void review() {
		//清空
		fillTable(new PayData());
		//显示
		frame.setVisible(true);
	}
}
