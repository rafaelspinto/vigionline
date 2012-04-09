<div id="menu" class="navbar navbar-static">
            <div class="navbar-inner">
              <div class="container" style="width: auto;">
                <a class="brand" href="${pageContext.request.contextPath}">Vigionline</a>
                <ul class="nav">
                  <li class="dropdown">
                  	<a class="dropdown-toggle" data-toggle="dropdown" href="#" >
                  		<%= messages.getMessage("locations") %>
                       	<b class="caret"></b>
                    </a>
                  	<ul id="locations" class="dropdown-menu">
                  		<li><a href="<%= baseUrl %>/locations"><%= messages.getMessage("all") %></a></li>
                      	<li class="divider"></li>
                      	<li><a href="<%= baseUrl %>/locations/create"><%= messages.getMessage("create_location") %></a></li>
                    </ul>	
                  </li>
                  <li class="divider-vertical"></li>
                  <li class="dropdown">
                  	<a class="dropdown-toggle" data-toggle="dropdown" href="#" >
                  		<%= messages.getMessage("manufacturers") %>
                       	<b class="caret"></b>
                    </a>
                  	<ul id="manufacturers" class="dropdown-menu">
                  		<li>
                  			<a href="<%= baseUrl %>/manufacturers"><%= messages.getMessage("all") %></a></li>
                      		<li class="divider"></li>
                      		<a href="<%= baseUrl %>/manufacturers/create"><%= messages.getMessage("create_manufacturer") %></a></li>
                    	</ul>	
                  </li>
                  <li class="divider-vertical"></li>
                  <li class="dropdown">
                  	<a class="dropdown-toggle" data-toggle="dropdown" href="#" >
                  		<%= messages.getMessage("models") %>
                       	<b class="caret"></b>
                    </a>
                  	<ul class="dropdown-menu">
                  		<li>
                  			<a href="<%= baseUrl %>/models"><%= messages.getMessage("all") %></a></li>
                      		<li class="divider"></li>
                      		<a href="<%= baseUrl %>/models/create"><%= messages.getMessage("create_model") %></a></li>
                    	</ul>	
                  </li>
                  <li class="divider-vertical"></li>
                  <li class="dropdown">
                  	<a class="dropdown-toggle" data-toggle="dropdown" href="#" >
                  		<%= messages.getMessage("actions") %>
                       	<b class="caret"></b>
                    </a>
                  	<ul class="dropdown-menu">
                  		<li>
                  			<a href="<%= baseUrl %>/actions"><%= messages.getMessage("all") %></a></li>
                      		<li class="divider"></li>
                      		<a href="<%= baseUrl %>/actions/create"><%= messages.getMessage("create_action") %></a></li>
                    	</ul>	
                  </li>
                  <li class="divider-vertical"></li>
                  <li class="dropdown">
                  	<a class="dropdown-toggle" data-toggle="dropdown" href="#" >
                  		<%= messages.getMessage("cameras") %>
                       	<b class="caret"></b>
                    </a>
                  	<ul class="dropdown-menu">
                  		<li>
                  			<a href="<%= baseUrl %>/cameras"><%= messages.getMessage("all") %></a></li>
                      		<li class="divider"></li>
                      		<a href="<%= baseUrl %>/cameras/create"><%= messages.getMessage("create_camera") %></a></li>
                    	</ul>	
                  </li>
                  <li class="divider-vertical"></li>
                  <li class="dropdown">
                  	<a class="dropdown-toggle" data-toggle="dropdown" href="#" >
                  		<%= messages.getMessage("users") %>
                       	<b class="caret"></b>
                    </a>
                  	<ul class="dropdown-menu">
                  		<li>
                  			<a href="<%= baseUrl %>/users"><%= messages.getMessage("all") %></a></li>
                      		<li class="divider"></li>
                      		<a href="<%= baseUrl %>/users/create"><%= messages.getMessage("create_user") %></a></li>
                    	</ul>	
                  </li>
                  <li class="divider-vertical"></li>
                   <li class="dropdown">
                  	<a class="dropdown-toggle" data-toggle="dropdown" href="#" >
                  		<%= messages.getMessage("roles") %>
                       	<b class="caret"></b>
                    </a>
                  	<ul class="dropdown-menu">
                  		<li>
                  			<a href="<%= baseUrl %>/roles"><%= messages.getMessage("all") %></a></li>
                      		<li class="divider"></li>
                      		<a href="<%= baseUrl %>/roles/create"><%= messages.getMessage("create_role") %></a></li>
                    	</ul>	
                  </li>
                  <li class="divider-vertical"></li>
                </ul>
                <ul class="nav pull-right">
                	<li id="fat-menu" class="dropdown">
                    	<a href="#" class="dropdown-toggle" data-toggle="dropdown"><%= messages.getMessage("settings") %><b class="caret"></b></a>
                    	<ul class="dropdown-menu">
                      		<li><a href="#">Action</a></li>
                      		<li class="divider"></li>
                      		<li><a href="#">Separated link</a></li>
                    	</ul>
                  	</li>
                  	<li><a href="${pageContext.request.contextPath}/setLang?lang=pt&country=PT"><img src="${pageContext.request.contextPath}/images/portuguese.png"></a></li>
                  	<li><a href="${pageContext.request.contextPath}/setLang?lang=en&country=EN"><img src="${pageContext.request.contextPath}/images/english.png"></a></li>
                </ul>
              </div>
            </div>
          </div>