<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@include file="admin.html"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%@ page import="com.gcit.lms.service.AdminService"%>
<%@ page import="java.util.*"%>
<%

	Integer authorID = Integer.parseInt(request.getParameter("authorId"));
	Author author = null;
	List <Integer>authorsId = new ArrayList<Integer>();
	ApplicationContext contex = RequestContextUtils.getWebApplicationContext(request);
	AdminService service = (AdminService) contex.getBean("AdminService") ;
	
	author = service.viewAuthorByID(authorID);
	List<Book> book1 = service.viewAuthorBook(authorID);
	for(Book b:book1)
	{
		authorsId.add(b.getBookId());
	}
	request.setAttribute("authorsId", authorsId);
	List<Book> book = service.viewBooks();
	
	
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
<div class ="center">
	<h2>Hello Admin!</h2>
	<h3>Enter Author Details to Edit</h3>

	<form name ="myForm" action="editAuthor"  onsubmit="return validateForm()" method="post">
		Enter Author Name: <input type="text" name="authorName"
			value="<%=author.getAuthorName()%>"><br /> <br /> <br /> <input
			type="hidden" name="authorId" value="<%=author.getAuthorID()%>"><br />   
			
			Select book published <br/>

	<h2 id="multiple-select-boxes"></h2>
  	<select id="books" class="selectpicker" name="booksId" data-live-search="true" multiple>

	<%
					for (Book a : book) {
				%>


	<option value="<%=a.getBookId() %>" ><%=a.getTitle()%></option>
	
	<%
					}
				%>

</select>
<br/><br/>
<button type="submit">Edit Author!</button>
</form>
</div>

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
 
 var authors =<%=request.getAttribute("authorsId") %>;
 $("#books").val(authors);
$("#books").multiselect("authors");

</script>
<script>
function validateForm() {
    var x = document.forms["myForm"]["authorName"].value;
    if (x == null || x == "") {
        alert("please enter an author Name");
        return false;
    }
}
   </script>
<br/> <br />
			
			
	
