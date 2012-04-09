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
			<form class="close" action="<%= baseUrl %>/locations/${it.idLocation }/delete" method="POST">
				<input type="hidden" name="idLocation" value="${it.idLocation }" />
				<input type="submit" class="btn btn-danger" value="<%= messages.getMessage("delete_location") %>">
			</form>
			<input name="name" value="${it.name }" readonly="readonly" />			
		</div>
	</div>
	<script type="text/javascript">
		$(function()
		{
			AppendToMenu("menu-locations", "<%= baseUrl %>/locations/${it.idLocation }/edit", "<%= messages.getMessage("edit_location") %>");
		});
	</script>
<%@ include file="footer.jsp"%>