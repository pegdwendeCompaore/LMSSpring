
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gcit.lms.service.*"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%

int brId = Integer.parseInt(request.getAttribute("branchId").toString());
Integer cardNo = Integer.parseInt(request.getAttribute("cardNo").toString());
ApplicationContext contex = RequestContextUtils.getWebApplicationContext(request);
BorrowerService service = (BorrowerService) contex.getBean("BorrowerService") ;

	List<Loans> bc = new ArrayList<Loans>();
		bc = service.viewtitle(brId, cardNo);
		//out.println(brId);
		//out.println(cardNo);
	
%>

<div class="modal-header ">
		
	Select Book title 
	
</div>

<style>
.center {
    margin: auto;
    text-align: center;
    width: 60%;
    border: 3px solid #73AD21;
    padding: 10px;
}

</style>

<h3 class="center">Select the Book you wish to return </h3>

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
					<th>title</th>
					<th>Date out </th>
					<th>dueDate</th>
				
					<th>select</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Loans l : bc) {
				%>

				<tr>
					<td><%=l.getTitle() %></td>
					<td><%=l.getDateOut() %></td>
					<td><%=l.getDuedate() %></td>
				
					
					
					<td>
					
					<button name="Edit" class="btn btn-sm btn-success" 
					 onclick="javascript:location.href='returned?branchId=<%=brId%>&cardNo=<%=cardNo %>&bookId=<%=l.getBookId() %>'" >Select</button>
					
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

<div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">

    </div>
  </div>
</div>
