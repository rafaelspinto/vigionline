<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
	<div class="pagination pagination-centered">
	<input id="w160" type="button" value="160x120">
	<input id="w320" type="button" value="320x240">
	<input id="w640" type="button" value="640x480">
	<input id="w1024" type="button" value="1024x768">
	<input type="button" class="bt_on .btn btn-success" value="on"/>
	<input type="button" class="bt_off .btn btn-danger .disabled" value="off"/>
		<ul class="thumbnails">
			<c:forEach var="camera" items="${it.cameras}">
				<li class="span3">
					<div class="thumbnail">
						<img source="<%=baseUrl %>/api/cameras/${camera.idCamera}/stream" src="<%=baseUrl%>/images/no_image.jpg" class="thumb">
						<c:forEach var="location" items="${it.locations}">
							<c:if test="${location.idLocation == camera.idLocation }">
								<h5>${location.name} : ${camera.name }</h5>
								<c:forEach var="action" items="${ it.actions}">
									<c:if test="${action.idModel == camera.idModel}">
										<form action="<%=baseUrl %>/api/actions/${action.idAction }/execute?idCamera=${camera.idCamera}" method="GET" class="form-inline">
											<input type="submit" class="btn btn-info" value="${action.name}">
										</form> 
									</c:if>
								</c:forEach>
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
		   e.preventDefault();  
			$(".thumb").each(function(){
				var on_src=$(this).attr("source");
				$(this).attr("src",on_src);
			});
		});
	   $(".bt_off").click(function(e){
		   e.preventDefault();
		 	$(".thumb").each(function(){
				$(this).attr("src",'<%=baseUrl%>/images/no_image.jpg');
			});
		});
	   $('form').submit(function () {
		   $.get(this.action, function(){ alert("submitted")});
		   return false;
		  });
	</script>	
<%@ include file="footer.jsp"%>