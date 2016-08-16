<%@ page import="com.gcit.lms.entity.*"%>
<%@ page import="com.gcit.lms.service.AdminService"%>
<%
	Integer bookID = Integer.parseInt(request.getParameter("bookId"));
	Book book =null;
	AdminService service = new AdminService();
	book = service.viewBookByID(bookID);
%>
<div>
	<h2>Hello Admin!</h2>
	<h3>Click to Delete Book</h3>
	<form action="deleteBook" method="post">
		 <input
			type="hidden" name="bookId" value="<%=book.getBookId()%>">
		<button type="submit">delete Book!</button>
	</form>
</div>