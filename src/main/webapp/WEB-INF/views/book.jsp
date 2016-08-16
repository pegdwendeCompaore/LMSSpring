<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@include file="include.html"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gcit.lms.service.AdminService"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%
ApplicationContext contex = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) contex.getBean("AdminService") ;
	List<Book> books = new ArrayList<Book>();
		books = service.viewBooks();	
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

<h3 class ="center">Below are a list of Book.</h3>
<div class="center">
<div class="table-responsive">

<table id="myTable" class="display table" width="100%" >
		
			<thead>
				<tr>
					
					<th>Book Title</th>
					<th>book Authors</th>
					<th>Book genres </th>
					<th> publisher </th>
					
				</tr>
			</thead>
			<tbody>
				<%
					for (Book b : books) {
				%>

				<tr>
					
					<td><%=b.getTitle() %></td>
			
					<td>
					<%List<Author>author =service.viewBookAuthor(b.getBookId()); 
					
					if(author!=null &&!author.isEmpty())
					{%>
					
							
								<%
									for (Author a : author) {
								%>
								<%=a.getAuthorName()+"." %>

								<%
									}
					}else
									{
								%>
							<%="No author" %>
							<%} %>

							
					
					</td>
					<td>
					<%List<Genre>genre =service.viewBookGenre(b.getBookId()); 
					
					if(genre!=null&&!genre.isEmpty()){%>
					
							

								<%
									for (Genre g : genre) {
								%>
					

								<%=g.getGenreName()+"." %>

								<%
									}
					}else{
								%>
					<%="No genre" %>
					<%} %>

						
					
					</td>
					<td> 
					<%
					String publisher="no publisher added";
						if(!service.viewBookPublisher(b.getBookId()).isEmpty() )
						{	
						 publisher =service.viewBookPublisher(b.getBookId()).get(0).getPublisherName();}
					
					
					%>
					<%=publisher %>
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