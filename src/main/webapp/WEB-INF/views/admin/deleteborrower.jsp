<%@ page import="com.gcit.lms.entity.*"%>
<%@ page import="com.gcit.lms.service.AdminService"%>
<%
	Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
	Borrower borrower;
	AdminService service = new AdminService();
	borrower = service.viewBorrowerByID(cardNo);
%>
<div>
	<h2>Hello Admin!</h2>
	<h3>Click to Delete Borrower</h3>
	<form action="deleteBorrower" method="post">
		 <input
			type="hidden" name="cardNo" value="<%=borrower.getCarNo() %>">
		<button type="submit">delete Book!</button>
	</form>
</div>