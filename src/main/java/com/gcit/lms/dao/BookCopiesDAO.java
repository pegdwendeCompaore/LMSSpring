package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.bookCopies;

public class BookCopiesDAO extends BaseDAO implements ResultSetExtractor<List<bookCopies>>{

	

	
public void addBookCopeies(bookCopies bc) throws SQLException {
		
		template.update("insert into tbl_book_copies (bookId, branchId, noOfCopies) values (?,?,?) ", new Object[] {  bc.getBookId(), bc.getBranchId(), bc.getNoOfCopies() });
	}

public void addBookCopeies2(bookCopies bc) throws SQLException {
	
	template.update("update tbl_book_copies set noOfCopies = ? where bookId = ? and branchId = ? ", new Object[] { bc.getNoOfCopies(), bc.getBookId(), bc.getBranchId() });
}


public List<bookCopies> readAllTitle(int branchID, int cardNo) throws SQLException {
	
	return template.query("select distinct * from tbl_book join tbl_book_copies on tbl_book_copies.bookId = tbl_book.bookId and tbl_book_copies.noOfCopies >'0' and tbl_book_copies.branchId = ? and tbl_book.bookId not in (select bookId from tbl_book_loans where branchId = ? and cardNo =?)", new Object[] {branchID, branchID,cardNo},this);
}

public List<bookCopies> getNCopies(int branchId) throws SQLException {
	
	return template.query("select tbl_book_copies.noOfCopies, tbl_book.title, tbl_book.bookId from tbl_book_copies, tbl_book where tbl_book_copies.branchId = ? and tbl_book_copies.bookId = tbl_book.bookId", new Object[] {branchId},this);
}
@Override
public List<bookCopies> extractData(ResultSet rs) {
	
	List<bookCopies> bc = new ArrayList<bookCopies>();
	
	try {
		while (rs.next()) {
			bookCopies b = new bookCopies();
			b.setTitle(rs.getString("title"));
			b.setBookId(rs.getInt("bookId"));
			b.setNoOfCopies(rs.getInt("noOfcopies"));
			bc.add(b);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return bc;
}
	

	

}
