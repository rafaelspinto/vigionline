<%@ include file="header.jsp"%>
<h2><%= messages.getMessage("locations") %></h2>
<form action="<%= baseUrl %>locations/create" method="POST">
	<label for="name"><%= messages.getMessage("name") %></label>
	<input name="name" />
	<input type="submit" value="<%= messages.getMessage("submit") %>" />
</form>
<%@ include file="footer.jsp"%>