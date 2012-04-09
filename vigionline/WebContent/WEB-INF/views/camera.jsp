<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp" %>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl %>/cameras"><%=messages.getMessage("cameras") %></a> <span class="divider">/</span></li>
			<li class="active">${it.camera.name }</li>
		</ul>
		<!-- End Navigation -->
		<div class="well">
			<form class="close" action="<%= baseUrl %>/cameras/${it.camera.idCamera }/delete" method="POST">
				<input type="hidden" name="idCamera" value="${it.camera.idCamera }" />
				<input type="submit" class="btn btn-danger" value="<%= messages.getMessage("delete_camera") %>">
			</form>
			<label for="name"><%= messages.getMessage("name") %></label><input name="name" value="${it.camera.name }" readonly="readonly"/>
			<label for="url"><%= messages.getMessage("url") %></label><input name="url" value="${it.camera.url }" readonly="readonly" />
			<label for="port"><%= messages.getMessage("port") %></label><input name="port" value="${it.camera.port }" readonly="readonly" />
			<label for="username"><%= messages.getMessage("username") %></label><input name="username" value="${it.camera.username }" readonly="readonly" />
			<label for="password"><%= messages.getMessage("password") %></label><input type="password" value="${it.camera.password }" readonly="readonly" />
			<label for="idLocation"><%= messages.getMessage("location") %></label>
			<select id="location" name="idLocation" disabled="disabled">
				<option value="${it.location.idLocation }">${it.location.name }</option>										
			</select>
			<label for="idManufacturer"><%= messages.getMessage("manufacturer") %></label>
			<select id="manufacturer" name="idManufacturer" disabled="disabled">
				<option value="${it.manufacturer.idManufacturer }">${it.manufacturer.name }</option>										
			</select>			
			<label for="idModel"><%= messages.getMessage("model") %></label>
			<select id="model" name="idModel" disabled="disabled">
				<option value="${it.model.idModel }">${it.model.name }</option>																	
			</select>			
		</div>
	</div>
	<script type="text/javascript">
		$(function()
		{
			AppendToMenu("menu-cameras", "<%= baseUrl %>/cameras/${it.camera.idCamera }/edit", "<%= messages.getMessage("edit_camera") %>");
		});
	</script>
<%@ include file="footer.jsp"%>