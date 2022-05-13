package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import code.BookData;
import code.BuyerData;
import code.StringUtil;

public class BuyerDataDao {
	
//	Ìí¼Ó
	public int addpay(Connection con,BuyerData buyerdata) throws Exception {
		String orcl="insert into BUYER (\"Buyer_name\", \"Buyer_gender\", \"Buyer_age\", \"Buyer_tele\", \"Buyer_id\") values(?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(orcl);
		pstmt.setString(1, buyerdata.getBuyerName());
		pstmt.setString(2, buyerdata.getBuyerGender());
		pstmt.setString(3, buyerdata.getBuyerAge());
		pstmt.setString(4, buyerdata.getBuyerTele());
		pstmt.setString(5, buyerdata.getBuyerId());
		return pstmt.executeUpdate();

	}

//	É¾³ý
	public int delete(Connection con,BuyerData buyerdata)throws Exception{
		StringBuffer sb=new StringBuffer("delete from BUYER");
		if(StringUtil.isnotempty(buyerdata.getBuyerName()) || StringUtil.isnotempty(buyerdata.getBuyerGender())
				|| 	StringUtil.isnotempty(buyerdata.getBuyerAge()) || StringUtil.isnotempty(buyerdata.getBuyerTele()) 
				|| StringUtil.isnotempty(buyerdata.getBuyerId()) ){
				sb.append(" and \"Buyer_name\" like '%"+buyerdata.getBuyerName()+"%'" +
						  " and \"Buyer_gender\" like '%"+buyerdata.getBuyerGender()+"%'" +
						  " and \"Buyer_age\" like '%"+buyerdata.getBuyerAge()+"%'" +
						  " and \"Buyer_tele\" like '%"+buyerdata.getBuyerTele()+"%'" +
						  " and \"Buyer_id\" like '%"+buyerdata.getBuyerId()+"%'");
			}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeUpdate();
	}
	
//	ÐÞ¸Ä
	public int update(Connection con,BuyerData buyerdata)throws Exception{
		String orcl="update BUYER set \"Buyer_name\"=?, \"Buyer_gender\"=?, \"Buyer_age\"=?, \"Buyer_tele\"=? where \"Buyer_id\"=?";
		PreparedStatement pstmt=con.prepareStatement(orcl);
		pstmt.setString(1, buyerdata.getBuyerName());
		pstmt.setString(2, buyerdata.getBuyerGender());
		pstmt.setString(3, buyerdata.getBuyerAge());
		pstmt.setString(4, buyerdata.getBuyerTele());
		pstmt.setString(5, buyerdata.getBuyerId());
		return pstmt.executeUpdate();
	}	
	
//	²éÑ¯
	public ResultSet list(Connection con,BuyerData buyerdata)throws Exception{
		StringBuffer sb=new StringBuffer("select * from BUYER");
		if(StringUtil.isnotempty(buyerdata.getBuyerName()) || StringUtil.isnotempty(buyerdata.getBuyerGender())
			|| 	StringUtil.isnotempty(buyerdata.getBuyerAge()) || StringUtil.isnotempty(buyerdata.getBuyerTele()) 
			|| StringUtil.isnotempty(buyerdata.getBuyerId()) ){
			sb.append(" and \"Buyer_name\" like '%"+buyerdata.getBuyerName()+"%'" +
					  " and \"Buyer_gender\" like '%"+buyerdata.getBuyerGender()+"%'" +
					  " and \"Buyer_age\" like '%"+buyerdata.getBuyerAge()+"%'" +
					  " and \"Buyer_tele\" like '%"+buyerdata.getBuyerTele()+"%'" +
					  " and \"Buyer_id\" like '%"+buyerdata.getBuyerId()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
		
	}
}
