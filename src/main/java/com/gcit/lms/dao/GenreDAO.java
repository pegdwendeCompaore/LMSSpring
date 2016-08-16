package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Genre;

public class GenreDAO extends BaseDAO implements ResultSetExtractor<List<Genre>>{

	
	
	public void addBranch(Genre genre) throws SQLException
	{
		template.update("insert into tbl_genre (genre_name) values (?)", new Object []{genre.getGenreName()});
	}
//	
	
	public void updateGenre(Genre genre) throws SQLException {
		template.update("update tbl_genre set genre_name = ? where genre_Id = ?",
				new Object[] { genre.getGenreName(), genre.getGenreId() });
	}

	public void deleteGenre(Genre genre) throws SQLException {
		template.update("delete from tbl_genre where genreId = ?", new Object[] { genre.getGenreId() });
	}

	public List<Genre> readAllGenre() throws SQLException {
		
		return template.query("select * from tbl_genre", this);
	}
	
	public List<Genre> readBookGenre (int bookId)
	{
		return template.query("select * from tbl_genre where genre_Id IN (select genre_Id from tbl_book_genres where bookId = ?)",
				new Object[] { bookId}, this);
	}
	
	@Override
	public List<Genre> extractData(ResultSet rs) {
		List<Genre> genre = new ArrayList<Genre>();
		
		try {
			while (rs.next()) {
				Genre g = new Genre();
				g.setGenreId(rs.getInt("genre_Id"));
				g.setGenreName(rs.getString("genre_name"));
				genre.add(g);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return genre;
	}

}
