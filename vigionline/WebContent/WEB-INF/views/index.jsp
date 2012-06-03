<%@ include file="header.jsp"%>
	<div class="container">
		<!-- Begin Navigation -->
		<ul class="breadcrumb">
			<li class="active">Home</li>
		</ul>
		<!-- End Navigation -->
		<div class="hero-unit">
			<h1>Vigionline</h1>
		</div>
		               
                       <c:forEach var="role" items="${roles}">
                                  ${role.id }
                       </c:forEach>	
                  	<!-- --------------- -->
	</div>
<%@ include file="footer.jsp"%>