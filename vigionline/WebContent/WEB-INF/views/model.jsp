<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
			<li><a href="<%=baseUrl %>/models"><%=messages.getMessage("models") %></a> <span class="divider">/</span></li>
			<li class="active">${it.model.name }</li>
		</ul>
		<!-- End Navigation -->
		<div class="well">
			<form class="form-inline" action="<%= baseUrl %>/models/${it.model.idModel }/delete" method="POST">
				<input type="hidden" name="idModel" value="${it.model.idModel }" />
				<a class="btn btn-primary" href="<%= baseUrl %>/models/${it.model.idModel }/edit"><%= messages.getMessage("edit_model") %></a>
				<input type="submit" class="btn btn-danger" value="<%= messages.getMessage("delete_model") %>">
			</form>
		</div>
		<div class="well">
			<label for="name"><%= messages.getMessage("name") %></label><input name="name" value="${it.model.name }" readonly="readonly" />
			<label for="videoUrl"><%= messages.getMessage("video_url") %></label><input name="videoUrl" value="${it.model.videoUrl }" readonly="readonly" />
			<label for="audioUrl"><%= messages.getMessage("audio_url") %></label><input name="audioUrl" value="${it.model.audioUrl }" readonly="readonly" />
			<label for="idManufacturer"><%= messages.getMessage("manufacturer") %></label>
			<select name="idManufacturer" disabled="disabled">
				<option value="${it.manufacturer.idManufacturer }">${it.manufacturer.name }</option>
			</select>			
			<label for="isMjpeg"><%= messages.getMessage("is_mjpeg") %></label><input type="checkbox" name="isMjpeg" value="${it.model.MJPEG }" readonly="readonly" />
			<label for="width"><%= messages.getMessage("width") %></label><input name="width" value="${it.model.width }" readonly="readonly" />
			<label for="width"><%= messages.getMessage("height") %></label><input name="height" value="${it.model.height }" readonly="readonly" />
			<label for="beginLinesToDiscard"><%= messages.getMessage("begin_lines_to_discard") %></label><input name="beginLinesToDiscard" value="${it.model.beginLinesToDiscard }" readonly="readonly" />
			<label for="endLinesToDiscard"><%= messages.getMessage("end_lines_to_discard") %></label><input name="endLinesToDiscard" value="${it.model.endLinesToDiscard }" readonly="readonly" />
		</div>
	</div>
<%@ include file="footer.jsp"%>