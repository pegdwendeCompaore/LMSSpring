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

import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;



public class PublisherDAO extends BaseDAO implements ResultSetExtractor<List<Publisher>>{

	
	
	
	public void addPublisher(Publisher publisher) throws SQLException {
		template.update("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?,?,?)", new Object[] { publisher.getPublisherName(),publisher.getPublisherAddress(),publisher.getPublisherPhone()});
	}
	
	public Integer addPublisherWithID(Publisher publisher) {
		final String publisherName = publisher.getPublisherName();
		final String INSERT_SQL = "insert into tbl_publisher (branchName) values (?) ";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "publisherId" });
				ps.setString(1, publisherName);
				return ps;
			}
		}, keyHolder);
		Integer publisherId = keyHolder.getKey().intValue();
		return publisherId;

	}
	
	

	public void updatePublisher(Publisher publisher) throws SQLException {
		template.update("update tbl_publisher set publisherName = ?, publisherAddress = ?,publisherPhone=?  where publisherId = ?",
				new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone(), publisher.getPublisherId() });
	}

	public void deletePublisher(Publisher publisher) throws SQLException {
		template.update("delete from tbl_publisher where publisherId = ?", new Object[] { publisher.getPublisherId() });
	}
	public List<Publisher> readBookPublisher (int bookId)
	{
		return template.query("select * from tbl_publisher where publisherId IN (select pubId from tbl_book where bookId = ?)",
				new Object[] { bookId}, this);
	}
	
	public List<Publisher> readAllPublishers() throws SQLException {
		
		return template.query("select * from tbl_publisher", this);
	}
	
//	public Integer getPublisherCount() throws SQLException {
//		return getCount("select count(*) from tbl_publisher", null);
//	}
	
	@Override
	public List<Publisher> extractData(ResultSet rs) {
		List<Publisher> publishers = new ArrayList<Publisher>();
	
		try {
			while (rs.next()) {
				Publisher p = new Publisher();
				p.setPublisherId(rs.getInt("publisherId"));
				
				p.setPublisherName(rs.getString("publisherName"));
				p.setPublisherAddress(rs.getString("publisherAddress"));
				p.setPublisherPhone(rs.getString("publisherPhone"));
				publishers.add(p);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return publishers;
	}


	public Publisher readByID(Integer pubID) throws SQLException {
		List<Publisher> publishers = template.query("select * from tbl_publisher where publisherId = ?", new Object[] {pubID},this);
		if(publishers!=null){
			return publishers.get(0);
		}
		return null;
	}

}
