<%@page import="vigionline.vwc.Message"%>
<%@ include file="header.jsp"%>
	<div class="container">
		<div class="well">
			<div class="alert alert-error"><% Message msg = (Message) request.getAttribute("it");%><%=messages.getMessage(msg.getMessage()) %></div>		
		</div>
	</div>
<%@ include file="footer.jsp"%>