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
			<div id="context-menu"></div>
			<input name="name" value="${it.location.name }" readonly="readonly" />			
		</div>
		<!-- CAMERAS -->
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
	<script type="text/javascript">
		$(function()
		{
			AppendToMenu("context-menu", "<%= baseUrl %>/locations/${it.location.idLocation }/edit", "<%= messages.getMessage("edit_location") %>");
			makeDeleteModelForm
			(
					"context-menu", 
					"<%= baseUrl %>/locations/${it.location.idLocation }/delete", 
					"idLocation", 
					"${it.location.idLocation }", 
					"<%= messages.getMessage("delete_location") %>"
			);
		});
	</script>
<%@ include file="footer.jsp"%>