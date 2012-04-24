<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl %>/users"><%=messages.getMessage("users") %></a> <span class="divider">/</span></li>
			<li class="active"><%=messages.getMessage("create_user") %></li>
		</ul>
		<!-- End Navigation -->
		<div id="formId" class="control-group">
			<form id="cuser" class="well" action="<%= baseUrl %>/users/create" method="POST">
				<label for="name"><%= messages.getMessage("name") %></label>
				<input name="name" />
				<label for="username"><%= messages.getMessage("username") %></label>
				<input name="username" />
				<label for="password"><%= messages.getMessage("password") %></label>
				<input id="pwd" name="password" type="password"/>
				<label for="confirm_password"><%= messages.getMessage("confirm_password") %></label>
				<input id="cpwd" name="confirm_password" type="password"/>
				<input class="btn" type="submit" value="<%= messages.getMessage("submit") %>" />
			</form>	
		</div>
	</div>
	<script type="text/javascript">
		$(function()
		{
			ValidateForm("cuser","pwd","cpwd");
		});
	</script>
<%@ include file="footer.jsp"%>