<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@include file="admin.html"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%@ page import="com.gcit.lms.service.AdminService"%>
<%@ page import="java.util.*"%>
<%
	Integer brId = Integer.parseInt(request.getParameter("branchId"));
	Branch branch = null;
	ApplicationContext contex = RequestContextUtils.getWebApplicationContext(request);
	AdminService service = (AdminService) contex.getBean("AdminService") ;
	
	branch = service.viewBranchByID(brId);
	
	List<Integer> booksId =new ArrayList<Integer>();
	List<Book> books =new  ArrayList<Book>();
	books = service.viewBranchBook(brId);
	for(Book b:books)
	{
		booksId.add(b.getBookId());
	}
	request.setAttribute("booksId", booksId);
	
%>


<%
	List<Book> book = new ArrayList<Book>();
	
	book = service.viewBooks();	
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
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
  <link href="./resources/template_files/css/highlight.css" rel="stylesheet">
  <link href="./resources/template_files/css/base.css" rel="stylesheet">
  <link href="./resources/template_files/css/custom.css" rel="stylesheet">
  <link href="./resources/template_files/css/bootstrap-select.min.css" rel="stylesheet">

	
	<h3 class="center">Enter Branch Details to Edit</h3>
	
	<div class ="center">
	<form name ="myForm" action="editBranch"  onsubmit="return validateForm()" method="post">
	
		Enter Branch Name: <input type="text" name="name"
		
			value="<%=branch.getBranchName()  %>"><br /> <br/>
			
		Enter Branch Address: <input type="text" name="address"
		
			value="<%=branch.getBranchAddres()  %>"><br /> 	<br/>
			<input type="hidden" name="branchId" value="<%=branch.getBranchId() %>">
			
			Select book in branch <br/><br/>

	<h2 id="multiple-select-boxes"></h2>
  	<select id ="books" class="selectpicker" name="booksId" data-live-search="true" multiple>

	<%
					for (Book a : book) {
				%>


	<option value="<%=a.getBookId() %>" ><%=a.getTitle()%></option>
	
	<%
					}
				%>


</select>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="./resources/template_files/js/highlight.pack.js"></script>
<script src="./resources/template_files/js/base.js"></script>
<script src="./resources/template_files/js/bootstrap-select.min.js"></script>

<script type="text/javascript">
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-35848102-1']);
  _gaq.push(['_trackPageview']);

  (function () {
    var ga = document.createElement('script');
    ga.type = 'text/javascript';
    ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(ga, s);
  })();
</script>
<script>
 
 var booksId =<%=request.getAttribute("booksId") %>;
 $("#books").val(booksId);
 </script>
 
 <script>
function validateForm() {
    var x = document.forms["myForm"]["name"].value;
    if (x == null || x == "") {
        alert("please enter an Branch Name");
        return false;
    }
}
   </script>
<br/> <br />
			
			
			
			
		<button type="submit">Edit Author!</button>
	</form>
</div>