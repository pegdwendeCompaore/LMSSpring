package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;


public class BookDAO extends BaseDAO implements ResultSetExtractor<List<Book>>{

	

	public void addBook(Book book) throws SQLException{
	
		int bId = addBookWithID(book);
	
		for (Author a: book.getAuthors())
		{
			template.update("insert into tbl_book_authors (bookId, authorId) values (?, ?)", new Object[] {bId,a.getAuthorID()});
		}
		for (Genre g: book.getGenres())
		{
			template.update("insert into tbl_book_genres (genre_Id, bookId) values (?, ?)", new Object[] { g.getGenreId(),bId});
		}
		

	}

	public void updateBook(Book book) throws SQLException{


		template.update("update tbl_book set title = ?, pubId = ? where bookId = ?", new Object[] {book.getTitle(),book.getPubId(), book.getBookId()});
		if(book.getGenres()!=null)
		{
			template.update("delete from tbl_book_genres where bookId = ?", new Object[] {book.getBookId()});
			for (Genre g: book.getGenres())
			{

				template.update("insert into tbl_book_genres (genre_Id, bookId) values (?,?)", new Object[] {g.getGenreId(), book.getBookId()});
			}
		}

		if(book.getAuthors()!=null)
		{
			template.update("delete from tbl_book_authors where bookId = ?", new Object[] {book.getBookId()});
			for (Author a : book.getAuthors())
			{

				template.update("insert into tbl_book_authors (bookId, authorId) values (?,?)", new Object[] {book.getBookId(),a.getAuthorID()});
			}
		}


	}

	public void deleteBook(Book book) throws SQLException{
		template.update("delete from tbl_book where bookId = ?", new Object[] {book.getBookId()});
	}
	
	public List<Book> readAllBooks() throws SQLException {
		return template.query("select * from tbl_book", this);
	}
	
	public List<Book> readAllBook(int branchId,int cardNo) throws SQLException {

	return template.query("select * from tbl_book join tbl_book_loans on tbl_book.bookId = tbl_book_loans.bookId and tbl_book_loans.branchId = ? and tbl_book_loans.cardNo = ?  and tbl_book_loans.dateIn is null", new Object[]{branchId,cardNo},this);
	}
	public Integer addBookWithID(Book book) {
		final String bookName = book.getTitle();
		final Integer pubId = book.getPubId();
		final String INSERT_SQL = "insert into tbl_book (title,pubId) values (?,?) ";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "bookId" });
				ps.setString(1, bookName);
				ps.setInt(2, pubId);
				return ps;
			}
		}, keyHolder);
		Integer bookId = keyHolder.getKey().intValue();
		return bookId;

	}
	
	public List<Book> readAuthorBook (int authorId)
	{
		return template.query("select * from tbl_book where bookId IN (select bookId from tbl_book_authors where authorId = ?)",
				new Object[] { authorId}, this);
	}
	public List<Book> readBranchBook (int branchId)
	{
		return template.query("select * from tbl_book where bookId IN (select bookId from tbl_book_copies where branchId = ?)",
				new Object[] { branchId}, this);
	}
	public List<Book>viewBookByPublisher(int pubId)
	{
		return template.query("select * from tbl_book where pubId =? ",
				new Object[] { pubId}, this);
	}

	@Override
	public List<Book> extractData(ResultSet rs){
	
		
		List<Book> books = new ArrayList<Book>();
		try {
			while(rs.next()){
				Book b = new Book();
				b.setBookId(rs.getInt("bookId"));
				b.setTitle(rs.getString("title"));
				b.setPubId(rs.getInt("pubId"));
				
				books.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}




	public Book readByID(Integer bookID) throws SQLException {
		List<Book> books = template.query("select * from tbl_book where bookId = ?", new Object[] {bookID}, this);
		if(books!=null){
			return books.get(0);
		}
		return null;
	}
}
