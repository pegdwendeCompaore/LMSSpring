<%@ page import="com.gcit.lms.entity.*"%>
<%@ page import="com.gcit.lms.service.AdminService"%>
<%
	Integer pubId = Integer.parseInt(request.getParameter("pubId"));
	Publisher publisher;
	AdminService service = new AdminService();
	publisher = service.viewPublisherByID(pubId);
%>
<div>
	<h2>Hello Admin!</h2>
	<h3>Click to Delete Publisher</h3>
	<form action="deletePublisher" method="post">
		 <input
			type="hidden" name="pubId" value="<%=publisher.getPublisherId() %>">
		<button type="submit">delete Book!</button>
	</form>
</div>