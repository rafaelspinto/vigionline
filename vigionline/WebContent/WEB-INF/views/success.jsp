<%@page import="vigionline.vwc.Message"%>
<%@ include file="header.jsp"%>
	<div class="container">
		<div class="hero-unit alert alert-success">
			<h3><% Message msg = (Message) request.getAttribute("it");%><%=messages.getMessage(msg.getMessage()) %></h3>		
		</div>
	</div>
<%@ include file="footer.jsp"%>