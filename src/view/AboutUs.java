package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

public class AboutUs {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutUs window = new AboutUs();
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
	public AboutUs() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u5173\u4E8E");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setForeground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		
		JTextPane txtpnuser = new JTextPane();
		txtpnuser.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		txtpnuser.setText("\u7248\u672C: 1.0.0 (user setup)\r\n\u63D0\u4EA4: 507ce72a4466fbb27b715c3722558bb15afa9f48\r\n\u65E5\u671F: 2021-06-17T13:28:07.755Z\r\nElectron: 12.0.7\r\nChrome: 89.0.4389.128\r\nNode.js: 14.16.0\r\nV8: 8.9.255.25-electron.0\r\nOS: Windows_NT x64 10.0.19043");
		txtpnuser.setEditable(false);
		txtpnuser.setBounds(0, 0, 362, 166);
		frame.getContentPane().add(txtpnuser);
		frame.setBounds(100, 100, 370, 204);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 设置Frame居中显示
		this.frame.setLocationRelativeTo(null);
		// 只关闭当前窗口
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
