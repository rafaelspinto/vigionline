<!-- ------------------------------------------------------ -->
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="vigionline.common.lang.MessageLocator"%>
<%
        String baseUrl = request.getContextPath();
		String lang = request.getParameter("lang"), country = request.getParameter("country");
		MessageLocator messages = new MessageLocator(lang, country);
%>
<!-- ------------------------------------------------------ -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="http://twitter.github.com/bootstrap/1.4.0/bootstrap.css">
<title>Vigionline</title>
</head>
<body>
	<div class="container">
		<div class="hero-unit">
			<form class="well" action="j_security_check" method="POST">
				<h2>Vigionline</h2>
				<label for="j_username"><%= messages.getMessage("username") %></label>
				<input name="j_username" /><br /> <label for="j_password"><%= messages.getMessage("password") %></label>
				<input name="j_password" type="password" /> <br /> <input
					class="btn" type="submit"
					value="<%= messages.getMessage("login") %>" />
				<hr />
				<a
					href="${pageContext.request.contextPath}/logon?lang=pt&country=PT">PT</a>
				| <a
					href="${pageContext.request.contextPath}/logon?lang=en&country=EN">EN</a>
			</form>
		</div>
	</div>
</body>
</html>