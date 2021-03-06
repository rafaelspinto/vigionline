<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl%>">Home</a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl%>/users"><%=messages.getMessage("users")%></a> <span class="divider">/</span></li>
			<li class="active">${it.user.name }</li>
		</ul>
		<!-- End Navigation -->
		<div class="well">
			<div id="context-menu"></div>
			<label for="name"><%=messages.getMessage("name")%></label>
			<input name="name" value="${it.user.name }" readonly="readonly" />
			<label for="username"><%=messages.getMessage("username")%></label>
			<input name="username" value="${it.user.username }" readonly="readonly"/>
			<label for="password"><%=messages.getMessage("password")%></label>
			<input type="password" name="password" value="${it.user.password }" readonly="readonly"/>	
		</div>
		<!-- ROLES -->
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>ID</th>
				<th><%=messages.getMessage("role")%></th>
				<th><%=messages.getMessage("active")%></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="role" items="${it.roles}">
				<tr>
					<td>${role.idRole }</td>
					<td><a href="<%=baseUrl %>/roles/${role.idRole }">${role.name}</a></td>
					<td><input type="checkbox" disabled="disabled"
						checked="checked"></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


	<!-- DIVISIONS -->
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>ID</th>
				<th><%=messages.getMessage("division")%></th>
				<th><%=messages.getMessage("active")%></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="division" items="${it.divisions}">
				<tr>
					<td>${division.idDivision }</td>
					<td><a href="<%=baseUrl %>/divisions/${division.idDivision }">${division.name}</a></td>
					<td><input type="checkbox" disabled="disabled"
						checked="checked"></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<script type="text/javascript">
		$(function() {
			AppendToMenu("context-menu", "<%=baseUrl%>/users/${it.user.idUser }/edit", "<%=messages.getMessage("edit_user")%>");	
			makeDeleteModelForm
			(
					"context-menu", 
					"<%= baseUrl %>/users/${it.user.idUser }/delete", 
					"idUser", 
					"${it.user.idUser }", 
					"<%= messages.getMessage("delete_user") %>");
		});
		</script>
	</div>
<%@ include file="footer.jsp"%>