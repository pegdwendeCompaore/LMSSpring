<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.web.servlet.support.RequestContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%@ page import="com.gcit.lms.service.*"%>
<%@ page import="java.util.*"%>
<%@include file="admin.html"%>
<%
	Integer bookId = Integer.parseInt(request.getParameter("bookId"));
	Book book = null;
	
	ApplicationContext contex = RequestContextUtils.getWebApplicationContext(request);
	AdminService service = (AdminService) contex.getBean("AdminService") ;
	book = service.viewBookByID(bookId);
%>

<%

	
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
	List<Integer> genresId = new ArrayList<Integer>();
	List<Genre> gIds = new ArrayList<Genre>();
	gIds = service.viewBookGenre(bookId);
	for(Genre g:gIds)
	{
		genresId.add( g.getGenreId());
	}
	request.setAttribute("genresId", genresId);
%>

<%
	List<Author> authors = new ArrayList<Author>();
		authors = service.viewAuthors()	;
	List<Integer> authorsId = new ArrayList<Integer>();
	List<Author> aIds = new ArrayList<Author>();
	aIds = service.viewBookAuthor(bookId);
	for(Author a:aIds)
	{
		authorsId.add(a.getAuthorID());
	}
	request.setAttribute("authorsId", authorsId);
	
	List<Integer>pubId = new ArrayList<Integer>();
	List<Publisher> pub = new ArrayList<Publisher>();
	pub = service.viewBookPublisher(bookId);
	for(Publisher p: pub)
	{
		pubId.add(p.getPublisherId());
	}
	request.setAttribute("pubId", pubId);
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
	<form name = "myForm"action="editBook"  onsubmit="return validateForm()" method="post">
<div class ="center">
<div >
		
		
		<h2>Hello Admin!</h2>
	<h3>Enter Book Details to Edit</h3>
		Enter Book title: <input type="text" name="title"
			value="<%=book.getTitle()%>"><br /> <input type="hidden"
			name="bookId" value="<%=book.getBookId()%>"> <br/><br/>
			
			Select Authors

		<select id="authors" class="selectpicker" name="authors" data-live-search="true"
			multiple>

			<%
				for (Author au : authors) {
			%>


			<option value="<%=au.getAuthorID()%>"><%=au.getAuthorName()%></option>

			<%
				}
			%>
		</select>
</div>

<br />
<br />
<div >
	Select publisher <select id ="publisher" class="selectpicker" name="publishers"
		data-live-search="true">

		<%
			for (Publisher a : publisher) {
		%>


		<option value="<%=a.getPublisherId()%>"><%=a.getPublisherName()%></option>

		<%
			}
		%>
	</select>



</div>
<br />
<br />
<div >
	Select Book Genre <select id ="genres" class="selectpicker" name="genres"
		data-live-search="true" multiple>

		<%
			for (Genre a : genre) {
		%>


		<option value="<%=a.getGenreId()%>"><%=a.getGenreName()%></option>

		<%
			}
		%>
	</select><br/><br/>
<button type="submit">Edit Book!</button>
</div>
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
<script>
 
 var authorsId =<%=request.getAttribute("authorsId") %>;
 $("#authors").val(authorsId);
 var pubId =<%=request.getAttribute("pubId") %>;
 $("#publisher").val(pubId);

var genresId =<%=request.getAttribute("genresId") %>;
$("#genres").val(genresId);
</script>
<script>
function validateForm() {
    var x = document.forms["myForm"]["title"].value;
    if (x == null || x == "") {
        alert("please enter a book title");
        return false;
    }
}
   </script>

