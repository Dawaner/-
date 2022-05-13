package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import code.PayData;
import code.SendData;
import code.StringUtil;

public class SendDataDao {
	
//	Ìí¼Ó
	public int addpay(Connection con, SendData senddata) throws Exception {
		String orcl="insert into Send (\"Send_id\", \"Send_name\") values(?,?)";
		PreparedStatement pstmt=con.prepareStatement(orcl);
		pstmt.setString(1, senddata.getSendId());
		pstmt.setString(2, senddata.getSendName());
		return pstmt.executeUpdate();

	}
	
//	É¾³ý
	public int delete(Connection con,SendData senddata)throws Exception{
		StringBuffer sb=new StringBuffer("delete from SEND");
		if(StringUtil.isnotempty(senddata.getSendId()) || StringUtil.isnotempty(senddata.getSendName())){
			sb.append(" and \"Send_id\" like '%"+senddata.getSendId()+"%'" +
					  "and \"Send_name\" like '%"+senddata.getSendName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeUpdate();
	}
	
//	ÐÞ¸Ä
	public int update(Connection con,SendData senddata)throws Exception{
		String orcl="update SEND set \"Send_name\"=? where \"Send_id\"=?";
		PreparedStatement pstmt=con.prepareStatement(orcl);
		pstmt.setString(2, senddata.getSendId());
		pstmt.setString(1, senddata.getSendName());
		return pstmt.executeUpdate();
	}

//	²éÑ¯
	public ResultSet list(Connection con,SendData senddata)throws Exception{
		StringBuffer sb=new StringBuffer("select * from SEND");
		if(StringUtil.isnotempty(senddata.getSendId()) || StringUtil.isnotempty(senddata.getSendName())){
			sb.append(" and \"Send_id\" like '%"+senddata.getSendId()+"%'" +
					  "and \"Send_name\" like '%"+senddata.getSendName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
		
	}	
}
