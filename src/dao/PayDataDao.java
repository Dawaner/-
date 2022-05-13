package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import code.StringUtil;
import code.PayData;

public class PayDataDao {

//	Ìí¼Ó
	public int addpay(Connection con,PayData paydata) throws Exception {
		String orcl="insert into PAY (\"Pay_id\", \"Pay_name\") values(?,?)";
		PreparedStatement pstmt=con.prepareStatement(orcl);
		pstmt.setString(1, paydata.getPayId());
		pstmt.setString(2, paydata.getPayName());
		return pstmt.executeUpdate();

	}
	
//	É¾³ý
	public int delete(Connection con,PayData paydata)throws Exception{
		StringBuffer sb=new StringBuffer("delete from PAY");
		if(StringUtil.isnotempty(paydata.getPayId()) || StringUtil.isnotempty(paydata.getPayName())){
			sb.append(" and \"Pay_id\" like '%"+paydata.getPayId()+"%'" +
					  "and \"Pay_name\" like '%"+paydata.getPayName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeUpdate();
	}
	
//	ÐÞ¸Ä
	public int update(Connection con,PayData paydata)throws Exception{
		String orcl="update PAY set \"Pay_name\"=? where \"Pay_id\"=?";
		PreparedStatement pstmt=con.prepareStatement(orcl);
		pstmt.setString(2, paydata.getPayId());
		pstmt.setString(1, paydata.getPayName());
		return pstmt.executeUpdate();
	}
	
//	²éÑ¯
	public ResultSet list(Connection con,PayData paydata)throws Exception{
		StringBuffer sb=new StringBuffer("select * from PAY");
		if(StringUtil.isnotempty(paydata.getPayId()) || StringUtil.isnotempty(paydata.getPayName())){
			sb.append(" and \"Pay_id\" like '%"+paydata.getPayId()+"%'" +
					  "and \"Pay_name\" like '%"+paydata.getPayName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
		
	}	
}
