<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl %>/users"><%=messages.getMessage("users") %></a> <span class="divider">/</span></li>
			<li class="active">${it.name }</li>
		</ul>
		<!-- End Navigation -->
		<div class="well">
			<form class="form-inline" action="<%= baseUrl %>/users/${it.idUser }/delete" method="POST">
				<input type="hidden" name="idUser" value="${it.idUser }" />
				<a class="btn btn-primary" href="<%= baseUrl %>/users/${it.idUser }/edit"><%= messages.getMessage("edit_user") %></a>
				<input type="submit" class="btn btn-danger" value="<%= messages.getMessage("delete_user") %>">
			</form>
		</div>
		<div class="well">
			<form class="well" action="<%= baseUrl %>/users/${it.idUser }/edit" method="POST">
				<input type="hidden" name="idUser" value="${it.idUser }" />
				<label for="name"><%= messages.getMessage("name") %></label>
				<input name="name" value="${it.name }" />
				<label for="username"><%= messages.getMessage("username") %></label>
				<input name="username" value="${it.username }" />
				<label for="password"><%= messages.getMessage("password") %></label>
				<input type="password" name="password" value="${it.password }" />
				<input class="btn" type="submit" value="<%= messages.getMessage("submit") %>" />
			</form>	
		</div>
	</div>
<%@ include file="footer.jsp"%>