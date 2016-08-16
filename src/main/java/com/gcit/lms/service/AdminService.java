package com.gcit.lms.service;

import java.security.Provider.Service;
import java.sql.SQLException;
import java.util.List;

import javax.swing.text.View;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LoansDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Loans;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.entity.bookCopies;

public class AdminService {
	@Autowired
	AuthorDAO aDao;
	@Autowired
	BookDAO bDao;
	@Autowired
	GenreDAO gDao;
	@Autowired
	BorrowerDAO boDao;
	@Autowired
	BranchDAO brDao;
	@Autowired
	BookCopiesDAO bcDao;
	@Autowired
	PublisherDAO pubDao;
	@Autowired
	LoansDAO lDao;
	
	
	@Transactional
	public void addAuthor(Author author) throws SQLException{
		

		aDao.addAuthorBook(author);
	}
	@Transactional
	public void addBorrower(Borrower borrower) throws SQLException{
			boDao.addBorrower(borrower);
	}

	@Transactional
	public void addBranch(Branch branch) throws SQLException{
			brDao.addBranch(branch);
	}
	@Transactional
	public void addBook(Book book) throws SQLException{
	int bId = bDao.addBookWithID(book);
	book.setBookId(bId);
	bDao.addBook(book);
		
		
	}
	@Transactional
	public void addBookCopies(bookCopies bc) throws SQLException{
		
		
			bcDao.addBookCopeies(bc);
		
	}
	@Transactional
	public void addPublisher(Publisher pub) throws SQLException{
		
			pubDao.addPublisher(pub);
	
	}
	@Transactional
	public void deleteAuthor(Author author) throws SQLException{
		aDao.deleteAuthor(author);
		
	}
	@Transactional
	public void deleteBook(Book book) throws SQLException{
		bDao.deleteBook(book);
	}
	@Transactional
	public void deleteBorrower(Borrower borrower) throws SQLException{
	
			boDao.deleteBorrower(borrower);
	
	}
	@Transactional
	public void deleteBranch(Branch branch) throws SQLException{
		
			brDao.deleteBranch(branch);;
		
	}	
	@Transactional
	public void deletePublisher(Publisher publisher) throws SQLException{
		
			pubDao.deletePublisher(publisher);;
		
	}
	@Transactional
	public List<Author> viewAuthors() throws SQLException{
		return aDao.readAllAuthors();
	}
	@Transactional
	public List<Book> viewBooks() throws SQLException{
		return bDao.readAllBooks();
	}
	@Transactional
	public List<Branch> viewBranch() throws SQLException{

			return brDao.readAllBranch();
	}
	@Transactional
	public List<Author> viewBookAuthor(int bookId)
	{
		return aDao.readBookAuthor(bookId);
	}
	@Transactional
	public List<Genre> viewBookGenre(int bookId)
	{
		return gDao.readBookGenre(bookId) ;
	}
	@Transactional
	public List<Publisher> viewBookPublisher(int bookId)
	{
		return pubDao.readBookPublisher(bookId);
	}
	@Transactional
	public List<Book> viewBranchBook(int branchId)
	{
		return bDao.readBranchBook(branchId);
	}
	@Transactional
	public List<Branch> viewBookBranchs(int bookId)
	{
		return brDao.readBookBranchs(bookId);
	}
	@Transactional
	public List<Publisher> viewPublisher() throws SQLException{
	
			return pubDao.readAllPublishers();
	}
	@Transactional
	public List<Genre> viewGenre() throws SQLException{
	
		return gDao.readAllGenre();
	}
	@Transactional
	public List<Borrower> viewBorrower() throws SQLException{

			return boDao.readAllBorrower();
		
	}
	@Transactional
	public List<Borrower> viewBorrower(int brId) throws SQLException{

			return boDao.readAllBorrower(brId);
	}
	@Transactional
	public List<Branch> viewBranch2() throws SQLException{
		
			
			return brDao.readAllBranch();
	
	}
	@Transactional
	public List<Publisher> viewPublisher2() throws SQLException{
	
			
			return pubDao.readAllPublishers();
		
	}
	
	@Transactional
	public Author viewAuthorByID(Integer authorID) throws SQLException{
		return aDao.readByID(authorID);
	}
	@Transactional
	public Book viewBookByID(Integer bookID) throws SQLException{
	
		return bDao.readByID(bookID);
	}
	@Transactional
	public Borrower viewBorrowerByID(Integer cardNo) throws SQLException{
	
			
			return boDao.readByID(cardNo);
		
	}
	@Transactional
	public Branch viewBranchByID(Integer branchId) throws SQLException{
		
			
			return brDao.readByID(branchId);
		
	}
	@Transactional
	public Publisher viewPublisherByID(Integer pubId) throws SQLException{
		
			
			return pubDao.readByID(pubId);
		
	}
	@Transactional
	public List<Book> viewAuthorBook(int authorId)
	{
		return bDao.readAuthorBook(authorId);
	}
	@Transactional
	public List<Book> readBookPublisher(int pubId)
	{
		return bDao.viewBookByPublisher(pubId);
	}
	@Transactional
	public void overWrite(int branchId, int cardNo, int bookId)
	{
		Loans loans =new Loans();
		loans.setBookId(bookId);
		loans.setBranchId(branchId);
		loans.setCardNo(cardNo);
		
		try {
			lDao.overWrite(loans);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
@Transactional
	public void editAuthor(Author author) throws SQLException {
		aDao.updateAuthor(author);
	}
@Transactional
	public void editBook(Book book) throws SQLException {
		bDao.updateBook(book);
	}
@Transactional
	public void editBorrower(Borrower borrower) throws SQLException {
	
			boDao.updateBorrower(borrower);
		
	}
	@Transactional
	public void editBranch(Branch branch) throws SQLException {
		
			brDao.updateBranch(branch);

		
	}
	@Transactional
	public void editPublisher(Publisher publisher) throws SQLException {
		
			pubDao.updatePublisher(publisher);
		
	}
	

	@Transactional
	public void overWrite(Loans l) throws SQLException{
		
			lDao.overWrite(l);
	
	}

}
