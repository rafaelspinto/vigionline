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
			<div id="context-menu"></div>
			<label for="name"><%= messages.getMessage("name") %></label><input name="name" value="${it.action.name }" readonly="readonly"/>
			<label for="action1"><%= messages.getMessage("action1") %></label><input name="action1" value="${it.action.action1 }" readonly="readonly" />
			<label for="action2"><%= messages.getMessage("action2") %></label><input name="action2" value="${it.action.action2 }" readonly="readonly" />
			<label for="idManufacturer"><%= messages.getMessage("manufacturer") %></label>
			<a href="<%=baseUrl %>/manufacturers/${it.manufacturer.idManufacturer }">${it.manufacturer.name }</a>			
			<label for="idModel"><%= messages.getMessage("model") %></label>
			<a href="<%=baseUrl %>/models/${it.model.idModel }">${it.model.name }</a>		
		</div>
	</div>
	<script type="text/javascript">
		$(function()
		{
			AppendToMenu("context-menu", "<%= baseUrl %>/actions/${it.action.idAction }/edit", "<%= messages.getMessage("edit_action") %>");
			makeDeleteModelForm
			(
					"context-menu", 
					"<%= baseUrl %>/actions/${it.action.idAction }/delete", 
					"idAction", 
					"${it.action.idAction }", 
					"<%= messages.getMessage("delete_action") %>"
			);
		});
	</script>
<%@ include file="footer.jsp"%>