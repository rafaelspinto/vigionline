<!-- ------------------------------------------------------ -->
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="vigionline.common.lang.MessageLocator"%>
<%
	String baseUrl = request.getContextPath();
	String lang = "", country = "";
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie c : cookies) {
			if (c.getName().equals("lang")) {
				lang = c.getValue();
			} else if (c.getName().equals("country")) {
				country = c.getValue();
			}
		}
	}
	MessageLocator messages = new MessageLocator(lang, country);
%>
<!--  USER STUFF -->
<%@page import="org.apache.catalina.realm.GenericPrincipal"%>
<%
	GenericPrincipal genericPrincipal = (GenericPrincipal) request.getUserPrincipal();
	final String[] roles = genericPrincipal.getRoles();
	boolean isAdmin = false;
	for (String role : roles) {
		if (role.equals("admin")) {
			isAdmin = true;
			break;
		}
	}
%>
<!-- ------------------------------------------------------ -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=baseUrl%>/css/bootstrap.css">
<link rel="stylesheet" href="<%=baseUrl%>/css/jquery-ui.css">
<script type="text/javascript" src="<%=baseUrl%>/js/jquery-latest.js"></script>
<script type="text/javascript" src="<%=baseUrl%>/js/jquery-ui-latest.js"></script>
<script type="text/javascript" src="<%=baseUrl%>/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="<%=baseUrl%>/js/bootstrap-dropdown.js"></script>
<script type="text/javascript" src="<%=baseUrl%>/js/bootstrap-alert.js"></script>
<script type="text/javascript" src="<%=baseUrl%>/js/vigionline.js"></script>
<title>Vigionline</title>
</head>
<body>