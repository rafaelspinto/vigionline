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
			<form class="close" action="<%= baseUrl %>/roles/${it.idRole }/delete" method="POST">
				<input type="hidden" name="idRole" value="${it.idRole }" />
				<input type="submit" class="btn btn-danger" value="<%= messages.getMessage("delete_role") %>">
			</form>
			<label for="name"><%= messages.getMessage("name") %></label>
			<input name="name" value="${it.name }" readonly="readonly" />
		</div>
	</div>
	<script type="text/javascript">
			$(function()
			{
				AppendToMenu("menu-roles", "<%= baseUrl %>/roles/${it.idRole }/edit", "<%= messages.getMessage("edit_role") %>");
			});
		</script>
<%@ include file="footer.jsp"%>