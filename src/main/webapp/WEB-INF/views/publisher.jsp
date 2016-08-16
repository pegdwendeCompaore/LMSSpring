<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@include file="include.html"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gcit.lms.service.AdminService"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%
ApplicationContext contex = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) contex.getBean("AdminService") ;

	List<Publisher> publisher = new ArrayList<Publisher>();

		publisher = service.viewPublisher();	
	
	
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

<h3 class ="center">Below are a list of Publisher.</h3>
<div class="center">
<div class="table-responsive">
<table id="myTable" class="display table" width="100%" >
			<thead>
				<tr>
					
					<th>Publisher Name</th>
					<th>Publisher Address</th>
					<th>Publisher Phone</th>
					<th> Books published</th>
					
				</tr>
			</thead>
			<tbody>
				<%
					for (Publisher p : publisher) {
				%>

				<tr>
					
					<td><%=p.getPublisherName() %></td>
					<td><%=p.getPublisherAddress() %></td>
					<td><%=p.getPublisherPhone() %></td>
					<td>
						<%
							if(p.getBook()!=null)
							{
						
						List<Book>books =p.getBook();%>
				
					
						
							<select class="selectpicker" name="bookId" style="align: center">

								<%
									for (Book b : books) {
								%>


								<option ><%=b.getTitle()%></option>

								<%
									}
								%>


							</select>
						<%
							}else
							{
						%>
						<%="No books" %>
						<%} %>
						
						
							
						
						
						
					
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