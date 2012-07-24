<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<%@page import="vigionline.common.model.Action"%>
<div class="container">
	<input id="w160" type="button" value="160x120">
	<input id="w320" type="button" value="320x240"> 
	<input id="w640" type="button" value="640x480"> 
	<input id="w1024" type="button" value="1024x768">
	<span class="label">max. fps</span>&nbsp &nbsp<select
		id="fps" class="input-mini">
		<option value="30">30</option>
		<option value="15">15</option>
		<option value="5">5</option>
		<option value="1">1</option>
	</select> <input type="button" class="bt_on .btn btn-success" value="on" /> 
	<input type="button" class="bt_off .btn btn-danger .disabled" value="off" style="display: none" />
	<ul class="thumbnails">
		<c:forEach var="camera" items="${it.cameras}">

			<!-- BEGIN CAMERAS -->
			<li class="span3 offset5">
				<div class="thumbnail">
					
					<c:forEach var="location" items="${it.locations}">
						<% boolean hasShown = false; %>
						<c:if test="${location.idLocation == camera.idLocation }">
						
							<a href="<%=baseUrl %>/locations/${location.idLocation }"><span class="label label-info">${location.name}<img class="record_status" url="<%=baseUrl %>/api/actions/recordstatus?idCamera=${camera.idCamera}"></span></a>
						
							<img source="<%=baseUrl %>/api/cameras/${camera.idCamera}/stream" src="<%=baseUrl%>/img/no_image.jpg" class="thumb">
							
							<button id="bt_menu_${camera.idCamera}" class="btn btn-primary bt_action btn-mini"	menu="menu_${camera.idCamera}"><%= messages.getMessage("actions") %><span class="caret"></span></button>
							<a href="<%=baseUrl %>/cameras/${camera.idCamera}"><span class="label label-inverse">${camera.name }</span></a>
							
							<!-- BEGIN ACTIONS -->
							<% if(isAdmin) { %>
							<div class="wells">
								<ul id="menu_${camera.idCamera}" class="action_menu" style="display: none;">
									<a class="bt_act" action="<%=baseUrl %>/api/actions/record?idCamera=${camera.idCamera}">
										<img src="<%=baseUrl%>/img/icons/record.png" />
									</a>
									<a class="bt_act" action="<%=baseUrl %>/api/actions/stoprecord?idCamera=${camera.idCamera}">
										<img src="<%=baseUrl%>/img/icons/stop_record.png" />
									</a>
								
									<c:forEach var="action" items="${ it.actions}">
										<c:if test="${action.idModel == camera.idModel}">
											<a class="bt_act"
												action="<%=baseUrl %>/api/actions/${action.idAction }/execute?idCamera=${camera.idCamera}">
												<img src="<%=baseUrl%>/img/icons/${action.name}.png" />
											</a>
										</c:if>
									</c:forEach>

								</ul>
							</div>
							<% } %>
							<!-- END ACTIONS -->

						</c:if>
					</c:forEach>
				</div>
			</li>

			<!-- END CAMERAS -->
		</c:forEach>
	</ul>
</div>
<script type="text/javascript">
		$('.thumb').error(function(){
		     $(this).attr("src","<%=baseUrl%>/img/no_connection.gif");
		});
		
		$("#w160").click(function() {
            setSize('160','120','2');
	    });
	    $("#w320").click(function() {
	            setSize('320','240','3');
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
	            $(".thumbnails").css('display', 'none').css('display', 'block');
	    }
	    $(document).ready(function(){
            setSize(320,240,'3');
    	});
	   $(".bt_on").click(function(e){
		   $(this).attr("src","#");
		   $(".thumb").attr("src",'<%=baseUrl%>/img/loading.gif');
		   $(".bt_off").show();
		   $(this).hide();
		   e.preventDefault();
		   setTimeout(function(){
			   $(".thumb").each(function(){
					var on_src=$(this).attr("source");
					$(this).attr("src",on_src+"?_=<%= System.currentTimeMillis() %>");
				});   
		   },1000);
		});
	   $(".bt_off").click(function(e){
		   $(".bt_on").show();
		   $(this).hide();
		   e.preventDefault();
		   setTimeout(function(){
			 	$(".thumb").each(function(){
			 		$(this).attr("src","#");
			 		$(this).hide();
			 		$(this).attr("src",'<%=baseUrl%>/img/no_image.jpg');
					$(this).show();
				});
		   },1000);
		});
	   $(".bt_act").click(function () {
		   var url = $(this).attr("action");
		   $.ajax({
			   url : url,
			   cache : false
		   });
		   
		   return false;
		  });
	   
	   $(".bt_action").click(function(){
		   $("#"+$(this).attr("menu")).slideToggle();
	   });
	   
	   function pollRecordStatus()
	   {   
			if( stillPolling == 0 )
			{
			 	$(".record_status").each(function() {
		 			var url = $(this).attr("url");
		 			var obj = this;
		 			stillPolling++;
		 			$.ajax({
		  			   url: url,
		  			   cache : false
		  			 }).done(function(data) { 
		  				 if(data == 'true')
		 					   $(obj).attr("src", "<%=baseUrl%>/img/icons/recording.png");
		  					else
		 					   $(obj).attr("src", "<%=baseUrl%>/img/icons/no_recording.png");
		  				stillPolling--;
		  			 });
		 		});
			}
			setTimeout(function() {
				pollRecordStatus();
			}, 10000);
		}
	   var stillPolling = 0;
	   pollRecordStatus();
</script>
<%@ include file="footer.jsp"%>