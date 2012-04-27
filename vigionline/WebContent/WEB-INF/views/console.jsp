<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
	<div class="pagination pagination-centered">
		<ul class="thumbnails">
			<c:forEach var="camera" items="${it.cameras}">
				<li class="span3">
					<div class="thumbnail">
						<img src="<%=baseUrl %>/api/cameras/${camera.idCamera}/stream" alt="" width="320" height="240" class="thumb">
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
	</script>		
<%@ include file="footer.jsp"%>