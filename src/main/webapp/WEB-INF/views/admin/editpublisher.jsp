<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%@ page import="com.gcit.lms.service.AdminService"%>
<%@include file="admin.html"%>
<%
	Integer pubId = Integer.parseInt(request.getParameter("pubId"));

	Publisher publisher = null;
	ApplicationContext contex = RequestContextUtils.getWebApplicationContext(request);
	AdminService service = (AdminService) contex.getBean("AdminService") ;
	
	publisher = service.viewPublisherByID(pubId);
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
<div class ="center">
	<h2>Hello Admin!</h2>
	<h3>Enter publisher Details to Edit</h3>
	<form name ="myForm" action="editPublisher"  onsubmit="return validateForm()" method="post">
		Enter publisher Name: <input type="text" name="name"
			value="<%=publisher.getPublisherName()  %>"><br /> 
		Enter publisher Address: <input type="text" name="address"
			value="<%=publisher.getPublisherAddress()  %>"><br /> 	
		Enter publisher Phone: <input type="text" name="phone"
			value="<%=publisher.getPublisherPhone() %>"><br /> 	
			<input type="hidden" name="pubId" value="<%=publisher.getPublisherId() %>">
		<button type="submit">Edit publisher!</button>
	</form>
</div>
<script>
function validateForm() {
    var x = document.forms["myForm"]["name"].value;
    if (x == null || x == "") {
        alert("please enter a publisher Name");
        return false;
    }
}
   </script>