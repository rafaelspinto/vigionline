<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp" %>
<div class="container">
	<!-- Begin Navigation -->
	<ul class="breadcrumb">
		<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
		<li><a href="<%=baseUrl %>/cameras"><%=messages.getMessage("cameras") %></a>
			<span class="divider">/</span></li>
		<li><a href="<%=baseUrl %>/cameras/${it.camera.idCamera}">${it.camera.name
				}</a> <span class="divider">/</span></li>
		<li class="active"><%=messages.getMessage("edit_camera") %></li>
	</ul>
	<!-- End Navigation -->
	<form class="well"
		action="<%= baseUrl %>/cameras/${it.camera.idCamera}/edit"
		method="POST">
		<input type="hidden" name="idCamera" value="${it.camera.idCamera }" />
		<label for="name"><%= messages.getMessage("name") %></label><input
			name="name" value="${it.camera.name }" /> <label for="url"><%= messages.getMessage("url") %></label><input
			name="url" value="${it.camera.url }" /> <label for="port"><%= messages.getMessage("port") %></label><input
			name="port" value="${it.camera.port }" /> <label for="username"><%= messages.getMessage("username") %></label><input
			name="username" value="${it.camera.username }" /> <label
			for="password"><%= messages.getMessage("password") %></label><input
			type="password" name="password" value="${it.camera.password }" /> <label
			for="idLocation"><%= messages.getMessage("location") %></label> <select
			id="location" name="idLocation">
			<option value="${it.location.idLocation }">${it.location.name
				}</option>
			<c:forEach var="location" items="${it.locations}">
				<c:if test="${location.idLocation != it.location.idLocation }">
					<option value="${location.idLocation }">${location.name }</option>
				</c:if>
			</c:forEach>
		</select> <label for="idManufacturer"><%= messages.getMessage("manufacturer") %></label>
		<select id="manufacturer" name="idManufacturer">
			<c:forEach var="manufacturer" items="${it.manufacturers}">
				<option value="${manufacturer.idManufacturer }">${manufacturer.name
					}</option>
			</c:forEach>
		</select> <label for="idModel"><%= messages.getMessage("model") %></label> <select
			id="model" name="idModel">
			<c:forEach var="model" items="${it.models}">
				<option value="${model.idModel }">${model.name }</option>
			</c:forEach>
		</select> <input class="btn" type="submit"
			value="<%= messages.getMessage("submit") %>" />
	</form>
</div>
<script type="text/javascript">
	$(function() {
		$("#manufacturer").change(
				function() {
					var idManufacturer = $(this).attr("value");
					$.getJSON("<%=baseUrl %>/api/manufacturers/"+idManufacturer+"/models", function(models)
				{
					$("#model").empty();
					$.each(models, function(i, model)
					{
						$("#model").append(new Option(model.name, model.idModel));
					});
				});	
			});			
		});
	</script>
<%@ include file="footer.jsp"%>
