<%@page import="vigionline.vwc.Message"%>
<%@ include file="header.jsp"%>
	<div class="container">
		<div class="hero-unit alert alert-error">
			<a class="close" data-dismiss="alert">×</a>
			<h3><%=messages.getMessage("not_found") %></h3>		
		</div>
	</div>
<%@ include file="footer.jsp"%>