<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl %>/roles"><%=messages.getMessage("roles") %></a> <span class="divider">/</span></li>
			<li class="active">${it.name }</li>
		</ul>
		<!-- End Navigation -->
		<div class="well">
			<label for="name"><%= messages.getMessage("name") %></label>
			<input name="name" value="${it.name }" readonly="readonly" />
		</div>
	</div>
<%@ include file="footer.jsp"%>