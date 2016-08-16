<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@include file="admin.html"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gcit.lms.service.AdminService"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%
ApplicationContext contex = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) contex.getBean("AdminService") ;
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

<h3>Enter Branch Details</h3>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="./resources/template_files/css/highlight.css" rel="stylesheet">
<link href="./resources/template_files/css/base.css" rel="stylesheet">
<link href="./resources/template_files/css/custom.css" rel="stylesheet">
<link href="./resources/template_files/css/bootstrap-select.min.css"
	rel="stylesheet">

<form name ="myForm" action="addBranch"  onsubmit="return validateForm()" method="post">
	<h3 class="center">Enter Branch Details</h3>
	<div class="center">
	Enter Branch Name: <input type="text" name="branchName">
	
	<br/><br/>
	 Enter Branch Address: <input type="text" name="branchAddress">
	
 <br/><br/>
	Select book to be added to the branch

	<h2 id="multiple-select-boxes"></h2>
	<select class="selectpicker" name="booksId" data-live-search="true"
		multiple>

		<%
					for (Book a : book) {
				%>


		<option value="<%=a.getBookId() %>"><%=a.getTitle()%></option>

		<%
					}
				%>


	</select>
<br/><br/>
	<button type="submit">Add Branch!</button>
	</div>
</form>

	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
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
function validateForm() {
    var x = document.forms["myForm"]["branchName"].value;
    if (x == null || x == "") {
        alert("please enter a branch Name");
        return false;
    }
}
   </script>
