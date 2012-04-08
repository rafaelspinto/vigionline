<div class="navbar navbar-static">
            <div class="navbar-inner">
              <div class="container" style="width: auto;">
                <a class="brand" href="${pageContext.request.contextPath}">Vigionline</a>
                <ul class="nav">
                  <li><a href="<%= baseUrl %>/locations"><%= messages.getMessage("locations") %></a></li>
                  <li class="divider-vertical"></li>
                  <li><a href="<%= baseUrl %>/manufacturers"><%= messages.getMessage("manufacturers") %></a></li>
                  <li class="divider-vertical"></li>
                  <li><a href="<%= baseUrl %>/models"><%= messages.getMessage("models") %></a></li>
                  <li><a href="<%= baseUrl %>/actions"><%= messages.getMessage("actions") %></a></li>
                  <li><a href="<%= baseUrl %>/cameras"><%= messages.getMessage("cameras") %></a></li>
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