<%@ include file="header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul class="nav nav-tabs nav-stacked">
	<li class="nav-header">Operations</li>
	<li><a href="${pageContext.request.contextPath}/locations/all">Get All Locations</a></li>
	<li><a href="${pageContext.request.contextPath}/locations/create">Create Location</a></li>
	
</ul>
<c:forEach var="item" items="${it}">
	<a class="btn btn-primary btn-large" href="${pageContext.request.contextPath}/api/locations/${item.idLocation }">${item.name}</a>
</c:forEach>
<%@ include file="footer.jsp"%>