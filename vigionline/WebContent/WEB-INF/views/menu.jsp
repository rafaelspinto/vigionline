<div id="navbar-example" class="navbar navbar-static">
            <div class="navbar-inner">
              <div class="container" style="width: auto;">
                <a class="brand" href="${pageContext.request.contextPath}">Vigionline</a>
                <ul class="nav">
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><%= messages.getMessage("locations") %><b class="caret"></b></a>
                    <ul class="dropdown-menu">
                      <li><a href="${pageContext.request.contextPath}/locations/create"><%= messages.getMessage("create_location") %></a></li>
                      <li class="divider"></li>
                      <li><a href="${pageContext.request.contextPath}/locations"><%= messages.getMessage("view_all_locations") %></a></li>
                    </ul>
                  </li>
                </ul>
                <ul class="nav pull-right">
                <li><a href="${pageContext.request.contextPath}/setLang?lang=pt&country=PT"><img src="${pageContext.request.contextPath}/images/portuguese.png"></a></li>
                  <li><a href="${pageContext.request.contextPath}/setLang?lang=en&country=EN"><img src="${pageContext.request.contextPath}/images/english.png"></a></li>
                  <li id="fat-menu" class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><%= messages.getMessage("settings") %><b class="caret"></b></a>
                    <ul class="dropdown-menu">
                      <li><a href="#">Action</a></li>
                      <li class="divider"></li>
                      <li><a href="#">Separated link</a></li>
                    </ul>
                  </li>
                </ul>
              </div>
            </div>
          </div> <!-- /navbar-example -->