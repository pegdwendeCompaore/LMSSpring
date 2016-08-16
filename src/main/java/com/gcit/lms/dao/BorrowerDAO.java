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

import com.gcit.lms.entity.Borrower;


public class BorrowerDAO extends BaseDAO implements ResultSetExtractor<List<Borrower>>{

	
	
	public void addBorrower(Borrower borrower) throws SQLException
	{
		template.update("insert into tbl_borrower (name, address, phone) values (?,?,?)", new Object [] {borrower.getBorrowerName(), borrower.getBorrowerAddress(), borrower.getBorrowerPhone()});
	}
	public Integer addBorrowerWithID(Borrower borrower) {
		final String name = borrower.getBorrowerName();
		final String INSERT_SQL = "insert into tbl_borrower (name) values (?) ";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "cardNo" });
				ps.setString(1, name);
				return ps;
			}
		}, keyHolder);
		Integer cardNo = keyHolder.getKey().intValue();
		return cardNo;

	}
	public void updateBorrower(Borrower borrower) throws SQLException {
		
		template.update("update tbl_borrower set name = ?, address = ? , phone=? where cardNo = ?",
				new Object[] { borrower.getBorrowerName(), borrower.getBorrowerAddress(),borrower.getBorrowerPhone(), borrower.getCarNo() });
	}

	public void deleteBorrower(Borrower borrower) throws SQLException {
		template.update("delete from tbl_borrower where cardNo = ?", new Object[] { borrower.getCarNo()});
	}

	public List<Borrower> readAllBorrower() throws SQLException {
		
		return template.query("select * from tbl_borrower", this);
	}
	public List<Borrower> readAllBorrower(int brId) throws SQLException {
		
		return template.query("Select distinct * from tbl_borrower, tbl_book_loans where tbl_borrower.cardNo = tbl_book_loans.cardNo and tbl_book_loans.branchId = ? and tbl_book_loans.dateIn is null", new Object[] {brId},this);
	}

//	public Integer getBorrowerCount() throws SQLException {
//		return getCount("select count(*) from tbl_borrower", null);
//	}
	
	@Override
	public List<Borrower> extractData(ResultSet rs) {
		List<Borrower> borrower = new ArrayList<Borrower>();
		try {
			while (rs.next()) {
				Borrower b = new Borrower();
				b.setCarNo(rs.getInt("cardNo"));
				b.setBorrowerName(rs.getString("name"));
				b.setBorrowerAddress(rs.getString("address"));
				b.setBorrowerPhone(rs.getString("phone"));
				borrower.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrower;
	}


	public Borrower readByID(Integer boID) throws SQLException {
		List<Borrower> borrower = template.query("select * from tbl_borrower where cardNo = ?", new Object[] {boID},this);
		if(borrower!=null){
			return borrower.get(0);
		}
		return null;
	}
	
	public boolean CheckID(Integer boID) throws SQLException {
		List<Borrower> borrower = template.query("select * from tbl_borrower where cardNo = ?", new Object[] {boID},this);
	
		if(!borrower.isEmpty()){
			return true;
		}
		else {
			return false;
		}
		
	}

}
