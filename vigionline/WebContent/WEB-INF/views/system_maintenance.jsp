<%@ include file="header.jsp"%>
<div class="container">
	<!-- Begin Navigation -->
	<ul class="breadcrumb">
		<li class="active"><%=messages.getMessage("system_maintenance")%></li>
	</ul>
	<!-- End Navigation -->
	<form class="hero-unit" action="<%=baseUrl%>/system/maintenance" method="POST">
		<span class="label label-info"><%= messages.getMessage("begin_date") %></span>
		<div class="well">		
			<span class="label"><%=messages.getMessage("day")%></span> <input
				type="text" id="beginDate" name="b_day" data-date-format="dd-mm-yyyy"
				class="input-medium" /><br /> <span class="label"><%=messages.getMessage("hour")%></span>
			<input type="text" id="hour" name="b_hour" class="input-mini"
				value="00" /> <span class="label"><%=messages.getMessage("min")%></span>
			<input type="text" id="min" name="b_min" class="input-mini" value="00" />
		</div>
		
		<span class="label label-info"><%= messages.getMessage("end_date") %></span>
		<div class="well">
			<span class="label"><%=messages.getMessage("day")%></span> <input
				type="text" id="endDate" name="e_day" data-date-format="dd-mm-yyyy"
				class="input-medium" /><br /> <span class="label"><%=messages.getMessage("hour")%></span>
			<input type="text" id="hour" name="e_hour" class="input-mini"
				value="00" /> <span class="label"><%=messages.getMessage("min")%></span>
			<input type="text" id="min" name="e_min" class="input-mini" value="00" />
		</div>
		<input type="submit" class="btn btn-danger" value="<%=messages.getMessage("delete_images") %>">
	</form>
</div>
<script type="text/javascript">
	$(function(){
		$("#beginDate").datepicker();
		$("#endDate").datepicker();
	});
</script>
<%@ include file="footer.jsp"%>