<%@ include file="header.jsp"%>
<span class="badge badge-inverse"><%= messages.getMessage("create_location") %></span>
<form action="<%= baseUrl %>locations/create" method="POST">
	<label for="name"><%= messages.getMessage("name") %></label>
	<input name="name" />
	<input type="submit" value="<%= messages.getMessage("submit") %>" />
</form>
<%@ include file="footer.jsp"%>