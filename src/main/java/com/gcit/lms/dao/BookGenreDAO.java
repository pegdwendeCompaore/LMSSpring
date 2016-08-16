//package com.gcit.lms.dao;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//import com.gcit.lms.entity.BookGenre;
//import com.gcit.lms.entity.bookCopies;
//
//public class BookGenreDAO extends BaseDAO{
//
//	public BookGenreDAO(Connection conn) {
//		super(conn);
//		// TODO Auto-generated constructor stub
//	}
//	
//public void addBookGenre(BookGenre bg) throws SQLException {
//		
//		save("insert into tbl_book_genres (genre_Id, bookId) values (?, ?)", new Object[] { bg.getGenreId(), bg.getBookId() });
//	}
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
