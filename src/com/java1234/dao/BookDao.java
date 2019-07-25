package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java1234.Util.StringUtil;
import com.java1234.model.Book;

/**
 * 图书Dao
 * @author 18221
 *
 */
public class BookDao {

	
	/**
	 * 添加图书
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int  add(Connection con,Book book)throws Exception {
		String sql="insert into t_book values(null,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getSex());
		pstmt.setFloat(4, book.getPrice());
		pstmt.setInt(5, book.getBookTypeId());
		pstmt.setString(6, book.getBookDesc());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 图书查询
	 * @param con
	 * @param bookType
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con,Book book)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_book b,t_booktype bt  where b.bookTypeId=bt.id");
		if(StringUtil.isNotEmpty(book.getBookName())) {
			sb.append(" and bookName like '%"+book.getBookName()+"%'");
		}
		if(StringUtil.isNotEmpty(book.getAuthor())) {
			sb.append(" and author like '%"+book.getAuthor()+"%'");
		}
		if(book.getBookTypeId()!=null&&book.getBookTypeId()!=-1) {
			sb.append(" and b.bookTypeId="+book.getBookTypeId());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	/**
	 * 图书删除
	 */
	public  int delete(Connection con,String id)throws Exception{
		String sql="delete from t_book where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
	
	/**
	 * 图书修改
	 */
	public int update(Connection con,Book book)throws Exception{
		String sql="update t_book set bookName=?,author=?,sex=?,price=?,bookDesc=?,bookTypeId=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());
		pstmt.setString(2,book.getAuthor());
		pstmt.setString(3,book.getSex());
		pstmt.setFloat(4, book.getPrice());
		pstmt.setString(5, book.getBookDesc());
		pstmt.setInt(6, book.getBookTypeId());
		pstmt.setInt(7, book.getId());
		return pstmt.executeUpdate();
		
	}
	public boolean existBookByBookTypeId(Connection con,String bookTypeId)throws Exception{
		String sql="select *from t_book where bookTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, bookTypeId);
		ResultSet rs=pstmt.executeQuery();
		return rs.next();
	}
}
