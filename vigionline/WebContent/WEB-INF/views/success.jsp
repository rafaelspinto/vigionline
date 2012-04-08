<%@page import="vigionline.vwc.Message"%>
<%@ include file="header.jsp"%>
	<div class="container">
		<div class="alert alert-success">
			<a class="close" data-dismiss="alert">×</a>
			<% Message msg = (Message) request.getAttribute("it");%><%=messages.getMessage(msg.getMessage()) %>		
		</div>
	</div>
<%@ include file="footer.jsp"%>