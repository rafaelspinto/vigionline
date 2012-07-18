<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl %>/manufacturers"><%=messages.getMessage("manufacturers") %></a> <span class="divider">/</span></li>
			<li class="active">${it.manufacturer.name }</li>
		</ul>
		<!-- End Navigation -->
		<div class="well">
			<div id="context-menu"></div>
			<input name="name" value="${it.manufacturer.name }" readonly="readonly" />			
		</div>
			<table class="table table-bordered">
				<thead><tr><th>ID</th><th><%=messages.getMessage("model") %></th></tr></thead>
				<tbody>
					<c:forEach var="m" items="${it.models}">
					<tr>
						<td>${m.idModel }</td>
						<td><a href="<%=baseUrl %>/models/${m.idModel }">${m.name}</a></td>
					</tr>	
					</c:forEach>
				</tbody>	
			</table>			
	</div>
	<script type="text/javascript">
		$(function()
		{
			AppendToMenu("context-menu", "<%= baseUrl %>/manufacturers/${it.manufacturer.idManufacturer }/edit", "<%= messages.getMessage("edit_manufacturer") %>");
			makeDeleteModelForm
			(
					"context-menu", 
					"<%= baseUrl %>/manufacturers/${it.manufacturer.idManufacturer }/delete", 
					"idManufacturer", 
					"${it.manufacturer.idManufacturer }", 
					"<%= messages.getMessage("delete_manufacturer") %>"
			);
		});
	</script>
<%@ include file="footer.jsp"%>