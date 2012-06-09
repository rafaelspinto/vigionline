<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<div class="container">
	<!-- Begin Navigation -->
	<ul class="breadcrumb">
		<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
		<li class="active"><%=messages.getMessage("divisions") %></li>
	</ul>
	<!-- End Navigation -->
	<table class="table table-bordered">
		<tbody>
			<c:forEach var="item" items="${it}">
				<tr><td>${item.idDivision}</td>
					<td><a href="<%=baseUrl %>/divisions/${item.idDivision }">
							${item.name}</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<%@ include file="footer.jsp"%>