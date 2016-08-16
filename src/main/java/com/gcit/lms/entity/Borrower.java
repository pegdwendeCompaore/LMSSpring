package com.gcit.lms.entity;

import java.util.List;

public class Borrower {
	private int carNo;
	private String borrowerName;
	private String borrowerAddress;
	private String borrowerPhone;
	private String dueDate;
	private int branchId;
	List <Book> book;
	List<Branch> branch;
	
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public int getCarNo() {
		return carNo;
	}
	public void setCarNo(int carNo) {
		this.carNo = carNo;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getBorrowerAddress() {
		return borrowerAddress;
	}
	public void setBorrowerAddress(String borrowerAddress) {
		this.borrowerAddress = borrowerAddress;
	}
	public String getBorrowerPhone() {
		return borrowerPhone;
	}
	public void setBorrowerPhone(String borrowerPhone) {
		this.borrowerPhone = borrowerPhone;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((borrowerAddress == null) ? 0 : borrowerAddress.hashCode());
		result = prime * result
				+ ((borrowerName == null) ? 0 : borrowerName.hashCode());
		result = prime * result
				+ ((borrowerPhone == null) ? 0 : borrowerPhone.hashCode());
		result = prime * result + carNo;
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
		Borrower other = (Borrower) obj;
		if (borrowerAddress == null) {
			if (other.borrowerAddress != null)
				return false;
		} else if (!borrowerAddress.equals(other.borrowerAddress))
			return false;
		if (borrowerName == null) {
			if (other.borrowerName != null)
				return false;
		} else if (!borrowerName.equals(other.borrowerName))
			return false;
		if (borrowerPhone == null) {
			if (other.borrowerPhone != null)
				return false;
		} else if (!borrowerPhone.equals(other.borrowerPhone))
			return false;
		if (carNo != other.carNo)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Borrower [carNo=" + carNo + ", borrowerName=" + borrowerName
				+ ", borrowerAddress=" + borrowerAddress + ", borrowerPhone="
				+ borrowerPhone + "]";
	}
	public List<Book> getBook() {
		return book;
	}
	public void setBook(List<Book> book) {
		this.book = book;
	}
	public List<Branch> getBranch() {
		return branch;
	}
	public void setBranch(List<Branch> branch) {
		this.branch = branch;
	}

	

}
