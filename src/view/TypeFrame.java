package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
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

import code.Main;
import code.PayData;
import code.StringUtil;
import code.TypeData;
import dao.TypeDataDao;

public class TypeFrame {

	public JFrame frame;
	private JTextField FTypeid;
	private JTextField FTypename;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JScrollPane scrollPane;
	private JTable typetable;
	
	private Main main = new Main();
	private TypeDataDao typeDataDao = new TypeDataDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TypeFrame window = new TypeFrame();
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
	public TypeFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u4E66\u7C4D\u79CD\u7C7B\u7BA1\u7406");
		frame.setBounds(100, 100, 464, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel FBookId = new JLabel("\u79CD\u7C7B\u7F16\u53F7\uFF1A");
		FBookId.setBounds(31, 29, 80, 19);
		FBookId.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JLabel FBookName = new JLabel("\u79CD\u7C7B\u540D\u79F0\uFF1A");
		FBookName.setBounds(239, 29, 118, 19);
		FBookName.setFont(new Font("宋体", Font.PLAIN, 16));
		
		FTypeid = new JTextField();
		FTypeid.setBounds(115, 26, 106, 25);
		FTypeid.setFont(new Font("宋体", Font.PLAIN, 16));
		FTypeid.setColumns(10);
		
		FTypename = new JTextField();
		FTypename.setBounds(327, 26, 106, 25);
		FTypename.setFont(new Font("宋体", Font.PLAIN, 16));
		FTypename.setColumns(10);
		
		btnNewButton = new JButton("\u9000\u51FA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();//销毁当前窗口
			}
		});
		btnNewButton.setBounds(367, 404, 80, 27);
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		
		btnNewButton_1 = new JButton("\u65B0\u589E");
		btnNewButton_1.setIcon(new ImageIcon(TypeFrame.class.getResource("/picture/add.png")));
		btnNewButton_1.setBounds(31, 71, 97, 27);
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TypeAddActionPerformed(e);
				//清空
				fillTable(new TypeData());
				//显示
				frame.setVisible(true);
			}
		});
		
		btnNewButton_2 = new JButton("\u4FEE\u6539");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TypeUpdateActionEvent(e);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(TypeFrame.class.getResource("/picture/modify.png")));
		btnNewButton_2.setBounds(336, 71, 97, 27);
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 16));
		
		btnNewButton_3 = new JButton("\u5220\u9664");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TypeDeleteActionEvent(e);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(TypeFrame.class.getResource("/picture/delete.png")));
		btnNewButton_3.setBounds(189, 71, 97, 27);
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 16));
		
		btnNewButton_4 = new JButton("\u67E5\u8BE2");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TypeSearchActionPerformed(e);
			}
		});
		btnNewButton_4.setIcon(new ImageIcon(TypeFrame.class.getResource("/picture/search.png")));
		btnNewButton_4.setBounds(31, 108, 97, 27);
		btnNewButton_4.setFont(new Font("宋体", Font.PLAIN, 16));
		
		btnNewButton_5 = new JButton("\u91CD\u7F6E");
		btnNewButton_5.setIcon(new ImageIcon(TypeFrame.class.getResource("/picture/reset.png")));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				重置
				FTypeid.setText("");
				FTypename.setText("");
			}
		});
		btnNewButton_5.setBounds(189, 108, 97, 27);
		btnNewButton_5.setFont(new Font("宋体", Font.PLAIN, 16));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(FBookId);
		frame.getContentPane().add(FTypeid);
		frame.getContentPane().add(FBookName);
		frame.getContentPane().add(FTypename);
		frame.getContentPane().add(btnNewButton_1);
		frame.getContentPane().add(btnNewButton_3);
		frame.getContentPane().add(btnNewButton_2);
		frame.getContentPane().add(btnNewButton_4);
		frame.getContentPane().add(btnNewButton_5);
		frame.getContentPane().add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 154, 401, 244);
		frame.getContentPane().add(scrollPane);
		
		typetable = new JTable();
//		点击
		typetable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				TypeTableMousePressed(e);
			}
		});
		typetable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u79CD\u7C7B\u7F16\u53F7", "\u79CD\u7C7B\u540D\u79F0"
			}
		));
		scrollPane.setViewportView(typetable);
		
		// 设置Frame居中显示
		this.frame.setLocationRelativeTo(null);
		// 只关闭当前窗口
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//清空
		this.fillTable(new TypeData());
		//显示
		frame.setVisible(true);
	}
	
//	查询
	private void TypeSearchActionPerformed(ActionEvent evts) {
		// TODO 自动生成的方法存根
		String typeid =this.FTypeid.getText();
		String typename =this.FTypename.getText();
		TypeData typeData=new TypeData();
		typeData.setTypeId(typeid);
		typeData.setTypeName(typename);
		this.fillTable(typeData);
	}
	
//	增加
	protected void TypeAddActionPerformed(ActionEvent evt) {
		String ftypeid = this.FTypeid.getText();
		String ftypename = this.FTypename.getText();
		if(StringUtil.isempty(ftypeid)){
			JOptionPane.showMessageDialog(null, "种类编号不能为空！");
			return;
		}
		if(StringUtil.isempty(ftypename)){
			JOptionPane.showMessageDialog(null, "种类名称不能为空！");
			return;
		}
		TypeData typeData=new TypeData(ftypeid, ftypename);
		Connection con=null;
		try {
			con=main.OpenOracleConnection();
			int n = typeDataDao.addtype(con, typeData);
			if (n==1) {
				JOptionPane.showMessageDialog(null, "图书种类添加成功！");
				//重置
				FTypeid.setText("");
				FTypename.setText("");
			}else {
				JOptionPane.showMessageDialog(null, "图书种类添加失败！");
				//重置
				FTypeid.setText("");
				FTypename.setText("");
			}
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "图书种类添加失败！");
		}finally {
			try {
				main.CloseOracleConnection(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
//	清空	
	public void fillTable(TypeData typeData) {
		DefaultTableModel dtm=(DefaultTableModel) typetable.getModel();
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
	
//	删除
	private void TypeDeleteActionEvent(ActionEvent evt) {
		String ftypeid = this.FTypeid.getText();
		String ftypename = this.FTypename.getText();
		if(StringUtil.isempty(ftypeid)){
			JOptionPane.showMessageDialog(null, "种类编号不能为空！");
			return;
		}
		if(StringUtil.isempty(ftypename)){
			JOptionPane.showMessageDialog(null, "种类名称不能为空！");
			return;
		}
		TypeData typeData=new TypeData(ftypeid, ftypename);
		int n=JOptionPane.showConfirmDialog(null, "确定要删除该记录吗？");
		if(n==0){
			Connection con=null;
			try{
				con=main.OpenOracleConnection();
				int deletelist=typeDataDao.delete(con, typeData);
				if(deletelist==1 || deletelist>1){
					JOptionPane.showMessageDialog(null, "删除成功");
					//重置
					FTypeid.setText("");
					FTypename.setText("");
					this.fillTable(new TypeData());
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
	private void TypeUpdateActionEvent(ActionEvent evt) {
		String ftypeid = FTypeid.getText();
		String ftypename = FTypename.getText();
		if(StringUtil.isempty(ftypeid)){
			JOptionPane.showMessageDialog(null, "种类编号不能修改！");
			return;
		}
		if(StringUtil.isempty(ftypename)){
			JOptionPane.showMessageDialog(null, "种类名称不能为空！");
			return;
		}
		TypeData typeData=new TypeData(ftypeid, ftypename);
		Connection con=null;
		try{
			con=main.OpenOracleConnection();
			int modifyNum=typeDataDao.update(con, typeData);
			if(modifyNum==1){
				JOptionPane.showMessageDialog(null, "修改成功");
				//重置
				FTypeid.setText("");
				FTypename.setText("");
				this.fillTable(new TypeData());
			}else{
				JOptionPane.showMessageDialog(null, "种类编号不能修改!");
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
	private void TypeTableMousePressed(MouseEvent evt) {
		int row=typetable.getSelectedRow();
		FTypeid.setText((String)typetable.getValueAt(row, 0));
		FTypename.setText((String)typetable.getValueAt(row, 1));
	}
	
//	刷新
	private void review() {
		//清空
		fillTable(new TypeData());
		//显示
		frame.setVisible(true);
	}
}
