<%@include file="include.html"%>
<%
Integer bId = Integer.parseInt(request.getAttribute("branchId").toString());
Integer bookId = Integer.parseInt(request.getAttribute("bookId").toString());
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

<h1 class="center">Enter  Number of copies for branch selected :</h1><br/>
 <div class="center">
<form name="myForm" action="addCopi" onsubmit="return validateForm()" method="post">

<input type="number" name="copies"><br /> <br />

<input type="hidden" name="bookId" value="<%=bookId %>"><br /> <br />
<input type="hidden" name="branchId" value="<%=bId %>">

<button type="submit" >Add Copies!</button>
</form>
<script>
  function validateForm() {
    var x = document.forms["myForm"]["copies"].value;
    if (x == null || x == "") {
        alert("please enter a number");
        return false;
    }
}
</script>
</div>

