<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<div class="container">
	<!-- Begin Navigation -->
	<ul class="breadcrumb">
		<li><a href="<%=baseUrl%>">Home</a> <span class="divider">/</span></li>
		<li><a href="<%=baseUrl%>/divisions"><%=messages.getMessage("divisions")%></a>
			<span class="divider">/</span></li>
		<li><a href="<%=baseUrl %>/divisions/${it.division.idDivision}">${it.division.name
				}</a> <span class="divider">/</span></li>
		<li class="active"><%=messages.getMessage("edit_division")%></li>
	</ul>
	<!-- End Navigation -->

	<form class="well"
		action="<%= baseUrl %>/divisions/${it.division.idDivision}/edit"
		method="POST">
		<input type="hidden" name="idDivision"
			value="${it.division.idDivision}" /> <label for="name"><%=messages.getMessage("name")%></label>
		<input name="name" value="${it.division.name }" />

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
			<c:forEach var="camera" items="${it.camerasInDivision}">
				<tr>
					<td>${camera.idCamera }</td>
					<td><a href="<%=baseUrl %>/cameras/${camera.idCamera }">${camera.name}</a></td>
					<td><input name="cameras" value="${camera.idCamera }"
						type="checkbox" checked="checked"></td>
				</tr>
			</c:forEach>
			<c:forEach var="camera" items="${it.camerasNotInDivision}">
					<tr>
						<td>${camera.idCamera }</td>
						<td><a href="<%=baseUrl %>/cameras/${camera.idCamera }">${camera.name}</a></td>
						<td><input name="cameras" value="${camera.idCamera }"
							type="checkbox"></td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>

		<input class="btn" type="submit"
			value="<%=messages.getMessage("submit")%>" />
	</form>
</div>
<%@ include file="footer.jsp"%>