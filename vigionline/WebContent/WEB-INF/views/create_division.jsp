<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl%>">Home</a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl%>/divisions"><%=messages.getMessage("divisions")%></a> <span class="divider">/</span></li>
			<li class="active"><%=messages.getMessage("create_division")%></li>
		</ul>
		<!-- End Navigation -->
	<form class="well" action="<%=baseUrl%>/divisions/create"
		method="POST">
		<label for="name"><%=messages.getMessage("name")%></label> <input
			name="name" /> <input class="btn" type="submit"
			value="<%=messages.getMessage("submit")%>" />
	</form>
</div>
<%@ include file="footer.jsp"%>