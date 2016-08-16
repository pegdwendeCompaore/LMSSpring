package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.entity.bookCopies;

public class LibrarianService {

	@Autowired
	BookCopiesDAO bcDao;

	@Transactional
	public List<bookCopies> getCopies(int branchId) throws SQLException {

		return bcDao.getNCopies(branchId);

	}

	@Transactional
	public void addCopies(bookCopies bookCopies) throws SQLException {
		bcDao.addBookCopeies2(bookCopies);

	}

}
