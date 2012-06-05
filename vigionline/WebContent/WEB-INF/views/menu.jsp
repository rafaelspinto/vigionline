<div id="menu" class="navbar navbar-static">
	<div class="navbar-inner">
		<div class="container" style="width: auto;">
			<a class="brand" href="${pageContext.request.contextPath}">Vigionline <span class="badge badge-info"><%= messages.getMessage("beta_version") %></span></a>
			<ul class="nav">
				<li><a href="<%= baseUrl %>/console"><%= messages.getMessage("console") %></a></li>
				<li class="divider-vertical"></li>

				<% if(isAdmin) { %>
					<%@ include file="menu_admin.jsp"%>
				<% } %>
				
				<li class="divider-vertical"></li>
			</ul>
			<ul class="nav pull-right">
				<li><a
					href="${pageContext.request.contextPath}/setLang?lang=pt&country=PT"><img
						src="${pageContext.request.contextPath}/images/portuguese.png"></a></li>
				<li><a
					href="${pageContext.request.contextPath}/setLang?lang=en&country=EN"><img
						src="${pageContext.request.contextPath}/images/english.png"></a></li>
				<li><a href="${pageContext.request.contextPath}/logout"><%= messages.getMessage("logout") %></a></li>
			</ul>
		</div>
	</div>
</div>