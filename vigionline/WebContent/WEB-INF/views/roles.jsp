<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
			<li class="active"><%=messages.getMessage("roles") %></li>
		</ul>
		<!-- End Navigation -->
		<div class="well">
			<a class="btn btn-primary" href="<%= baseUrl %>/roles/create"><%= messages.getMessage("create_role") %></a>
		</div>
		<div class="well">
			<div class="pagination pagination-centered">
				<ul>
					<c:forEach var="item" items="${it}">
						<li>
							<a href="<%=baseUrl %>/roles/${item.idRole }">	${item.name}</a>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
<%@ include file="footer.jsp"%>