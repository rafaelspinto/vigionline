<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<div class="container">
	<!-- Begin Navigation -->
	<ul class="breadcrumb">
		<li><a href="<%=baseUrl%>">Home</a> <span class="divider">/</span></li>
		<li class="active"><%=messages.getMessage("locations")%></li>
	</ul>
	<!-- End Navigation -->
	<div id="context-menu"></div><br />
	<table class="table table-bordered">
		<tbody>
			<c:forEach var="item" items="${it}">
				<tr>
					<td>${item.idLocation }</td>
					<td><a href="<%=baseUrl %>/locations/${item.idLocation }">
							${item.name}</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script type="text/javascript">
		$(function()
		{
			AppendToMenu("context-menu", "<%= baseUrl %>/locations/create", "<%= messages.getMessage("create_location") %>");
		});
</script>
<%@ include file="footer.jsp"%>