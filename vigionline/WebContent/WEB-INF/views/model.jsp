<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl%>">Home</a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl%>/models"><%=messages.getMessage("models")%></a> <span class="divider">/</span></li>
			<li class="active">${it.model.name }</li>
		</ul>
		<!-- End Navigation -->
		<div class="well">
			<div id="context-menu"></div>
			<label for="name"><%=messages.getMessage("name")%></label><input name="name" value="${it.model.name }" readonly="readonly" />
			<label for="videoUrl"><%=messages.getMessage("video_url")%></label><input name="videoUrl" value="${it.model.videoUrl }" readonly="readonly" />
			<label for="audioUrl"><%=messages.getMessage("audio_url")%></label><input name="audioUrl" value="${it.model.audioUrl }" readonly="readonly" />
			<label for="idManufacturer"><%=messages.getMessage("manufacturer")%></label>
			<a href="<%=baseUrl %>/manufacturers/${it.manufacturer.idManufacturer }">${it.manufacturer.name }</a>
			<label for="width"><%=messages.getMessage("width")%></label><input name="width" value="${it.model.width }" readonly="readonly" />
			<label for="width"><%=messages.getMessage("height")%></label><input name="height" value="${it.model.height }" readonly="readonly" />
		</div>

		<table class="table table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th><%=messages.getMessage("action")%></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="a" items="${it.actions}">
					<tr>
						<td>${a.idAction }</td>
						<td><a href="<%=baseUrl %>/actions/${a.idAction }">${a.name}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

</div>
	<script type="text/javascript">
		$(function()
		{
			AppendToMenu("context-menu", "<%=baseUrl%>/models/${it.model.idModel }/edit", "<%=messages.getMessage("edit_model")%>");
			makeDeleteModelForm
			(
					"context-menu", 
					"<%=baseUrl%>/models/${it.model.idModel }/delete", 
					"idModel", 
					"${it.model.idModel }", 
					"<%=messages.getMessage("delete_model")%>"
			);
		});
	</script>
<%@ include file="footer.jsp"%>