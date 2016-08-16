

<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gcit.lms.service.*"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%
Integer brId = Integer.parseInt(request.getAttribute("branchId").toString());
Integer cardNo = Integer.parseInt(request.getAttribute("cardNo").toString());
ApplicationContext contex = RequestContextUtils.getWebApplicationContext(request);
BorrowerService service = (BorrowerService) contex.getBean("BorrowerService") ;
	
	List<bookCopies> bc = new ArrayList<bookCopies>();
		bc = service.viewtitle2(brId,cardNo);
		
	
	
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
<div class="modal-header">
		
	Select a Book 
</div>
<div class="modal-body">	
<form action="checked" method="post">
<select class="selectpicker" name="bookId" style="align:center">

	<%
					for (bookCopies b : bc) {
				%>


	<option value="<%=b.getBookId() %>" ><%=b.getTitle() %></option>
	
	<%
					}
				%>


</select>
<input
			type="hidden" name="branchId" value="<%=brId%>">
			<input
			type="hidden" name="cardNo" value="<%=cardNo%>">
<br/> <br/>
<button type="submit">check Out!</button>
</form>
</div>
</div>