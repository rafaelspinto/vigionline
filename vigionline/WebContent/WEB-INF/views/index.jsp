<%@ include file="header.jsp"%>
<ul class="nav nav-tabs nav-stacked">
	<li class="nav-header">VRI</li>
	<li><a href="${pageContext.request.contextPath}/api/locations">LocationsResource</a></li>
</ul>

<ul class="nav nav-tabs nav-stacked">
	<li class="nav-header">VWC</li>
	<li><a href="${pageContext.request.contextPath}/locations"><%= messages.getMessage("locations") %></a></li>
</ul>
<%@ include file="footer.jsp"%>