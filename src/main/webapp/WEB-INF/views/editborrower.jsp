<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%@include file="admin.html"%>
<%@ page import="com.gcit.lms.service.AdminService"%>
<%
	Integer boId = Integer.parseInt(request.getParameter("cardNo"));
	Borrower borrower = null;
	
	ApplicationContext contex = RequestContextUtils.getWebApplicationContext(request);
	AdminService service = (AdminService) contex.getBean("AdminService") ;
	
	borrower = service.viewBorrowerByID(boId);
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
	<h3>Enter Borrower Details to Edit</h3>
	<form name ="myForm"action="editBorrower"  onsubmit="return validateForm()" method="post">
		Enter Borrower Name: <input type="text" name="name"
			value="<%=borrower.getBorrowerName()  %>"><br /> 
		Enter Borrower Address: <input type="text" name="address"
			value="<%=borrower.getBorrowerAddress() %>"><br /> 	
		Enter Borrower Phone: <input type="text" name="phone"
			value="<%=borrower.getBorrowerPhone() %>"><br /> 
			<input type="hidden" name="cardNo" value="<%=borrower.getCarNo() %>">
		<button type="submit">Edit Author!</button>
	</form>
</div>
<script>
function validateForm() {
    var x = document.forms["myForm"]["name"].value;
    if (x == null || x == "") {
        alert("please enter a Borrower Name");
        return false;
    }
}
   </script>