package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import code.BookData;
import code.BuyData;
import code.StringUtil;

public class BuyDataDao {
	
//	Ìí¼Ó
	public int addbuy(Connection con,BuyData buydata) throws Exception {
		String orcl="insert into BUY (\"Buy_id\", \"Buy_pay_id\", \"Buy_send_id\", \"Buy_time\") values(?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(orcl);
		pstmt.setString(1, buydata.getBuyId());
		pstmt.setString(2, buydata.getBuyPayId());
		pstmt.setString(3, buydata.getBuySendId());
		pstmt.setString(4, buydata.getBuyTime());
		return pstmt.executeUpdate();

	}
	
//	É¾³ý
	public int delete(Connection con,BuyData buydata)throws Exception{
		StringBuffer sb=new StringBuffer("delete from BUY");
		if(StringUtil.isnotempty(buydata.getBuyId()) || StringUtil.isnotempty(buydata.getBuyPayId())
				|| 	StringUtil.isnotempty(buydata.getBuySendId()) || StringUtil.isnotempty(buydata.getBuyTime())  ){
				sb.append(" and \"Buy_id\" like '%"+buydata.getBuyId()+"%'" +
						  " and \"Buy_pay_id\" like '%"+buydata.getBuyPayId()+"%'" +
						  " and \"Buy_send_id\" like '%"+buydata.getBuySendId()+"%'" +
						  " and \"Buy_time\" like '%"+buydata.getBuyTime()+"%'");
			}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeUpdate();
	}
	
//	ÐÞ¸Ä
	public int update(Connection con,BuyData buydata)throws Exception{
		String orcl="update BUY set \"Buy_pay_id\"=?, \"Buy_send_id\"=?, \"Buy_time\"=? where \"Buy_id\"=?";
		PreparedStatement pstmt=con.prepareStatement(orcl);
		pstmt.setString(4, buydata.getBuyId());
		pstmt.setString(1, buydata.getBuyPayId());
		pstmt.setString(2, buydata.getBuySendId());
		pstmt.setString(3, buydata.getBuyTime());
		return pstmt.executeUpdate();
	}	

//	²éÑ¯
	public ResultSet list(Connection con,BuyData buydata)throws Exception{
		StringBuffer sb=new StringBuffer("select * from BUY");
		if(StringUtil.isnotempty(buydata.getBuyId()) || StringUtil.isnotempty(buydata.getBuyPayId())
			|| 	StringUtil.isnotempty(buydata.getBuySendId()) || StringUtil.isnotempty(buydata.getBuyTime())  ){
			sb.append(" and \"Buy_id\" like '%"+buydata.getBuyId()+"%'" +
					  " and \"Buy_pay_id\" like '%"+buydata.getBuyPayId()+"%'" +
					  " and \"Buy_send_id\" like '%"+buydata.getBuySendId()+"%'" +
					  " and \"Buy_time\" like '%"+buydata.getBuyTime()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
		
	}
	
}
