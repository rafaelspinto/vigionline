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
			<form class="form-inline" action="<%= baseUrl %>/manufacturers/${it.manufacturer.idManufacturer }/delete" method="POST">
				<input type="hidden" name="idManufacturer" value="${it.manufacturer.idManufacturer }" />
				<a class="btn btn-primary" href="<%= baseUrl %>/manufacturers/${it.manufacturer.idManufacturer }/edit"><%= messages.getMessage("edit_manufacturer") %></a>
				<input type="submit" class="btn btn-danger" value="<%= messages.getMessage("delete_manufacturer") %>">
			</form>
		</div>
		<div class="well">
			<h1><%= messages.getMessage("data") %></h1>
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
<%@ include file="footer.jsp"%>