<!-- MENU LOCATIONS -->
<li class="dropdown">
	<a class="dropdown-toggle"	data-toggle="dropdown" href="#"> <%= messages.getMessage("locations") %><b class="caret"></b></a>
	<ul id="menu-cameras" class="dropdown-menu">
		<!-- LOCATIONS -->
		<li class="nav-header"><i class="icon-home"></i><%= messages.getMessage("locations") %></li>		
		<li><a href="<%= baseUrl %>/locations"><%= messages.getMessage("all_locations") %></a></li>
		<li><a href="<%= baseUrl %>/locations/create"><i class="icon-plus"></i><%= messages.getMessage("create_location") %></a></li>
		</ul>
</li>

<!-- MENU CAMERAS -->
<li class="dropdown">
	<a class="dropdown-toggle"	data-toggle="dropdown" href="#"> <%= messages.getMessage("equipments") %><b class="caret"></b></a>
	<ul id="menu-cameras" class="dropdown-menu">
		<!-- MANUFACTURERS -->
		<li class="nav-header"><%= messages.getMessage("manufacturers") %></li>		
		<li><a href="<%= baseUrl %>/manufacturers"><%= messages.getMessage("all_manufacturers") %></a></li>
		<li><a href="<%= baseUrl %>/manufacturers/create"><i class="icon-plus"></i><%= messages.getMessage("create_manufacturer") %></a></li>
		<li class="divider"></li>

		<!-- MODELS -->
		<li class="nav-header"><%= messages.getMessage("models") %></li>		
		<li><a href="<%= baseUrl %>/models"><%= messages.getMessage("all_models") %></a></li>
		<li><a href="<%= baseUrl %>/models/create"><i class="icon-plus"></i><%= messages.getMessage("create_model") %></a></li>
		<li class="divider"></li>

		<!-- ACTIONS -->
		<li class="nav-header"><%= messages.getMessage("actions") %></li>		
		<li><a href="<%= baseUrl %>/actions"><%= messages.getMessage("all_actions") %></a></li>
		<li><a href="<%= baseUrl %>/actions/create"><i class="icon-plus"></i><%= messages.getMessage("create_action") %></a></li>
		<li class="divider"></li>		
		
		<!-- CAMERAS -->
		<li class="nav-header"><i class="icon-camera"></i><%= messages.getMessage("cameras") %></li>		
		<li><a href="<%= baseUrl %>/cameras"><%= messages.getMessage("all_cameras") %></a></li>
		<li><a href="<%= baseUrl %>/cameras/create"><i class="icon-plus"></i><%= messages.getMessage("create_camera") %></a></li>
		
	</ul>
</li>

<!-- MENU PERMISSIONS -->
<li class="dropdown">
	<a class="dropdown-toggle"data-toggle="dropdown" href="#"><%= messages.getMessage("permissions") %><b class="caret"></b></a>
	<ul id="menu-users" class="dropdown-menu">
		<!-- USERS -->
		<li class="nav-header"><i class="icon-user"></i><%= messages.getMessage("users") %></li>
		<li><a href="<%= baseUrl %>/users"><%= messages.getMessage("all_users") %></a></li>
		<li><a href="<%= baseUrl %>/users/create"><i class="icon-plus"></i><%= messages.getMessage("create_user") %></a></li>
		<li class="divider"></li>
			
		<!-- ROLES -->
		<li class="nav-header"><%= messages.getMessage("roles") %></li>
		<li><a href="<%= baseUrl %>/roles"><%= messages.getMessage("all_roles") %></a></li>
		<li class="divider"></li>
		
		<!-- DIVISIONS -->
		<li class="nav-header"><%= messages.getMessage("divisions") %></li>
		<li><a href="<%= baseUrl %>/divisions"><%= messages.getMessage("all_divisions") %></a></li>
		<li><a href="<%= baseUrl %>/divisions/create"><i class="icon-plus"></i><%= messages.getMessage("create_division") %></a></li>
	</ul>
</li>

<!-- MENU LOCATIONS -->
<li class="dropdown">
	<a class="dropdown-toggle"	data-toggle="dropdown" href="#"> <%= messages.getMessage("system") %><b class="caret"></b></a>
	<ul id="menu-cameras" class="dropdown-menu">
		<!-- LOCATIONS -->
		<li class="nav-header"><i class="icon-info-sign"></i><%= messages.getMessage("info") %></li>		
		<li><a href="<%= baseUrl %>/system"><%= messages.getMessage("system_info") %></a></li>
		<li><a href="<%= baseUrl %>/system/maintenance"><%= messages.getMessage("system_maintenance") %></a></li>
		</ul>
</li>