<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl %>/manufacturers"><%=messages.getMessage("manufacturers") %></a> <span class="divider">/</span></li>
			<li class="active"><%=messages.getMessage("create_manufacturer") %></li>
		</ul>
		<!-- End Navigation -->
		<div class="well">
			<form class="form-inline" action="<%= baseUrl %>/manufacturers/create" method="POST">
				<label for="name"><%= messages.getMessage("name") %></label>
				<input name="name" />
				<input class="btn" type="submit" value="<%= messages.getMessage("submit") %>" />
			</form>	
		</div>
	</div>
<%@ include file="footer.jsp"%>