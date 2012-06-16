<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<div class="container">
	<!-- Begin Navigation -->
	<ul class="breadcrumb">
		<li class="active"><%=messages.getMessage("recordings")%></li>
	</ul>
	<!-- End Navigation -->
	<div class="pagination pagination-centered">
		<ul class="thumbnails">
			<li class="offset5">
				<div class="thumbnail">
									
					<div class="well">		 
						<span class="label"><%=messages.getMessage("day") %></span>
						<input type="text" id="date" name="date" data-date-format="dd-mm-yyyy" class="input-medium" /><br />
						<span class="label"><%=messages.getMessage("hour") %></span>
						<input type="text" id="hour" name="hour" class="input-mini" value="00"/>
						<span class="label"><%=messages.getMessage("min") %></span>
						<input type="text" id="min" name="min" class="input-mini" value="00"/>
					</div>
					
					<select id="idCamera">
						<c:forEach var="camera" items="${it.cameras}">
							<option value="${camera.idCamera }">${camera.name }</option>
						</c:forEach>
					</select>
					
					<img id="record" src="<%=baseUrl%>/img/no_image.jpg" class="thumb">
										
					<div class="pagination pagination-centered">
					<ul>
						<li><a id="bt_play"><img src="<%=baseUrl%>/img/icons/play.png" /></a></li>
						<li><a id="bt_stop"><img src="<%=baseUrl%>/img/icons/stop.png" /></a></li>
						</ul>
					</div>		
				</div>
			</li>
		</ul>
	</div>
</div>
<script>
	$(function() {
		var dp = $("#date").datepicker();
		$("#bt_play").click(function(){
			var idCamera = $("#idCamera").val();
			var day = $("#date").val();
			var hour = $("#hour").val();
			var min = $("#min").val();
			var url = "<%=baseUrl%>/api/cameras/"+idCamera+"/recordedstream?day="+day+"&hour="+hour+"&min="+min;
			//TODO: Validate date
			
			$("#record").attr("src",url);
		});
	});
</script>
<%@ include file="footer.jsp"%>