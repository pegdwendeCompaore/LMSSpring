
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
<%

	List<Publisher> publisher = new ArrayList<Publisher>();
		publisher = service.viewPublisher();	
	
	
%>
<%
	List<Genre> genre = new ArrayList<Genre>();
		genre = service.viewGenre()	;
%>

<%
	List<Author> authors = new ArrayList<Author>();
		authors = service.viewAuthors()	;
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
	
<form name ="myForm" action="addBook"  onsubmit="return validateForm()" method="post">
<div class="center">
  Enter Book Title: 

<input type="text" name="bookName">
</div>
<div class="center">
Select Authors 

  <select class="selectpicker" name="authors" data-live-search="true" multiple>

	<%
					for (Author au : authors) {
				%>


	<option value="<%=au.getAuthorID() %>" ><%=au.getAuthorName() %></option>
	
	<%
					}
				%>
</select>
  </div>


<div class="center">
Select publisher 
  <select class="selectpicker" name="publishers" data-live-search="true">

	<%
					for (Publisher a : publisher) {
				%>


	<option value="<%=a.getPublisherId()%>" ><%=a.getPublisherName() %></option>
	
	<%
					}
				%>
</select>



  </div>

<div class="center">
Select Book Genre 

  <select class="selectpicker" name="genres" data-live-search="true" multiple>

	<%
					for (Genre a : genre) {
				%>


	<option value="<%=a.getGenreId() %>" ><%=a.getGenreName() %></option>
	
	<%
					}
				%>
</select>
<br/><br/>
<button type="submit" >Add Book!</button>
  </div>


</form>

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
  <script type="text/javascript">
<!--

//-->

  function validateForm() {
    var x = document.forms["myForm"]["bookName"].value;
    if (x == null || x == "") {
        alert("please enter a book Name");
        return false;
    }
}
   </script>
