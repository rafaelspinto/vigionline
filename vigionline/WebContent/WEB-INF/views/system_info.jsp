<%@ include file="header.jsp"%>
<div class="container">
	<!-- Begin Navigation -->
	<ul class="breadcrumb">
		<li class="active"><%=messages.getMessage("system_info")%></li>
	</ul>
	<!-- End Navigation -->
	<div class="hero-unit">
		<span class="label label-info"><%=messages.getMessage("image_dir")%></span>
		<span id="image_dir"></span>
		<hr />
		
		<span class="label label-info"><%=messages.getMessage("space_available")%></span>
		<span id="spc_available"></span> <b>GiB</b>
		
		<span class="label label-info"><%=messages.getMessage("total_space")%></span>
		<span id="total_spc"></span> <b>GiB</b>
		<hr />
		
		<span class="label label-info"><%=messages.getMessage("number_images")%></span>
		<span id="image_count"></span>
	</div>
<script type="text/javascript">
	
	function setData(m_url, m_container)
	{
		$.ajax({
			url : m_url,
			cache : false 
		}).done(function(data){
			$("#"+m_container).html(data);
		});
	}

	function pollSystem()
	{
		setData("<%=baseUrl%>/api/system/space_available", "spc_available");		
		setData("<%=baseUrl%>/api/system/total_space", "total_spc");
		setData("<%=baseUrl%>/api/system/image_count", "image_count");
		setTimeout(function(){
				pollSystem();
			},
			10000
		);
	}
	$(function(){
		pollSystem();
		setData("<%=baseUrl%>/api/system/image_dir", "image_dir");
	});
</script>
<%@ include file="footer.jsp"%>