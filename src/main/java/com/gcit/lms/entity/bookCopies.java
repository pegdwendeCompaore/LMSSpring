package com.gcit.lms.entity;

import java.io.Serializable;

public class bookCopies implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4945219260769303098L;
	
	private int bookId;
	private int branchId;
	private int noOfCopies;
	private String title;
	private String branchName;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public int getNoOfCopies() {
		return noOfCopies;
	}
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
		result = prime * result + branchId;
		result = prime * result + noOfCopies;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		bookCopies other = (bookCopies) obj;
		if (bookId != other.bookId)
			return false;
		if (branchId != other.branchId)
			return false;
		if (noOfCopies != other.noOfCopies)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "bookCopies [bookId=" + bookId + ", branchId=" + branchId
				+ ", noOfCopies=" + noOfCopies + "]";
	}
	
	
	

}
