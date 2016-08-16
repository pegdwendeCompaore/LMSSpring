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

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Branch;

public class BranchDAO extends BaseDAO implements ResultSetExtractor<List<Branch>>{

	

	public void addBranch(Branch branch) throws SQLException
		{	
			int brId = addBranchWithID(branch);
			for (Book b: branch.getBooks())
			{
			template.update("insert into tbl_book_copies (bookId, branchId, noOfCopies) values (?,?,?)",new Object[]{b.getBookId(),brId, 1});	
			}
			
		}
		
	public Integer addBranchWithID(Branch branch) {
		final String branchName = branch.getBranchName();
		final String branchAddress = branch.getBranchAddres(); 
		final String INSERT_SQL = "insert into tbl_library_branch (branchName, branchAddress) values (?,?) ";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "branchId" });
				ps.setString(1, branchName);
				ps.setString(2, branchAddress);
				return ps;
			}
		}, keyHolder);
		Integer branchId = keyHolder.getKey().intValue();
		return branchId;

	}
		public void updateBranch(Branch branch) throws SQLException {
			
			template.update("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?",
					new Object[] { branch.getBranchName(), branch.getBranchAddres(), branch.getBranchId() });
			
			if(branch.getBooks()!=null)
			{template.update("delete from tbl_book_copies where branchId = ?",new Object[] { branch.getBranchId() });
				for(Book b: branch.getBooks())
				{
					template.update("insert tbl_book_copies (bookId, branchId,noOfCopies) values (?,?,?)",
							new Object[] { b.getBookId() , branch.getBranchId(), 1 });
				}
			}
		}
	
		public void deleteBranch(Branch branch) throws SQLException {
			template.update("delete from tbl_library_branch where branchId = ? ", new Object[] { branch.getBranchId()});
		}

		

		public List<Branch> readAllBranch2(int cardNo) throws SQLException {
			
			return template.query("Select distinct tbl_library_branch.branchId, tbl_library_branch.branchName, tbl_library_branch.branchAddress from tbl_library_branch join tbl_book_loans where tbl_library_branch.branchId = tbl_book_loans.branchId and tbl_book_loans.cardNo = ? and dateIn is null ", new Object[] {cardNo},this);
		}
		

		
	
		public List<Branch> readAllBranch() throws SQLException {
			
			return template.query("select * from tbl_library_branch", this);
		}
	
		public List<Branch> readBookBranchs (int bookId)
		{
			return template.query("select * from tbl_library_branch where branchId IN (select branchId from tbl_book_copies where bookId = ?)",
					new Object[] { bookId}, this);
		}
		
		@Override
		public List<Branch> extractData(ResultSet rs) {
			List<Branch> branch = new ArrayList<Branch>();
			
			try {
				while (rs.next()) {
					Branch b = new Branch();
					b.setBranchId(rs.getInt("branchId"));
					b.setBranchName(rs.getString("branchName"));
					b.setBranchAddres(rs.getString("branchAddress"));
					branch.add(b);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return branch;
		}
	
	
		public Branch readByID(Integer branchID) throws SQLException {
			List<Branch> branch = template.query("select * from tbl_library_branch where branchId = ?", new Object[] {branchID},this);
			if(branch!=null){
				return branch.get(0);
			}
			return null;
		}

}
