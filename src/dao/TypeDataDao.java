package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import code.TypeData;
import code.PayData;
import code.StringUtil;

public class TypeDataDao {
	
//	Ìí¼Ó
	public int addtype(Connection con,TypeData typedata) throws Exception {
		String orcl="insert into TYPE (\"Type_id\", \"Type_name\") values(?,?)";
		PreparedStatement pstmt=con.prepareStatement(orcl);
		pstmt.setString(1, typedata.getTypeId());
		pstmt.setString(2, typedata.getTypeName());
		return pstmt.executeUpdate();

	}
	
//	É¾³ý
	public int delete(Connection con,TypeData typedata)throws Exception{
		StringBuffer sb=new StringBuffer("delete from Type");
		if(StringUtil.isnotempty(typedata.getTypeId()) || StringUtil.isnotempty(typedata.getTypeName())){
			sb.append(" and \"Type_id\" like '%"+typedata.getTypeId()+"%'" +
					  "and \"Type_name\" like '%"+typedata.getTypeName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeUpdate();
	}
	
//	ÐÞ¸Ä
	public int update(Connection con,TypeData typedata)throws Exception{
		String orcl="update Type set \"Type_name\"=? where \"Type_id\"=?";
		PreparedStatement pstmt=con.prepareStatement(orcl);
		pstmt.setString(2, typedata.getTypeId());
		pstmt.setString(1, typedata.getTypeName());
		return pstmt.executeUpdate();
	}

//	²éÑ¯
	public ResultSet list(Connection con,TypeData typedata)throws Exception{
		StringBuffer sb=new StringBuffer("select * from TYPE");
		if(StringUtil.isnotempty(typedata.getTypeId()) || StringUtil.isnotempty(typedata.getTypeName())){
			sb.append(" and \"Type_id\" like '%"+typedata.getTypeId()+"%'" +
					  "and \"Type_name\" like '%"+typedata.getTypeName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
		
	}
}
