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
				<label for="name"><%= messages.getMessage("name") %></label>
				<input name="name" value="${it.name }" readonly="readonly" />
				<label for="username"><%= messages.getMessage("username") %></label>
				<input name="username" value="${it.username }" readonly="readonly"/>
				<label for="password"><%= messages.getMessage("password") %></label>
				<input type="password" name="password" value="${it.password }" readonly="readonly"/>	
		</div>
	</div>
<%@ include file="footer.jsp"%>