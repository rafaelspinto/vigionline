<%@ include file="header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<h2><%= messages.getMessage("locations") %></h2>
	<div class="pagination">
	<ul>
		<c:forEach var="item" items="${it}">
			<li>
				<a href="${pageContext.request.contextPath}/api/locations/${item.idLocation }">	${item.name}</a>
			</li>
		</c:forEach>
	</ul>
	</div>
<%@ include file="footer.jsp"%>