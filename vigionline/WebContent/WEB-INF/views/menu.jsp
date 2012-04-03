<div id="navbar-example" class="navbar navbar-static">
            <div class="navbar-inner">
              <div class="container" style="width: auto;">
                <a class="brand" href="${pageContext.request.contextPath}">Vigionline</a>
                <ul class="nav">
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><%= messages.getMessage("locations") %> <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                      <li><a href="${pageContext.request.contextPath}/locations/create">Create Location</a></li>
                      <li class="divider"></li>
                      <li><a href="#">View All Locations</a></li>
                    </ul>
                  </li>
                </ul>
                <ul class="nav pull-right">
                  <li id="fat-menu" class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Settings<b class="caret"></b></a>
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