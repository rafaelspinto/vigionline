<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl %>/locations"><%=messages.getMessage("locations") %></a> <span class="divider">/</span></li>
			<li class="active"><%=messages.getMessage("create_location") %></li>
		</ul>
		<!-- End Navigation -->
		<div class="well">
			
		</div>
	</div>
<%@ include file="footer.jsp"%>