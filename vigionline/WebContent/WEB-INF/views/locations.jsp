<%@ include file="header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul class="nav nav-tabs nav-stacked">
	<li class="nav-header">Operations</li>
	<li><a href="${pageContext.request.contextPath}/locations/all">Get All Locations</a></li>
	<li><a href="${pageContext.request.contextPath}/locations/create">Create Location</a></li>
	
</ul>
${it }
<c:forEach var="item" items="${it}">
	${item.name}<br />
</c:forEach>
<%@ include file="footer.jsp"%>