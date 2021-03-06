<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<div class="container">
	<!-- Begin Navigation -->
	<ul class="breadcrumb">
		<li><a href="<%=baseUrl%>">Home</a> <span class="divider">/</span></li>
		<li class="active"><%=messages.getMessage("actions")%></li>
	</ul>
	<!-- End Navigation -->
	<div id="context-menu"></div><br />
	<table class="table table-bordered">
		<tbody>
			<c:forEach var="item" items="${it}">
				<tr>
					<td>${item.idAction }</td>
					<td><a href="<%=baseUrl %>/actions/${item.idAction }">
							${item.name}</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script type="text/javascript">
		$(function()
		{
			AppendToMenu("context-menu", "<%= baseUrl %>/actions/create", "<%= messages.getMessage("create_action") %>");
		});
</script>
<%@ include file="footer.jsp"%>