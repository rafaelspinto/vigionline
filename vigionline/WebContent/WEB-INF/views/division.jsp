<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<div class="container">
	<!-- Begin Navigation -->
	<ul class="breadcrumb">
		<li><a href="<%=baseUrl%>">Home</a> <span class="divider">/</span></li>
		<li><a href="<%=baseUrl%>/divisions"><%=messages.getMessage("divisions")%></a>
			<span class="divider">/</span></li>
		<li class="active">${it.division.name }</li>
	</ul>
	<!-- End Navigation -->
	<div class="well">
		<div id="context-menu"></div>
		<label for="name"><%=messages.getMessage("name")%></label> <input
			name="name" value="${it.division.name }" readonly="readonly" />
	</div>

	<!-- CAMERAS -->
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>ID</th>
				<th><%=messages.getMessage("cameras")%></th>
				<th><%=messages.getMessage("active")%></th>
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
			AppendToMenu("context-menu", "<%=baseUrl%>/divisions/${it.division.idDivision }/edit", "<%=messages.getMessage("edit_division")%>");
			makeDeleteModelForm
			(
					"context-menu", 
					"<%=baseUrl%>/divisions/${it.division.idDivision }/delete", 
					"idDivision", 
					"${it.division.idDivision }", 
					"<%=messages.getMessage("delete_division")%>"
			);
		});
	</script>
<%@ include file="footer.jsp"%>