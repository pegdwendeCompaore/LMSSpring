//package com.gcit.lms.dao;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//
//import com.gcit.lms.entity.BookAuthor;
//
//public class BookAuthorDAO extends BaseDAO{
//
//	public BookAuthorDAO(Connection conn) {
//		super(conn);
//		// TODO Auto-generated constructor stub
//	}
//	
//	
//	public void addBookAuthor(BookAuthor ba) throws SQLException {
//		
//		save("insert into tbl_book_authors (bookId, authorId) values (?, ?)", new Object[] { ba.getBookId(), ba.getAuthorId() });
//	}
//
//
//	@Override
//	public <T> List<T> extractData(ResultSet rs) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <T> List<T> extractDataFirstLevel(ResultSet rs) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
