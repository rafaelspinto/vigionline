<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp" %>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl %>/actions"><%=messages.getMessage("actions") %></a> <span class="divider">/</span></li>
			<li class="active">${it.action.name }</li>
		</ul>
		<!-- End Navigation -->
		<div class="well">
			<form class="close" action="<%= baseUrl %>/actions/${it.action.idAction }/delete" method="POST">
				<input type="hidden" name="idAction" value="${it.action.idAction }" />
				<input type="submit" class="btn btn-danger" value="<%= messages.getMessage("delete_action") %>">
			</form>
			<label for="name"><%= messages.getMessage("name") %></label><input name="name" value="${it.action.name }" readonly="readonly"/>
			<label for="action1"><%= messages.getMessage("action1") %></label><input name="action1" value="${it.action.action1 }" readonly="readonly" />
			<label for="action2"><%= messages.getMessage("action2") %></label><input name="action2" value="${it.action.action2 }" readonly="readonly" />
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
			AppendToMenu("menu-actions", "<%= baseUrl %>/actions/${it.action.idAction }/edit", "<%= messages.getMessage("edit_action") %>");
		});
	</script>
<%@ include file="footer.jsp"%>