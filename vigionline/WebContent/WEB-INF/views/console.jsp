<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li><a href="<%=baseUrl %>">Home</a> <span class="divider">/</span></li>
			<li class="active"><%=messages.getMessage("console") %></li>
		</ul>
		<!-- End Navigation -->
		<div class="pagination pagination-centered">
			<ul class="thumbnails">
				<c:forEach var="camera" items="${it}">
					<li class="span3">
						<div class="thumbnail">
							<img src="<%=baseUrl %>/api/cameras/${camera.idCamera}/stream" alt="" width="320">
							<h5>${camera.name }</h5>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<script type="text/javascript">
		$('img').error(function(){
	        console.log(this.src);
	        this.src = 'http://placehold.it/320x240';
		});
	</script>		
<%@ include file="footer.jsp"%>