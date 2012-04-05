<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl %>/locations"><%=messages.getMessage("locations") %></a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl %>/locations/${it.idLocation}">${it.name }</a> <span class="divider">/</span></li>
			<li class="active"><%=messages.getMessage("edit_location") %></li>
		</ul>
		<!-- End Navigation -->
		<div class="well">
			<form class="form-inline" action="<%= baseUrl %>/locations/${it.idLocation }/edit" method="POST">
				<label for="name"><%= messages.getMessage("name") %></label>
				<input type="hidden" name="idLocation" value="${it.idLocation }" />
				<input name="name" value="${it.name }"/>
				<input class="btn" type="submit" value="<%= messages.getMessage("submit") %>" />
			</form>
		</div>
	</div>
<%@ include file="footer.jsp"%>