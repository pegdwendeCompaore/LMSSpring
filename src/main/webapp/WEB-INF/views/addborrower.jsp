<%@include file="admin.html" %>

<style>
.center {
    margin: auto;
    text-align: center;
    width: 60%;
    border: 3px solid #73AD21;
    padding: 10px;
}
</style>

	
	<h3 class = "center">Enter Borrower Details</h3>
	<form name = "myForm" action="addBorrower"  onsubmit="return validateForm()" method="post">
		<div class = "center">
		Enter Borrower Name: <input type="text" name="borrowerName">
		</div>
		<div class = "center">
		Enter Borrower Address: <input type="text" name="borrowerAddress">
		</div>
		<div class = "center">
		Enter Borrower Phone: <input type= "text" name="borrowerPhone">
		</div>
		<div class = "center">
		<button type="submit">Add Borrower!</button>
		</div>
	</form>
	<script>
	function validateForm() {
    var x = document.forms["myForm"]["borrowerName"].value;
    if (x == null || x == "") {
        alert("please enter a borrowerName Name");
        return false;
    }
}
   </script>