<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl %>/models"><%=messages.getMessage("models") %></a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl %>/models/${it.model.idModel}">${it.model.name }</a> <span class="divider">/</span></li>
			<li class="active"><%=messages.getMessage("edit_model") %></li>
		</ul>
		<!-- End Navigation -->
			<form class="well" action="<%= baseUrl %>/models/${it.model.idModel }/edit" method="POST">
				<input type="hidden" name="idModel" value="${it.model.idModel }" />
				<label for="name"><%= messages.getMessage("name") %></label><input name="name" value="${it.model.name }" />
				<label for="videoUrl"><%= messages.getMessage("video_url") %></label><input name="videoUrl" value="${it.model.videoUrl }" />
				<label for="audioUrl"><%= messages.getMessage("audio_url") %></label><input name="audioUrl" value="${it.model.audioUrl }" />
				<label for="idManufacturer"><%= messages.getMessage("manufacturer") %></label>
				<select name="idManufacturer">
					<option value="${it.manufacturer.idManufacturer }">${it.manufacturer.name }</option>
					<c:forEach var="manufacturer" items="${it.manufacturers}">
						<c:if test="${manufacturer.idManufacturer != it.model.idManufacturer }">
							<option value="${manufacturer.idManufacturer }">${manufacturer.name }</option>
						</c:if>										
					</c:forEach>										
				</select>			
				<label for="width"><%= messages.getMessage("width") %></label><input name="width" value="${it.model.width }" />
				<label for="width"><%= messages.getMessage("height") %></label><input name="height" value="${it.model.height }" />
				<input class="btn" type="submit" value="<%= messages.getMessage("submit") %>" />
			</form>
		</div>
<%@ include file="footer.jsp"%>