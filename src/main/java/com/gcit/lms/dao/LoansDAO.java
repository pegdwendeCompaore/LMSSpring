package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.*;

public class LoansDAO extends BaseDAO implements ResultSetExtractor<List<Loans>>{

	
	
	
	
	public void checkOut(Loans l) throws SQLException  {
		template.update("insert into tbl_book_loans (bookId, branchId , cardNo, dateOut, dueDate, dateIn) values (?,?,?, curdate(),DATE_ADD(curdate(),INTERVAL 7 DAY),null)  ",
				new Object[] { l.getBookId() , l.getBranchId(), l.getCardNo() });
	}
	public void overWrite(Loans l) throws SQLException  {
		template.update("Update tbl_book_loans set dueDate = DATE_ADD(dueDate,INTERVAL 7 DAY)  where bookId =? and branchId=? and cardNo=? and dateIn is null",
				new Object[] { l.getBookId() , l.getBranchId(), l.getCardNo() });
	}
	public void Return(Loans l) throws SQLException {
		template.update("Update tbl_book_loans set dateIn = curdate() where bookId =? and branchId=? and cardNo=? ",
				new Object[] { l.getBookId() , l.getBranchId(), l.getCardNo() });
		
	}
	
	
	public List<Loans> readAllBook(int branchId,int cardNo) throws SQLException {

	return template.query("select * from tbl_book join tbl_book_loans on tbl_book.bookId = tbl_book_loans.bookId and tbl_book_loans.branchId = ? and tbl_book_loans.cardNo = ?  and tbl_book_loans.dateIn is null", new Object[]{branchId,cardNo},this);
	}

	
	@Override
	public List<Loans> extractData(ResultSet rs){
	
		
		List<Loans> loans = new ArrayList<Loans>();
		try {
			while(rs.next()){
				Loans b = new Loans();
				b.setBookId(rs.getInt("bookId"));
				b.setTitle(rs.getString("title"));
				b.setDuedate(rs.getString("dueDate"));
				b.setDateOut(rs.getString("dateOut"));
				
				
				loans.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loans;
	}
	

}
