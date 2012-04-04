<%@ include file="header.jsp"%>
<h2><%= messages.getMessage("location") %></h2>
<label for="name"><%= messages.getMessage("name") %></label>
<input name="name" value="${it.name }" readonly="readonly" />
<a href="<%= baseUrl %>/locations/${it.idLocation }/edit"><img src="${pageContext.request.contextPath}/images/edit.png"></a>
<%@ include file="footer.jsp"%>