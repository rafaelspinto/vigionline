<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl %>/locations"><%=messages.getMessage("locations") %></a> <span class="divider">/</span></li>
			<li class="active">${it.name }</li>
		</ul>
		<!-- End Navigation -->
		<div class="well">
			<form class="form-inline" action="<%= baseUrl %>/locations/${it.idLocation }/delete" method="POST">
				<input type="hidden" name="idLocation" value="${it.idLocation }" />
				<a class="btn btn-primary" href="<%= baseUrl %>/locations/${it.idLocation }/edit"><%= messages.getMessage("edit_location") %></a>
				<input type="submit" class="btn btn-danger" href="" value="<%= messages.getMessage("delete_location") %>">
			</form>
		</div>
		<div class="well">
			<input name="name" value="${it.name }" readonly="readonly" />			
		</div>
	</div>
<%@ include file="footer.jsp"%>