<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@include file="include.html"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gcit.lms.service.*"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%
Integer brId = Integer.parseInt(request.getAttribute("branchId").toString());
ApplicationContext contex = RequestContextUtils.getWebApplicationContext(request);

LibrarianService service = (LibrarianService) contex.getBean("LibrarianService") ;

	List<bookCopies> bc = new ArrayList<bookCopies>();
		bc = service.getCopies( brId);
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



<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">   
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<link rel="stylesheet" 
href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css"></style>
<script type="text/javascript" 
src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" 
src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<h3 class ="center">Below are a list of Book with existing number of copies</h3>
<div class ="center">
<div class="table-responsive">
<h2>${message}</h2>
<table id="myTable" class="display table" width="100%" >

			<thead>
				<tr>
					<th>Book Title</th>
					<th>No of Copies</th>
					<th>Add Copies</th>
					
				</tr>
			</thead>
			<tbody>
				<%
					for (bookCopies b : bc) {
				%>

				<tr>
					<td><%=b.getTitle() %></td>
					<td><%=b.getNoOfCopies() %></td>
					
					
					<td>
					
					<button name="AddCopies" class="btn btn-sm btn-danger" 
							onclick="window.location.href='add?bookId=<%=b.getBookId()%>&branchId=<%=brId%>' ">Add copies</button>
					
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