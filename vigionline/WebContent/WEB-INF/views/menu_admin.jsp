<!-- RECORDINGS -->
<li> <a href="<%= baseUrl %>/recordings"><%= messages.getMessage("recordings") %></a></li>
				<li class="divider-vertical"></li>
<!-- LOCATIONS -->
<li class="dropdown"><a class="dropdown-toggle"
	data-toggle="dropdown" href="#"> <%= messages.getMessage("locations") %>
		<b class="caret"></b>
</a>
	<ul id="menu-locations" class="dropdown-menu">
		<li><a href="<%= baseUrl %>/locations"><%= messages.getMessage("all") %></a></li>
		<li class="divider"></li>
		<li><a href="<%= baseUrl %>/locations/create"><%= messages.getMessage("create_location") %></a></li>
	</ul></li>
<li class="divider-vertical"></li>

<!-- MANUFACTURERS -->
<li class="dropdown"><a class="dropdown-toggle"
	data-toggle="dropdown" href="#"> <%= messages.getMessage("manufacturers") %>
		<b class="caret"></b>
</a>
	<ul id="menu-manufacturers" class="dropdown-menu">
		<li><a href="<%= baseUrl %>/manufacturers"><%= messages.getMessage("all") %></a></li>
		<li class="divider"></li>
		<li><a href="<%= baseUrl %>/manufacturers/create"><%= messages.getMessage("create_manufacturer") %></a></li>
	</ul></li>
<li class="divider-vertical"></li>

<!-- MODELS -->
<li class="dropdown"><a class="dropdown-toggle"
	data-toggle="dropdown" href="#"> <%= messages.getMessage("models") %>
		<b class="caret"></b>
</a>
	<ul id="menu-models" class="dropdown-menu">
		<li><a href="<%= baseUrl %>/models"><%= messages.getMessage("all") %></a></li>
		<li class="divider"></li>
		<li><a href="<%= baseUrl %>/models/create"><%= messages.getMessage("create_model") %></a></li>
	</ul></li>
<li class="divider-vertical"></li>

<!-- ACTIONS -->
<li class="dropdown"><a class="dropdown-toggle"
	data-toggle="dropdown" href="#"> <%= messages.getMessage("actions") %>
		<b class="caret"></b>
</a>
	<ul id="menu-actions" class="dropdown-menu">
		<li><a href="<%= baseUrl %>/actions"><%= messages.getMessage("all") %></a></li>
		<li class="divider"></li>
		<li><a href="<%= baseUrl %>/actions/create"><%= messages.getMessage("create_action") %></a></li>
	</ul></li>
<li class="divider-vertical"></li>

<!-- CAMERAS -->
<li class="dropdown"><a class="dropdown-toggle"
	data-toggle="dropdown" href="#"> <%= messages.getMessage("cameras") %>
		<b class="caret"></b>
</a>
	<ul id="menu-cameras" class="dropdown-menu">
		<li><a href="<%= baseUrl %>/cameras"><%= messages.getMessage("all") %></a></li>
		<li class="divider"></li>
		<li><a href="<%= baseUrl %>/cameras/create"><%= messages.getMessage("create_camera") %></a></li>
	</ul></li>
<li class="divider-vertical"></li>

<!-- DIVISIONS -->
<li class="dropdown"><a class="dropdown-toggle"
	data-toggle="dropdown" href="#"> <%= messages.getMessage("divisions") %>
		<b class="caret"></b>
</a>
	<ul id="menu-divisions" class="dropdown-menu">
		<li><a href="<%= baseUrl %>/divisions"><%= messages.getMessage("all") %></a></li>
		<li class="divider"></li>
		<li><a href="<%= baseUrl %>/divisions/create"><%= messages.getMessage("create_division") %></a></li>
	</ul></li>
<li class="divider-vertical"></li>

<!-- USERS -->
<li class="dropdown"><a class="dropdown-toggle"
	data-toggle="dropdown" href="#"> <%= messages.getMessage("users") %>
		<b class="caret"></b>
</a>
	<ul id="menu-users" class="dropdown-menu">
		<li><a href="<%= baseUrl %>/users"><%= messages.getMessage("all") %></a></li>
		<li class="divider"></li>
		<li><a href="<%= baseUrl %>/users/create"><%= messages.getMessage("create_user") %></a></li>
	</ul></li>
<li class="divider-vertical"></li>

<!-- ROLES -->
<li class="dropdown"><a class="dropdown-toggle"
	data-toggle="dropdown" href="#"> <%= messages.getMessage("roles") %>
		<b class="caret"></b>
</a>
	<ul id="menu-roles" class="dropdown-menu">
		<li><a href="<%= baseUrl %>/roles"><%= messages.getMessage("all") %></a></li>
		<li class="divider"></li>
		<li><a href="<%= baseUrl %>/roles/create"><%= messages.getMessage("create_role") %></a></li>
	</ul></li>