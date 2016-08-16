package com.gcit.lms.entity;

public class BookAuthor {
	
	private int bookId;
	private int authorId;
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + authorId;
		result = prime * result + bookId;
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
		BookAuthor other = (BookAuthor) obj;
		if (authorId != other.authorId)
			return false;
		if (bookId != other.bookId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BookAuthor [bookId=" + bookId + ", authorId=" + authorId + "]";
	}
	

}
