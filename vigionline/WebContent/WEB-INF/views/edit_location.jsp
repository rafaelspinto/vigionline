<%@ include file="header.jsp"%>
<h2><%= messages.getMessage("edit_location") %></h2>
<form action="<%= baseUrl %>/locations/${it.idLocation }/edit" method="POST">
	<label for="name"><%= messages.getMessage("name") %></label>
	<input name="name" value="${it.name }"/>
	<input type="submit" value="<%= messages.getMessage("submit") %>" />
</form>
<%@ include file="footer.jsp"%>