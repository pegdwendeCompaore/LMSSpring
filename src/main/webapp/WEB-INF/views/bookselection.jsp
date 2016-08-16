<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.gcit.lms.service.BorrowerService"%>
<%@include file="admin.html"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gcit.lms.service.AdminService"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%
Integer brId = Integer.parseInt(request.getParameter("branchId"));
Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));

ApplicationContext contex = RequestContextUtils.getWebApplicationContext(request);
BorrowerService service = (BorrowerService) contex.getBean("BorrowerService") ;

	List<Loans> loans = new ArrayList<Loans>();
		loans = service.viewtitle(brId, cardNo);	
%>

<style>
.center {
    margin: auto;
    text-align: center;
    width: 60%;
    border: 3px solid #73AD21;
    padding: 10px;
}
</style>

<h2 class ="center">Hello Admin!</h2>
<h3 class = "center">Below are a list of Book to choose from.</h3>

<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">   
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<link rel="stylesheet" 
href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css"></style>
<script type="text/javascript" 
src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" 
src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<div class ="center">
<div class="table-responsive">
	<h2>${message}</h2>
		<table id="myTable" class=table-responsive" width="100%" >
			<thead>
				<tr>
					<th>Book ID</th>
					<th>Book Title</th>
					<th>Due Date</th>
					<th>Over Write Due date</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Loans l : loans) {
				%>

				<tr>
					<td><%=l.getBookId() %></td>
					<td><%=l.getTitle() %></td>
					<td><%=l.getDuedate() %></td>
					
					<td>
					
					<button name="Edit" class="btn btn-sm btn-success" onclick="javascript:location.href='overwrite?bookId=<%=l.getBookId() %>&branchId=<%=brId %>&cardNo=<%=cardNo %>'">
						Select</button>
							
				
					</td>
					
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
<script>
$(document).ready(function(){
    $('#myTable').dataTable();
});
</script>
		
	</div>
</div>

<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">

    </div>
  </div>
</div>