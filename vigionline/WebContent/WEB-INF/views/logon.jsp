<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li class="active">Home</li>
		</ul>
		<!-- End Navigation -->
		<div class="hero-unit">
			<h1>Vigionline</h1>
			
			<form class="well" action="<%= baseUrl %>/logon" method="POST">
				<label for="username"><%= messages.getMessage("username") %></label>
				<input name="username" />
				<label for="password"><%= messages.getMessage("password") %></label>
				<input id="password" name="password" type="password"/>
				<input class="btn" type="submit" value="<%= messages.getMessage("login") %>" />
			</form>	
		
		</div>
	</div>
<%@ include file="footer.jsp"%>