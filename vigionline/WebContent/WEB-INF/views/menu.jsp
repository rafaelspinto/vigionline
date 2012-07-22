<div id="menu" class="navbar navbar-static">
	<div class="navbar-inner">
		<div class="container" style="width: auto;">
			<a class="brand" href="${pageContext.request.contextPath}">Vigionline <span class="badge badge-info"><%= messages.getMessage("beta_version") %></span></a>
			<ul class="nav">
				<li class="dropdown">
					<a class="dropdown-toggle"	data-toggle="dropdown" href="#"> <%= messages.getMessage("video") %><b class="caret"></b></a>
						<ul id="menu-video" class="dropdown-menu">
						<li><a href="<%= baseUrl %>/console"><i class="icon-picture"></i><%= messages.getMessage("live") %></a></li>				
						<% if(isAdmin) { %>
							<li class="divider"></li>
							<!-- RECORDINGS -->
							<li><a href="<%= baseUrl %>/recordings"><i class="icon-facetime-video"></i><%= messages.getMessage("recordings") %></a></li>
						<% } %>		
					</ul>
				</li>
				<% if(isAdmin) { %>
					<%@ include file="menu_admin.jsp"%>
				<% } %>
				
				<li class="divider-vertical"></li>
			</ul>
			<ul class="nav pull-right">
				<li><a
					href="${pageContext.request.contextPath}/setLang?lang=pt&country=PT"><img
						src="${pageContext.request.contextPath}/img/portuguese.png"></a></li>
				<li><a
					href="${pageContext.request.contextPath}/setLang?lang=en&country=EN"><img
						src="${pageContext.request.contextPath}/img/english.png"></a></li>
				<li><a href="${pageContext.request.contextPath}/logout"><%= messages.getMessage("logout") %></a></li>
			</ul>
		</div>
	</div>
</div>