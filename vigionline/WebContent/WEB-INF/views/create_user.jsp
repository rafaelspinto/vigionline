<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl %>/users"><%=messages.getMessage("users") %></a> <span class="divider">/</span></li>
			<li class="active"><%=messages.getMessage("create_user") %></li>
		</ul>
		<!-- End Navigation -->
		<div class="well">
			<form class="well" action="<%= baseUrl %>/users/create" method="POST">
				<label for="name"><%= messages.getMessage("name") %></label>
				<input name="name" />
				<label for="username"><%= messages.getMessage("username") %></label>
				<input name="username" />
				<label for="password"><%= messages.getMessage("password") %></label>
				<input name="password" />
				<input class="btn" type="submit" value="<%= messages.getMessage("submit") %>" />
			</form>	
		</div>
	</div>
<%@ include file="footer.jsp"%>