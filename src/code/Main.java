package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
	 	
	public static String UserId;
	public static String UserPasswd;
	
//	�������ݿ�
	public Connection OpenOracleConnection() throws ClassNotFoundException {
		Connection con=null;
		//load jdbc driver
		Class.forName("oracle.jdbc.driver.OracleDriver"); 
		
		//Creating a connection between the Java program and the Oracle database. 
		try {
			con=DriverManager.getConnection(
			"jdbc:oracle:thin:@Dawan:1521:orcl",UserId,UserPasswd);
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
//	�������ݿ�	
	public void CloseOracleConnection(Connection con) throws Exception {

		//close oracle connection
		if(con!=null)
			con.close();
		
	}
	

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������

	}

}
