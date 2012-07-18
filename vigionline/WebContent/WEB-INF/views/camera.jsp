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
			<div id="context-menu"></div>
			<label for="name"><%= messages.getMessage("name") %></label><input name="name" value="${it.camera.name }" readonly="readonly"/>
			<label for="url"><%= messages.getMessage("url") %></label><input name="url" value="${it.camera.url }" readonly="readonly" />
			<label for="port"><%= messages.getMessage("port") %></label><input name="port" value="${it.camera.port }" readonly="readonly" />
			<label for="username"><%= messages.getMessage("username") %></label><input name="username" value="${it.camera.username }" readonly="readonly" />
			<label for="password"><%= messages.getMessage("password") %></label><input id="password" type="password" value="${it.camera.password }" readonly="readonly" />
			<input id="show_password" type="checkbox" /><%= messages.getMessage("show_password") %>
			<label for="idLocation"><%= messages.getMessage("location") %></label>
			<a href="<%=baseUrl %>/locations/${it.location.idLocation }">${it.location.name }</a>
			<label for="idManufacturer"><%= messages.getMessage("manufacturer") %></label>
			<a href="<%=baseUrl %>/manufacturers/${it.manufacturer.idManufacturer }">${it.manufacturer.name }</a>			
			<label for="idModel"><%= messages.getMessage("model") %></label>
			<a href="<%=baseUrl %>/models/${it.model.idModel }">${it.model.name }</a>			
		</div>
	</div>
	<script type="text/javascript">
		$(function()
		{
			AppendToMenu("context-menu", "<%= baseUrl %>/cameras/${it.camera.idCamera }/edit", "<%= messages.getMessage("edit_camera") %>");
			makeDeleteModelForm
			(
					"context-menu", 
					"<%= baseUrl %>/cameras/${it.camera.idCamera }/delete", 
					"idCamera", 
					"${it.camera.idCamera }", 
					"<%= messages.getMessage("delete_camera") %>"
			);
			var isVisible = false;
			$("#show_password").click(function(){
				if( isVisible == false)
				{
					$('#password').replaceWith($('#password').clone().attr('type', 'text'));
					isVisible = true;
				}
				else
				{
					$('#password').replaceWith($('#password').clone().attr('type', 'password'));
					isVisible = false;
				}
			});
		});
	</script>
<%@ include file="footer.jsp"%>