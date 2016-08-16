<%@include file="include.html" %>
<%
	
Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
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
<h2 class ="center"> ${message}</h2>
<h2 class ="center">Hello Client!</h2>
<h3 class ="center">Choose your action.</h3>

<div class ="center">
<button name="CheckOUt" class="btn btn-sm btn-success" 
					onclick="window.location.href='checkOut?cardNo=<%=cardNo%>'" class ="center">check Out a Book</button> <br/>
<br/>
<br/>
<button name="CheckIn" class="btn btn-sm btn-success" 
					onclick="window.location.href='checkIn?cardNo=<%=cardNo%>'" class ="center">Return a book</button><br/>
</div>