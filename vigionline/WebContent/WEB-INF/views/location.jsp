<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl %>/locations"><%=messages.getMessage("locations") %></a> <span class="divider">/</span></li>
			<li class="active">${it.location.name }</li>
		</ul>
		<!-- End Navigation -->
		<div class="well">
			<form class="close" action="<%= baseUrl %>/locations/${it.location.idLocation }/delete" method="POST">
				<input type="hidden" name="idLocation" value="${it.location.idLocation }" />
				<input type="submit" class="btn btn-danger" value="<%= messages.getMessage("delete_location") %>">
			</form>
			<input name="name" value="${it.location.name }" readonly="readonly" />			
		</div>
		<!-- CAMERAS -->
	<div class="well">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th><%=messages.getMessage("cameras") %></th>
					<th><%=messages.getMessage("active") %></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="camera" items="${it.cameras}">
					<tr>
						<td>${camera.idCamera }</td>
						<td><a href="<%=baseUrl %>/cameras/${camera.idCamera }">${camera.name}</a></td>
						<td><input name="cameras" value="${camera.idCamera }"
							type="checkbox" disabled="disabled" checked="checked"></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
		
	</div>
	<script type="text/javascript">
		$(function()
		{
			AppendToMenu("menu-locations", "<%= baseUrl %>/locations/${it.idLocation }/edit", "<%= messages.getMessage("edit_location") %>");
		});
	</script>
<%@ include file="footer.jsp"%>