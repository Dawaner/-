package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
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
import code.SendData;
import code.StringUtil;
import dao.SendDataDao;

public class SendFrame {

	public JFrame frame;
	private JTextField FSendid;
	private JTextField FSendname;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JScrollPane scrollPane;
	private JTable sendtable;
	
	private Main main = new Main();
	private SendDataDao sendDataDao = new SendDataDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SendFrame window = new SendFrame();
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
	public SendFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u53D1\u8D27\u65B9\u5F0F\u7BA1\u7406");
		frame.setBounds(100, 100, 471, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel FBookId = new JLabel("\u53D1\u8D27\u65B9\u5F0F\u7F16\u53F7\uFF1A");
		FBookId.setBounds(31, 29, 112, 19);
		FBookId.setFont(new Font("����", Font.PLAIN, 16));
		
		JLabel FBookName = new JLabel("\u53D1\u8D27\u65B9\u5F0F\u540D\u79F0\uFF1A");
		FBookName.setBounds(234, 26, 118, 19);
		FBookName.setFont(new Font("����", Font.PLAIN, 16));
		
		FSendid = new JTextField();
		FSendid.setBounds(143, 26, 50, 25);
		FSendid.setFont(new Font("����", Font.PLAIN, 16));
		FSendid.setColumns(10);
		
		FSendname = new JTextField();
		FSendname.setBounds(347, 23, 50, 25);
		FSendname.setFont(new Font("����", Font.PLAIN, 16));
		FSendname.setColumns(10);
		
		btnNewButton = new JButton("\u9000\u51FA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();//���ٵ�ǰ����
			}
		});
		btnNewButton.setBounds(367, 404, 80, 27);
		btnNewButton.setFont(new Font("����", Font.PLAIN, 16));
		
		btnNewButton_1 = new JButton("\u65B0\u589E");
		btnNewButton_1.setIcon(new ImageIcon(SendFrame.class.getResource("/picture/add.png")));
		btnNewButton_1.setBounds(31, 71, 97, 27);
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SendAddActionPerformed(e);
				//���
				fillTable(new SendData());
				//��ʾ
				frame.setVisible(true);
			}
		});
		
		btnNewButton_2 = new JButton("\u4FEE\u6539");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SendUpdateActionEvent(e);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(SendFrame.class.getResource("/picture/modify.png")));
		btnNewButton_2.setBounds(336, 71, 97, 27);
		btnNewButton_2.setFont(new Font("����", Font.PLAIN, 16));
		
		btnNewButton_3 = new JButton("\u5220\u9664");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SendDeleteActionEvent(e);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(SendFrame.class.getResource("/picture/delete.png")));
		btnNewButton_3.setBounds(189, 71, 97, 27);
		btnNewButton_3.setFont(new Font("����", Font.PLAIN, 16));
		
		btnNewButton_4 = new JButton("\u67E5\u8BE2");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SendSearchActionPerformed(e);
			}
		});
		btnNewButton_4.setIcon(new ImageIcon(SendFrame.class.getResource("/picture/search.png")));
		btnNewButton_4.setBounds(31, 108, 97, 27);
		btnNewButton_4.setFont(new Font("����", Font.PLAIN, 16));
		
		btnNewButton_5 = new JButton("\u91CD\u7F6E");
		btnNewButton_5.setIcon(new ImageIcon(SendFrame.class.getResource("/picture/reset.png")));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				����
				FSendid.setText("");
				FSendname.setText("");
			}
		});
		btnNewButton_5.setBounds(189, 108, 97, 27);
		btnNewButton_5.setFont(new Font("����", Font.PLAIN, 16));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(FBookId);
		frame.getContentPane().add(FSendid);
		frame.getContentPane().add(FBookName);
		frame.getContentPane().add(FSendname);
		frame.getContentPane().add(btnNewButton_1);
		frame.getContentPane().add(btnNewButton_3);
		frame.getContentPane().add(btnNewButton_2);
		frame.getContentPane().add(btnNewButton_4);
		frame.getContentPane().add(btnNewButton_5);
		frame.getContentPane().add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 157, 402, 241);
		frame.getContentPane().add(scrollPane);
		
		sendtable = new JTable();
//		���
		sendtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				SendTableMousePressed(e);
			}
		});
		sendtable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u53D1\u8D27\u65B9\u5F0F\u7F16\u53F7", "\u53D1\u8D27\u65B9\u5F0F\u540D\u79F0"
			}
		));
		scrollPane.setViewportView(sendtable);
		
		// ����Frame������ʾ
		this.frame.setLocationRelativeTo(null);
		// ֻ�رյ�ǰ����
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//���
		this.fillTable(new SendData());
		//��ʾ
		frame.setVisible(true);
	}
	
//	��ѯ
	private void SendSearchActionPerformed(ActionEvent evts) {
		// TODO �Զ����ɵķ������
		String sendid =this.FSendid.getText();
		String sendname =this.FSendname.getText();
		SendData sendData=new SendData();
		sendData.setSendId(sendid);
		sendData.setSendName(sendname);
		this.fillTable(sendData);
	}

//	����
	protected void SendAddActionPerformed(ActionEvent evt) {
		String fsendid = this.FSendid.getText();
		String fsendname = this.FSendname.getText();
		if(StringUtil.isempty(fsendid)){
			JOptionPane.showMessageDialog(null, "������ʽ��Ų���Ϊ�գ�");
			return;
		}
		if(StringUtil.isempty(fsendname)){
			JOptionPane.showMessageDialog(null, "������ʽ���Ʋ���Ϊ�գ�");
			return;
		}
		SendData sendData=new SendData(fsendid, fsendname);
		Connection con=null;
		try {
			con=main.OpenOracleConnection();
			int n = sendDataDao.addpay(con, sendData);
			if (n==1) {
				JOptionPane.showMessageDialog(null, "������ʽ��ӳɹ���");
				//����
				FSendid.setText("");
				FSendname.setText("");
			}else {
				JOptionPane.showMessageDialog(null, "������ʽ���ʧ�ܣ�");
				//����
				FSendid.setText("");
				FSendname.setText("");
			}
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "������ʽ���ʧ�ܣ�");
		}finally {
			try {
				main.CloseOracleConnection(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
//	���
	public void fillTable(SendData sendData) {
		DefaultTableModel dtm=(DefaultTableModel) sendtable.getModel();
		dtm.setRowCount(0); // ���ó�0��
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
	
//	ɾ��
	private void SendDeleteActionEvent(ActionEvent evt) {
		String fsendid = this.FSendid.getText();
		String fsendname = this.FSendname.getText();
		if(StringUtil.isempty(fsendid)){
			JOptionPane.showMessageDialog(null, "������ʽ��Ų���Ϊ�գ�");
			return;
		}
		if(StringUtil.isempty(fsendname)){
			JOptionPane.showMessageDialog(null, "������ʽ���Ʋ���Ϊ�գ�");
			return;
		}
		SendData sendData=new SendData(fsendid, fsendname);
		int n=JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ���ü�¼��");
		if(n==0){
			Connection con=null;
			try{
				con=main.OpenOracleConnection();
				int deletelist=sendDataDao.delete(con, sendData);
				if(deletelist==1 || deletelist>1){
					JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
					//����
					FSendid.setText("");
					FSendname.setText("");
					this.fillTable(new SendData());
				}else{
					JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
				}
			}catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
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
	
//	�޸�
	private void SendUpdateActionEvent(ActionEvent evt) {
		String fsendid = FSendid.getText();
		String fsendname = FSendname.getText();
		if(StringUtil.isempty(fsendid)){
			JOptionPane.showMessageDialog(null, "������ʽ��Ų����޸ģ�");
			return;
		}
		if(StringUtil.isempty(fsendname)){
			JOptionPane.showMessageDialog(null, "������ʽ���Ʋ���Ϊ�գ�");
			return;
		}
		SendData sendData=new SendData(fsendid, fsendname);
		Connection con=null;
		try{
			con=main.OpenOracleConnection();
			int modifyNum=sendDataDao.update(con, sendData);
			if(modifyNum==1){
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
				//����
				FSendid.setText("");
				FSendname.setText("");
				this.fillTable(new SendData());
			}else{
				JOptionPane.showMessageDialog(null, "������ʽ��Ų����޸�!");
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "�޸�ʧ��");
		}finally{
			try {
				main.CloseOracleConnection(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
//	���
	private void SendTableMousePressed(MouseEvent evt) {
		int row=sendtable.getSelectedRow();
		FSendid.setText((String)sendtable.getValueAt(row, 0));
		FSendname.setText((String)sendtable.getValueAt(row, 1));
	}
	
//	ˢ��
	private void review() {
		//���
		fillTable(new SendData());
		//��ʾ
		frame.setVisible(true);
	}
}
