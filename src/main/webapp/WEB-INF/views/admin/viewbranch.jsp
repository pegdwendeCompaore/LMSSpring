<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@include file="admin.html"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gcit.lms.service.AdminService"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%
ApplicationContext contex = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) contex.getBean("AdminService") ;
	
	List<Branch> branch = new ArrayList<Branch>();
	
		branch = service.viewBranch();	
	
	
	
%>

<h2>Hello Admin!</h2>
<h2 class ="center">${message}</h2>
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

<h3 class="center">Below are a list of Branch.</h3>

<div class="center">
<div class="table-responsive">
<table id="myTable" class="display table" width="100%" >
			<thead>
				<tr>
					
					<th>branch Name</th>
					<th>branch Address</th>
					<th>branch book</th>
					<th>Edit Branch</th>
					<th>Delete Branch</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Branch br : branch) {
				%>

				<tr>
					
					<td><%=br.getBranchName() %></td>
					<td><%=br.getBranchAddres() %></td>	
					<td>
						<%List<Book> book = service.viewBranchBook(br.getBranchId()); 
						
						if(book!=null&& !book.isEmpty())
						{
						for(Book b:book)
						{
							%>
							
							<%=b.getTitle()+"." %>
							
						<% 	
						}
						}
						else 
						{
						%>
						<%="no books." %>
					<%}
						
						%>
					</td>
					<td>
					
					<button name="Edit" class="btn btn-sm btn-success" 
					onclick="javascript:location.href='editbranch?branchId=<%=br.getBranchId() %>'">Edit</button>
				
				
					</td>
					
					<td>
					
					<button name="Delete" class="btn btn-sm btn-danger" 
							onclick="javascript:location.href='deleteBranch?branchId=<%=br.getBranchId() %>'">Delete</button>
					
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