package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.*;
import com.gcit.lms.entity.*;

public class BorrowerService {

	@Autowired
	BorrowerDAO boDao;
	@Autowired
	BookCopiesDAO bcDao;
	@Autowired
	BookDAO bDao;
	@Autowired
	LoansDAO lDao;
	@Autowired
	BranchDAO brDao;

	@Transactional
	public boolean CheckId(int cardNo) throws SQLException {

		return boDao.CheckID(cardNo);

	}

	@Transactional
	public List<bookCopies> viewtitle2(int branchID, int cardNo) throws SQLException {

		return bcDao.readAllTitle(branchID,cardNo);

	}

	@Transactional
	public List<Loans> viewtitle(int branchId, int cardNo) throws SQLException {

		return lDao.readAllBook(branchId, cardNo);

	}

	@Transactional
	public void checkOut(Loans l) throws SQLException {

		lDao.checkOut(l);

	}

	@Transactional
	public void Return(Loans l) throws SQLException {

		lDao.Return(l);
		;

	}

	@Transactional
	public List<Branch> viewBranch(int cardNo) throws SQLException {
		return brDao.readAllBranch2(cardNo);

	}

}
