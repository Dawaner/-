package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import code.BookData;
import code.StringUtil;

public class BookDataDao {
	
//	Ìí¼Ó
	public int addbook(Connection con,BookData bookdata) throws Exception {
		String orcl="insert into BOOK (\"Book_id\", \"Book_name\", \"Book_price\", \"Book_synopsis\", \"Book_sales\") values(?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(orcl);
		pstmt.setString(1, bookdata.getBookId());
		pstmt.setString(2, bookdata.getBookName());
		pstmt.setString(3, bookdata.getBookPrice());
		pstmt.setString(4, bookdata.getBookSynopsis());
		pstmt.setString(5, bookdata.getBookSales());
		return pstmt.executeUpdate();

	}

//	É¾³ý
	public int delete(Connection con,BookData bookdata)throws Exception{
		StringBuffer sb=new StringBuffer("delete from BOOK");
		if(StringUtil.isnotempty(bookdata.getBookId()) || StringUtil.isnotempty(bookdata.getBookName())
				|| 	StringUtil.isnotempty(bookdata.getBookPrice()) || StringUtil.isnotempty(bookdata.getBookSynopsis()) 
				|| StringUtil.isnotempty(bookdata.getBookSales()) ){
				sb.append(" and \"Book_id\" like '%"+bookdata.getBookId()+"%'" +
						  " and \"Book_name\" like '%"+bookdata.getBookName()+"%'" +
						  " and \"Book_price\" like '%"+bookdata.getBookPrice()+"%'" +
						  " and \"Book_synopsis\" like '%"+bookdata.getBookSynopsis()+"%'" +
						  " and \"Book_sales\" like '%"+bookdata.getBookSales()+"%'");
			}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeUpdate();
	}
	
//	ÐÞ¸Ä
	public int update(Connection con,BookData bookdata)throws Exception{
		String orcl="update Book set \"Book_name\"=?, \"Book_price\"=?, \"Book_synopsis\"=?, \"Book_sales\"=? where \"Book_id\"=?";
		PreparedStatement pstmt=con.prepareStatement(orcl);
		pstmt.setString(5, bookdata.getBookId());
		pstmt.setString(1, bookdata.getBookName());
		pstmt.setString(2, bookdata.getBookPrice());
		pstmt.setString(3, bookdata.getBookSynopsis());
		pstmt.setString(4, bookdata.getBookSales());
		return pstmt.executeUpdate();
	}	

//	²éÑ¯
	public ResultSet list(Connection con,BookData bookdata)throws Exception{
		StringBuffer sb=new StringBuffer("select * from BOOK");
		if(StringUtil.isnotempty(bookdata.getBookId()) || StringUtil.isnotempty(bookdata.getBookName())
			|| 	StringUtil.isnotempty(bookdata.getBookPrice()) || StringUtil.isnotempty(bookdata.getBookSynopsis()) 
			|| StringUtil.isnotempty(bookdata.getBookSales()) ){
			sb.append(" and \"Book_id\" like '%"+bookdata.getBookId()+"%'" +
					  " and \"Book_name\" like '%"+bookdata.getBookName()+"%'" +
					  " and \"Book_price\" like '%"+bookdata.getBookPrice()+"%'" +
					  " and \"Book_synopsis\" like '%"+bookdata.getBookSynopsis()+"%'" +
					  " and \"Book_sales\" like '%"+bookdata.getBookSales()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
		
	}
}
