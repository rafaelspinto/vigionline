<%@ include file="header.jsp"%>
<span class="badge badge-inverse">Create Location</span>
<form action="<%= baseUrl %>locations/create" method="POST">
	<label for="name">Name</label>
	<input name="name" />
	<input type="submit" value="Submit" />
</form>
<%@ include file="footer.jsp"%>