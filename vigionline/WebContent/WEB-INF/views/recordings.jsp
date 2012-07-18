<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<div class="container">
	<!-- Begin Navigation -->
	<ul class="breadcrumb">
		<li class="active"><%=messages.getMessage("recordings")%></li>
	</ul>
	<!-- End Navigation -->
	<div class="pagination pagination-centered">
	<input id="w640" type="button" value="640x480"> 
	<input id="w1024" type="button" value="1024x768">
	<select	id="idCamera">
			<c:forEach var="camera" items="${it.cameras}">
				<c:forEach var="location" items="${it.locations}">
					<c:if test="${location.idLocation == camera.idLocation }">
							<option value="${camera.idCamera }">${location.name} - ${camera.name }</option>
					</c:if>
				</c:forEach>
			</c:forEach>
	</select>
	</div>
	<div class="pagination pagination-centered">
		<ul class="thumbnails">
			<li class="span4 offset5">
				<div class="thumbnail">
									
					<div class="well">		 
						<span class="label"><%=messages.getMessage("day") %></span>
						<input type="text" id="date" name="date" data-date-format="dd-mm-yyyy" class="input-medium" /><br />
						<span class="label"><%=messages.getMessage("hour") %></span>
						<input type="text" id="hour" name="hour" class="input-mini" value="00"/>
						<span class="label"><%=messages.getMessage("min") %></span>
						<input type="text" id="min" name="min" class="input-mini" value="00"/>
					</div>
					
					
					
					<img id="record" src="<%=baseUrl%>/img/no_image.jpg" class="thumb">
										
					<div class="pagination pagination-centered">
					
						<a id="bt_play"><i class="icon-play"></i></a>
						<a id="bt_stop"><i class="icon-stop"></i></a> &nbsp &nbsp
						
							<span class="label">max. fps</span>&nbsp &nbsp<select id="fps" class="input-mini">
								<option value="30">30</option>
								<option value="15">15</option>
								<option value="5">5</option>
								<option value="1">1</option>
							</select>					
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
			if( $("#date").val().length === 0 ) 
			{
				    $("#date").parents('div').addClass('control-group error');
			}
			else
			{
				var idCamera = $("#idCamera").val();
				var day = $("#date").val();
				var hour = $("#hour").val();
				var min = $("#min").val();
				var fps = $("#fps").val();
				$(this).attr("src","#");
				$("#record").attr("src",'<%=baseUrl%>/img/loading.gif');
				setTimeout(function(){
					var url = "<%=baseUrl%>/api/cameras/"+idCamera+"/recordedstream?day="+day+"&hour="+hour+"&min="+min+"&fps="+fps;
					$("#record").attr("src",url);
					$("#date").parents('div').removeClass('control-group error');
				}, 1000);
			}
		});
		
		$("#bt_stop").click(function(){
			$("#record").attr("src","<%=baseUrl%>/img/no_image.jpg");
		});
		$('.thumb').error(function(){
		     $(this).attr("src","<%=baseUrl%>/img/no_connection.gif");
		});
						
	    $("#w640").click(function() {
	            setSize('640','480','4');
	    });
	    $("#w1024").click(function() {
            setSize('1024','768','6');
    	});
	    
	    function setSize(w, h, s)
	    {
	            $(".thumb").attr('width',w);
	            $(".thumb").attr('height',h);
	            $('.thumbnails li').attr('class','span'+s);
	            //$(".thumbnails").css('display', 'none').css('display', 'block');
	    }
	    $(document).ready(function(){
            setSize(640,480,'4');
    	});
	});
</script>
<%@ include file="footer.jsp"%>