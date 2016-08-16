<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@include file="include.html"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gcit.lms.service.AdminService"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%
ApplicationContext contex = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) contex.getBean("AdminService") ;
	
	List<Branch> branch = new ArrayList<Branch>();
	
		branch = service.viewBranch();	
	
	
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

<h2 class ="center">Hello Librarian!</h2>
<h3 class ="center">Below are a list of Branch to select from.</h3>



<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">   
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<link rel="stylesheet" 
href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css"></style>
<script type="text/javascript" 
src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" 
src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<div class="center">
<div class="table-responsive">
<table id="myTable" class="display table" width="100%" >
			<thead>
				<tr>
					<th>Branch ID</th>
					<th>branch Name</th>
					<th>branch Address</th>
					<th>Edit Branch</th>
					<th>Add Copy</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Branch b : branch) {
				%>

				<tr>
					<td><%=b.getBranchId() %></td>
					<td><%=b.getBranchName() %></td>
					<td><%=b.getBranchAddres() %></td>
					
					
					<td>
					
					<button name="Edit" class="btn btn-sm btn-success"
					onclick="window.location.href='edit?branchId=<%=b.getBranchId() %>'">Edit</button>
				
				
					</td>
					
					<td>
					
					<button name="addCopy" class="btn btn-sm btn-danger" 
							onclick="window.location.href='addcopies?branchId=<%=b.getBranchId()%>'" >Select</button>
					
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

<footer id="myFooter" style="margin-top:10px; margin-right:5px;">
    <div class="w3-container w3-theme-l2 w3-padding-32">
      <h4>Footer</h4>
    </div>

    <div class="w3-container w3-theme-l1">
      <p>Powered by <a href="http://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
    </div>
  </footer>