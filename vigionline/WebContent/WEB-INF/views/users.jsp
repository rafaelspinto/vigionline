<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<div class="container">
	<!-- Begin Navigation -->
	<ul class="breadcrumb">
		<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
		<li class="active"><%=messages.getMessage("users") %></li>
	</ul>
	<!-- End Navigation -->
	<table class="table table-bordered">
		<tbody>
			<c:forEach var="item" items="${it}">
				<tr><td>${item.idUser}</td>
					<td><a href="<%=baseUrl %>/users/${item.idUser }">
							${item.name}</a></td>
					<td>${item.username}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<%@ include file="footer.jsp"%>