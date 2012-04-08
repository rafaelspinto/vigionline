<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp" %>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl %>/actions"><%=messages.getMessage("actions") %></a> <span class="divider">/</span></li>
			<li class="active"><%=messages.getMessage("create_action") %></li>
		</ul>
		<!-- End Navigation -->
		<div class="well">
			<form class="well" action="<%= baseUrl %>/actions/create" method="POST">
				<label for="name"><%= messages.getMessage("name") %></label><input name="name" value="${it.action.name }" required="required"/>
				<label for="action1"><%= messages.getMessage("action1") %></label><input name="action1" value="${it.action.action1 }" required="required"/>
				<label for="action2"><%= messages.getMessage("action2") %></label><input name="action2" value="${it.action.action2 }" />
				<label for="idManufacturer"><%= messages.getMessage("manufacturer") %></label>
				<select id="manufacturer" name="idManufacturer">
					<c:forEach var="manufacturer" items="${it.manufacturers}">
						<option value="${manufacturer.idManufacturer }">${manufacturer.name }</option>							
					</c:forEach>										
				</select>			
				<label for="idModel"><%= messages.getMessage("model") %></label>
				<select id="model" name="idModel">
					<c:forEach var="model" items="${it.models}">
						<option value="${model.idModel }">${model.name }</option>							
					</c:forEach>										
				</select>
				<input class="btn" type="submit" value="<%= messages.getMessage("submit") %>" />
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$("#manufacturer").change(function(){
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