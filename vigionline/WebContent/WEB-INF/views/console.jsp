<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<%@page import="vigionline.common.model.Action"%>
<div class="container">
	${it.username}
	<input id="w160" type="button" value="160x120"> <input
		id="w320" type="button" value="320x240"> <input id="w640"
		type="button" value="640x480"> <input id="w1024" type="button"
		value="1024x768"> <input type="button"
		class="bt_on .btn btn-success" value="on" /> <input type="button"
		class="bt_off .btn btn-danger .disabled" value="off" style="display:none"/>
	<ul class="thumbnails">
		<c:forEach var="camera" items="${it.cameras}">
			<li class="span3 offset5">
				<div class="thumbnail">
					<img source="<%=baseUrl %>/api/cameras/${camera.idCamera}/stream"
						src="<%=baseUrl%>/images/no_image.jpg" class="thumb">
					<c:forEach var="location" items="${it.locations}">
						<c:if test="${location.idLocation == camera.idLocation }">
							<div class="record_status" url="<%=baseUrl %>/api/cameras/${camera.idCamera}/recordstatus"></div>
							<h5>${location.name} : ${camera.name }</h5>
							<!-- ------------------- -->
							<div class="wells">
								<button class="btn btn-primary bt_action btn-mini" menu="menu_${camera.idCamera}"><%= messages.getMessage("actions") %></button>	
								<ul id="menu_${camera.idCamera}" class="action_menu" style="display:none;">
									<hr>
									<c:forEach var="action" items="${ it.actions}">
										<c:if test="${action.idModel == camera.idModel}">
											
												<a class="bt_act"
													action="<%=baseUrl %>/api/actions/${action.idAction }/execute?idCamera=${camera.idCamera}">
													<img src="<%=baseUrl%>/images/icons/${action.name}.png" />
												</a>
										</c:if>
									</c:forEach>
								</ul>
							</div>
							<!-- ------------------- -->
						</c:if>
					</c:forEach>
				</div>
			</li>
		</c:forEach>
	</ul>
</div>
<script type="text/javascript">
		$('.thumb').error(function(){
	        console.log(this.src);
	        this.src = '<%=baseUrl%>/images/no_image.jpg';
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
		   $(".thumb").attr("src",'<%=baseUrl%>/images/loading.gif');
		   $(".bt_off").show();
		   $(this).hide();
		   e.preventDefault();  
			$(".thumb").each(function(){
				var on_src=$(this).attr("source");
				$(this).attr("src",on_src);
			});
		});
	   $(".bt_off").click(function(e){
		   $(".bt_on").show();
		   $(this).hide();
		   e.preventDefault();
		 	$(".thumb").each(function(){
		 		$(this).hide();
		 		$(this).attr("src","#");
				$(this).attr("src",'<%=baseUrl%>/images/no_image.jpg');
				$(this).show();
			});
		});
	   $(".bt_act").click(function () {
		   var url = $(this).attr("action");
		   $.get(url);
		   return false;
		  });
	   
	   $(".bt_action").click(function(){
		   $("#"+$(this).attr("menu")).slideToggle();
	   });
	   
	   function pollRecordStatus(url, container)
	   {
		   $.get(url, function(data) {
			   if(data == 'true')
				   data = '<img width="20px" src="'+'<%=baseUrl%>/images/icons/recording.png'+'" />';
				else
				   data = '<img width="20px" src="'+'<%=baseUrl%>/images/icons/no_recording.png'+'" />';
		        $(container).html(data);
		        setTimeout(function(){ pollRecordStatus(url, container); },10000);
		    });
	   }
	      $(".record_status").each(function(){
				   var url = $(this).attr("url");
				   pollRecordStatus(url,this);
	   		}); 
	</script>
<%@ include file="footer.jsp"%>