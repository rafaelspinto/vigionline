<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
	<div class="pagination pagination-centered">
	<input id="w160" type="button" value="160x120">
	<input id="w320" type="button" value="320x240">
	<input id="w640" type="button" value="640x480">
	<input id="w1024" type="button" value="1024x768">
		<ul class="thumbnails">
			<c:forEach var="camera" items="${it.cameras}">
				<li class="span3">
					<div class="thumbnail">
						<img src="<%=baseUrl %>/api/cameras/${camera.idCamera}/stream" class="thumb">
						<c:forEach var="location" items="${it.locations}">
							<c:if test="${location.idLocation == camera.idLocation }">
								<h5>${location.name} : ${camera.name }</h5>
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
	</script>		
<%@ include file="footer.jsp"%>