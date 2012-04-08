<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl %>/roles"><%=messages.getMessage("roles") %></a> <span class="divider">/</span></li>
			<li class="active"><%=messages.getMessage("create_role") %></li>
		</ul>
		<!-- End Navigation -->
		<div class="well">
			<form class="well" action="<%= baseUrl %>/roles/create" method="POST">
				<label for="name"><%= messages.getMessage("name") %></label>
				<input name="name" />
				<input class="btn" type="submit" value="<%= messages.getMessage("submit") %>" />
			</form>	
		</div>
	</div>
<%@ include file="footer.jsp"%>