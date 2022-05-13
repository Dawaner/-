package view;
import java.awt.EventQueue;

import javax.swing.UIManager;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.HeadlessException;

import java.awt.Font;
import javax.swing.JTextField;
import code.Main;
import code.StringUtil;
import code.UserData;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class Login {
	
	private Main main=new Main();
	private UserData userData=new UserData();
	
	public JFrame frame;
	private JTextField username_txt;
	private JPasswordField password_txt;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
		      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		    } catch (Exception e) {
		      e.printStackTrace();
		    }

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u7BA1\u7406\u5458\u767B\u5F55");
		frame.setBounds(100, 100, 435, 286);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/picture/logo.png")));
		lblNewLabel.setBounds(101, 21, 200, 53);
		lblNewLabel.setFont(new Font("����", Font.BOLD, 20));
		
		JLabel lblNewLabel_1 = new JLabel("\u8D26\u53F7\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/picture/userName.png")));
		lblNewLabel_1.setBounds(78, 97, 68, 19);
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 16));
		
		JLabel lblNewLabel_2 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/picture/password.png")));
		lblNewLabel_2.setBounds(78, 147, 68, 19);
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 16));
		
		username_txt = new JTextField();
		username_txt.setBounds(150, 95, 164, 24);
		username_txt.setFont(new Font("����", Font.PLAIN, 15));
		username_txt.setColumns(10);
		
		password_txt = new JPasswordField();
		password_txt.setBounds(150, 142, 164, 24);
		
		btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.setIcon(new ImageIcon(Login.class.getResource("/picture/login.png")));
		btnNewButton.setBounds(75, 194, 97, 27);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loginActionPerformed(e);
				} catch (ClassNotFoundException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("����", Font.PLAIN, 16));
		
		btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.setIcon(new ImageIcon(Login.class.getResource("/picture/reset.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		btnNewButton_1.setBounds(234, 194, 97, 27);
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 16));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblNewLabel);
		frame.getContentPane().add(lblNewLabel_2);
		frame.getContentPane().add(password_txt);
		frame.getContentPane().add(lblNewLabel_1);
		frame.getContentPane().add(username_txt);
		frame.getContentPane().add(btnNewButton);
		frame.getContentPane().add(btnNewButton_1);
		
		// ����Frame������ʾ
		this.frame.setLocationRelativeTo(null);
	}
	
//	�˺���������
	protected void resetValueActionPerformed(ActionEvent e) {
		this.username_txt.setText("");
		this.password_txt.setText("");
		}

//	��¼�¼�����
	private void loginActionPerformed(ActionEvent evt) throws HeadlessException, ClassNotFoundException {
		String username = this.username_txt.getText();
		String password = new String(this.password_txt.getPassword());
		if(StringUtil.isempty(username)) {
			JOptionPane.showMessageDialog(null,"�û�������Ϊ��");
			return;
		}
		if(StringUtil.isempty(password)) {
			JOptionPane.showMessageDialog(null,"���벻��Ϊ��");
			return;
		}
		
		main.UserId=username;
		main.UserPasswd=password;
		Connection con = null;
		con = main.OpenOracleConnection();
		if (con!=null) {
			JOptionPane.showMessageDialog(frame, "�û�����ɹ���", "������",
					JOptionPane.INFORMATION_MESSAGE);
			frame.dispose();//���ٵ�ǰ����
			new MainFrame().frame.setVisible(true);//�����µĴ���
			try {
				main.CloseOracleConnection(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
//		else if(main.UserId!=null) {
//			username_txt.setText(username);
//			JOptionPane.showMessageDialog(frame, "�������", "������",
//					JOptionPane.INFORMATION_MESSAGE);
//		}
		else {
			username_txt.setText(username);
			password_txt.setText("");
			JOptionPane.showMessageDialog(frame, "�û�����ʧ�ܣ�", "������",
					JOptionPane.INFORMATION_MESSAGE);
			try {
				main.CloseOracleConnection(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		
	}
	
}
