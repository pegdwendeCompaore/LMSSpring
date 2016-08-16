<%@ page import="com.gcit.lms.entity.Author"%>
<%@ page import="com.gcit.lms.service.AdminService"%>
<%
	Integer authorID = Integer.parseInt(request.getParameter("authorId"));
	Author author = null;
	AdminService service = new AdminService();
	author = service.viewAuthorByID(authorID);
%>
<div>
	<h2>Hello Admin!</h2>
	<h3> Click to Delete Author</h3>
	<form action="deleteAuthor" method="post">
		 <input
			type="hidden" name="authorId" value="<%=author.getAuthorID()%>">
		<button type="submit">delete Author!</button>
	</form>
</div>