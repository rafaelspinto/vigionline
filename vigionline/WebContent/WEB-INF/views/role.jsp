<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl %>/roles"><%=messages.getMessage("roles") %></a> <span class="divider">/</span></li>
			<li class="active">${it.name }</li>
		</ul>
		<!-- End Navigation -->
		<div class="well">
			<form class="form-inline" action="<%= baseUrl %>/roles/${it.idRole }/delete" method="POST">
				<input type="hidden" name="idRole" value="${it.idRole }" />
				<a class="btn btn-primary" href="<%= baseUrl %>/roles/${it.idRole }/edit"><%= messages.getMessage("edit_role") %></a>
				<input type="submit" class="btn btn-danger" value="<%= messages.getMessage("delete_role") %>">
			</form>
		</div>
		<div class="well">
			<label for="name"><%= messages.getMessage("name") %></label>
			<input name="name" value="${it.name }" readonly="readonly" />
		</div>
	</div>
<%@ include file="footer.jsp"%>