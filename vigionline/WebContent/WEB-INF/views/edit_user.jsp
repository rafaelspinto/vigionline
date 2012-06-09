<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<div class="container">
	<!-- Begin Navigation -->
	<ul class="breadcrumb">
		<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
		<li><a href="<%=baseUrl %>/users"><%=messages.getMessage("users") %></a>
			<span class="divider">/</span></li>
		<li><a href="<%=baseUrl %>/users/${it.user.idUser}">${it.user.name}</a>
			<span class="divider">/</span></li>
		<li class="active"><%=messages.getMessage("edit_user") %></li>
	</ul>
	<!-- End Navigation -->
	<form class="well"
		action="<%= baseUrl %>/users/${it.user.idUser }/edit" method="POST">
		<div class="well">
			<input type="hidden" name="idUser" value="${it.user.idUser }" /> <label
				for="name"><%= messages.getMessage("name") %></label> <input
				name="name" value="${it.user.name }" /> <label for="username"><%= messages.getMessage("username") %></label>
			<input name="username" value="${it.user.username }" /> <label
				for="password"><%= messages.getMessage("password") %></label> <input
				type="password" name="password" value="${it.user.password }" />
		</div>
		<!-- ROLES -->
		<div class="well">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th><%=messages.getMessage("role") %></th>
						<th><%=messages.getMessage("active") %></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="role" items="${it.allRoles}">
						<tr>
							<td>${role.idRole }</td>
							<td><a href="<%=baseUrl %>/roles/${role.idRole }">${role.name}</a></td>
							<td><input name="roles" value="${role.name }"
								type="checkbox"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- DIVISIONS -->
		<div class="well">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th><%=messages.getMessage("division") %></th>
						<th><%=messages.getMessage("active") %></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="division" items="${it.allDivisions}">
						<tr>
							<td>${division.idDivision }</td>
							<td><a href="<%=baseUrl %>/divisions/${division.idDivision }">${division.name}</a></td>
							<td><input name="divisions" value="${division.idDivision }"
								type="checkbox"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<input class="btn" type="submit"
			value="<%= messages.getMessage("submit") %>" />
	</form>
</div>
<%@ include file="footer.jsp"%>