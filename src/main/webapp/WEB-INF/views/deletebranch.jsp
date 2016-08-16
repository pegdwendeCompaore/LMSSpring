<%@ page import="com.gcit.lms.entity.*"%>
<%@ page import="com.gcit.lms.service.AdminService"%>
<%
	Integer branchId = Integer.parseInt(request.getParameter("branchId"));
	Branch branch;
	AdminService service = new AdminService();
	branch = service.viewBranchByID(branchId);
%>
<div>
	<h2>Hello Admin!</h2>
	<h3>Click to Delete Branch</h3>
	<form action="deleteBranch" method="post">
		 <input
			type="hidden" name="branchId" value="<%=branch.getBranchId() %>">
		<button type="submit">delete Book!</button>
	</form>
</div>