<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl %>/manufacturers"><%=messages.getMessage("manufacturers") %></a> <span class="divider">/</span></li>
			<li class="active">${it.name }</li>
		</ul>
		<!-- End Navigation -->
		<div class="well">
			<form class="form-inline" action="<%= baseUrl %>/manufacturers/${it.idManufacturer }/delete" method="POST">
				<input type="hidden" name="idManufacturer" value="${it.idManufacturer }" />
				<a class="btn btn-primary" href="<%= baseUrl %>/manufacturers/${it.idManufacturer }/edit"><%= messages.getMessage("edit_manufacturer") %></a>
				<input type="submit" class="btn btn-danger" href="" value="<%= messages.getMessage("delete_manufacturer") %>">
			</form>
		</div>
		<div class="well">
			<input name="name" value="${it.name }" readonly="readonly" />			
		</div>
	</div>
<%@ include file="footer.jsp"%>